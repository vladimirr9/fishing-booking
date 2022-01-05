package com.project.fishingbookingback.service;


import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.FishingPromotion;
import com.project.fishingbookingback.repository.FishingPromotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingPromotionService {

    private final FishingPromotionRepository repository;

    public FishingPromotionService(FishingPromotionRepository repository) {
        this.repository = repository;
    }

    public FishingPromotion findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(FishingPromotion.class.getSimpleName()));
    }


    public FishingPromotion addPromotion(FishingPromotion fishingPromotion) {
        return repository.save(fishingPromotion);
    }

    public void deletePromotion(Long id_promotion) {
        findByID(id_promotion);
        repository.deleteById(id_promotion);
    }

    public List<FishingPromotion> getPromotions(Long id) {
        return repository.findByFishingAdventure_Id(id);
    }
}
