package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.request.FishingPromotionDTO;
import com.project.fishingbookingback.model.FishingPromotion;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {
    public FishingPromotion toModel(FishingPromotionDTO fishingPromotionDTO) {
        return new FishingPromotion(fishingPromotionDTO.getFromTime(), fishingPromotionDTO.getToTime(), fishingPromotionDTO.getPrice(), fishingPromotionDTO.getValidUntil(), null, fishingPromotionDTO.getPeopleNumber());
    }
}
