package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.BoatPromotion;
import com.project.fishingbookingback.model.HolidayHomePromotion;
import com.project.fishingbookingback.repository.BoatPromotionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BoatPromotionService {

    private final BoatPromotionRepository repository;

    public BoatPromotionService(BoatPromotionRepository repository) {
        this.repository = repository;
    }

    public BoatPromotion findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(BoatPromotion.class.getSimpleName()));
    }


    public BoatPromotion addPromotion(BoatPromotion boatPromotion) {
        return repository.save(boatPromotion);
    }

    public void deletePromotion(Long id_promotion) {
        findByID(id_promotion);
        repository.deleteById(id_promotion);
    }

    public List<BoatPromotion> getPromotions(Long id) {
        return repository.findByBoat_Id(id);
    }
    public Collection<BoatPromotion> getAllForBoatOwner(String email) { return  repository.getAllForBoatOwner(email);}
}
