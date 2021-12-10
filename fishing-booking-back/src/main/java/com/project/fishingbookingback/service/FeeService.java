package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.ServiceFee;
import com.project.fishingbookingback.repository.FeeRepository;
import org.springframework.stereotype.Service;

@Service
public class FeeService {
    private final FeeRepository feeRepository;

    public FeeService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public ServiceFee get() {
        var fees = feeRepository.findAll();
        if (fees.isEmpty()) {
            return new ServiceFee(0.0);
        } else return fees.get(0);
    }

    public ServiceFee update(ServiceFee serviceFee) {
        var fees = feeRepository.findAll();
        if (fees.isEmpty()) {
            return feeRepository.save(serviceFee);
        } else {
            ServiceFee savedFee = fees.get(0);
            savedFee.setFee(serviceFee.getFee());
            return feeRepository.save(savedFee);
        }
    }
}
