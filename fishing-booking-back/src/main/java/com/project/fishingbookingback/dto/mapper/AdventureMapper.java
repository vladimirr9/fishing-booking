package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.request.NewAdventureDTO;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.FishingAdventure;
import org.springframework.stereotype.Component;

@Component
public class AdventureMapper {
    public AdventureMapper() {
    }

    public FishingAdventure toModel(NewAdventureDTO newAdventureDTO) {
        return new FishingAdventure(newAdventureDTO.getName(),
                newAdventureDTO.getDescription(),
                newAdventureDTO.getBiography(),
                newAdventureDTO.getMaxPeople(),
                newAdventureDTO.getRulesOfConduct(),
                newAdventureDTO.getAvailableEquipment(),
                newAdventureDTO.getCancellationFee(),
                newAdventureDTO.getPriceList(),
                new Address(newAdventureDTO.getStreetAndNumber(),
                        newAdventureDTO.getCity(),
                        newAdventureDTO.getCountry(),
                        newAdventureDTO.getLatitude(),
                        newAdventureDTO.getLongitude()));
    }
}
