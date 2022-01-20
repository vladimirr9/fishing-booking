package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.UnrecognizedTypeException;
import com.project.fishingbookingback.model.*;
import org.springframework.stereotype.Service;



@Service
public class PromotionService {

    private final HolidayHomePromotionService holidayHomePromotionService;
    private final FishingPromotionService fishingPromotionService;
    private final BoatPromotionService boatPromotionService;

    public PromotionService(HolidayHomePromotionService holidayHomePromotionService, FishingPromotionService fishingPromotionService, BoatPromotionService boatPromotionService) {
        this.holidayHomePromotionService = holidayHomePromotionService;
        this.fishingPromotionService = fishingPromotionService;
        this.boatPromotionService = boatPromotionService;
    }


    public void removePromotion(Long promotionId,String type) {
        switch (type) {
            case "ADVENTURE" -> fishingPromotionService.deletePromotion(promotionId);
            case "HOLIDAY_HOME" -> holidayHomePromotionService.deletePromotion(promotionId);
            case "BOAT" -> boatPromotionService.deletePromotion(promotionId);
            default -> throw new UnrecognizedTypeException("Requested reservation type doesn't exist!");
        }
    }
}
