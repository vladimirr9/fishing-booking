package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.FishingPromotion;
import com.project.fishingbookingback.model.HolidayHomePromotion;
import com.project.fishingbookingback.repository.HolidayHomePromotionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class HolidayHomePromotionService {

    private final HolidayHomePromotionRepository repository;

    public HolidayHomePromotionService(HolidayHomePromotionRepository repository) {
        this.repository = repository;
    }

    public HolidayHomePromotion findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(HolidayHomePromotion.class.getSimpleName()));
    }


    public HolidayHomePromotion addPromotion(HolidayHomePromotion holidayHomePromotion) {
        return repository.save(holidayHomePromotion);
    }

    public void deletePromotion(Long id_promotion) {
        findByID(id_promotion);
        repository.deleteById(id_promotion);
    }

    public List<HolidayHomePromotion> getPromotions(Long id) {
        return repository.findByHolidayHome_Id(id);
    }
    public Collection<HolidayHomePromotion> getAllForHomeOwner(String email) { return  repository.getAllForHomeOwner(email);}
}