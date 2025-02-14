package com.olahammed;

import com.olahammed.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
//@NoArgsConstructor
public class FraudCheckHistoryController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudulent(@PathVariable("customerId") Integer customerId){
        boolean isFradulentCustomer = fraudCheckService.isFraudulent(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFradulentCustomer);
    }
}
