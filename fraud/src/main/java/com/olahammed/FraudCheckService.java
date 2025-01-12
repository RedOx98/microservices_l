package com.olahammed;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private  final  FraudCheckHistoryRepository fraudCheckHistoryRepository;



    public boolean isFraudulent(Integer customerId){
        fraudCheckHistoryRepository.save(
                        FraudCheckHistory
                        .builder()
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .customerId(customerId).build()
        );
        return false;
    }
}
