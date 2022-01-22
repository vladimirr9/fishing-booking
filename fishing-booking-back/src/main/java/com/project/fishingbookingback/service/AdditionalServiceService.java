package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.AdditionalService;
import com.project.fishingbookingback.repository.AdditionalServiceRepository;

import org.springframework.stereotype.Service;

@Service
public class AdditionalServiceService {
    private final AdditionalServiceRepository additionalServiceRepository;

    public AdditionalServiceService(AdditionalServiceRepository additionalServiceRepository) {
        this.additionalServiceRepository = additionalServiceRepository;
    }

    public AdditionalService findById(Long id){
        return additionalServiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AdditionalService.class.getSimpleName())) ;
    }
}
