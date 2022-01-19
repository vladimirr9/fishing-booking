package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.response.PromotionViewDTO;
import com.project.fishingbookingback.model.BoatPromotion;
import com.project.fishingbookingback.model.FishingPromotion;
import com.project.fishingbookingback.model.HolidayHomePromotion;
import com.project.fishingbookingback.model.Promotion;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {

    public PromotionViewDTO toViewDTO(Promotion promotion){
        PromotionViewDTO promotionDTO = new PromotionViewDTO();
        promotionDTO.setPromotionId(promotion.getId());
        promotionDTO.setCurrentPrice( promotion.getPrice().floatValue());
        promotionDTO.setFromTime(promotion.getFromTime());
        promotionDTO.setToTime(promotion.getToTime());
        promotionDTO.setValidUntil(promotion.getValidUntil());

        if(promotion.getClass() == BoatPromotion.class)
            toViewDTO((BoatPromotion) promotion,promotionDTO);
        else if(promotion.getClass()==HolidayHomePromotion.class)
            toViewDTO((HolidayHomePromotion) promotion,promotionDTO);
        else
            toViewDTO((FishingPromotion) promotion,promotionDTO);


        return promotionDTO;
    }

    //MENJATI CENU

    private void toViewDTO(HolidayHomePromotion promotion,PromotionViewDTO promotionDTO){
        promotionDTO.setStandardPrice(promotion.getHolidayHome().getPricePerDay());
        promotionDTO.setEntityName(promotion.getHolidayHome().getName());
        promotionDTO.setEntityType("Holiday home");
    }

    private void toViewDTO(BoatPromotion promotion,PromotionViewDTO promotionDTO){
        promotionDTO.setStandardPrice(promotion.getBoat().getPricePerDay());
        promotionDTO.setEntityName(promotion.getBoat().getName());
        promotionDTO.setEntityType("Boat");
    }

    private void toViewDTO(FishingPromotion promotion,PromotionViewDTO promotionDTO){
        promotionDTO.setStandardPrice(promotion.getFishingAdventure().getHourlyPrice().floatValue());
        promotionDTO.setEntityName(promotion.getFishingAdventure().getName());
        promotionDTO.setEntityType("Fishing adventure");
    }

}

