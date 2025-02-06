package com.olahammed.customer;

import com.olahammed.amqp.RabbitMQMessageProducer;
import com.olahammed.clients.fraud.FraudCheckResponse;
import com.olahammed.clients.fraud.FraudClient;
import com.olahammed.clients.notification.NotificationClient;
//import com.olahammed.clients.notification.NotificationPayloadDTO;
import com.olahammed.kafka.config.NotificationPayloadDTO;
import com.olahammed.clients.notification.NotificationResponseDTO;
import com.olahammed.kafka.config.KafkaProducerConfig;
import lombok.AllArgsConstructor;
//import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;
//import com.olahammed.kafka.KafkaProducer;

@Service
@AllArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final KafkaTemplate<String, NotificationPayloadDTO> kafkaTemplate;


    public CompletableFuture<NotificationResponseDTO> registerCustomer(CustomerRegistrationRequest request) {
        Customer  customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        Customer customer1 = new Customer(1,request.firstName(),request.lastName(),request.email());
        customer1.setId(1);
        customer1.setFirstName(request.firstName());
        customer1.setLastName(request.lastName());
        customer1.setEmail(request.email());


//        todo: check if email is valid
//        todo: check if password is valid
        customerRepository.saveAndFlush(customer);
//        todo: check if fraudster
        String url = String.format("http://FRAUD/api/v1/fraud-check/%d", customer.getId());
        String notificationUrl = String.format("http://NOTIFICATION/api/v1/notification/send-notification");
        System.out.println("Requesting Fraud Check at: " + url);

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                url,
//                FraudCheckResponse.class,
//                customer.getId()
//        );


//        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudulent(customer.getId());
//        if (fraudCheckResponse.isFraudster()) {
//            throw new IllegalStateException("fraudster");
//        }

//        todo: send notification
//        NotificationPayloadDTO payload = new NotificationPayloadDTO(
//                customer.getId(), 1, customer.getFirstName(), customer.getLastName()
//        );
//        NotificationResponseDTO notificationResponse = notificationClient.sendNotification(payload);
//        return notificationResponse;
        // Step 1: Call the fraud check asynchronously
         return CompletableFuture.supplyAsync(() -> {
            System.out.println("Requesting Fraud Check at: " + url);
            FraudCheckResponse fraudCheckResponse = fraudClient.isFraudulent(customer.getId());

            if (fraudCheckResponse.isFraudster()) {
                throw new IllegalStateException("fraudster");
            }

            return fraudCheckResponse;
        }).thenApplyAsync(fraudCheckResponse -> {
            // Step 2: Once fraud check is complete, send notification
             System.out.println("Requesting Notification sending at: " + notificationUrl);
            NotificationPayloadDTO payload = new NotificationPayloadDTO(
                    customer.getId(), 1, customer.getFirstName(), customer.getLastName(), customer.getEmail()
            );

            System.out.println("Sending notification...");
//            NotificationResponseDTO response = notificationClient.sendNotification(payload); //not sending request to notification directly anymore
             kafkaTemplate.send("olaidescode", new NotificationPayloadDTO(customer.getId(), 1, customer.getFirstName(), customer.getLastName(), customer.getEmail()));
             rabbitMQMessageProducer.publish(payload, "internal.exchange", "internal.notification.routing-key");
             return null;
        });
    }
}