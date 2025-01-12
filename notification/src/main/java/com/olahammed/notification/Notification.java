package com.olahammed.notification;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notification {

    @Id()
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Integer id;

    private Integer customerId;

    private Integer fraudCheckId;

    private String message;

    private String firstName;

    private String lastName;

    private String toCustomerEmail;

    private String sender;

    public String fullName(){
        return this.firstName + " " + this.lastName;
    }

//    @Enumerated
//            (EnumType.STRING)
//    @Column(name = "verified_type", columnDefinition = "enum('READ', 'UNREAD') DEFAULT 'UNREAD'")
    private String status;

    private LocalDateTime sentAt;
}
