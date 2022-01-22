package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.request.NewAdventureDTO;
import com.project.fishingbookingback.dto.request.UpdateAdventureDTO;
import com.project.fishingbookingback.dto.response.ClientsAdventureViewDTO;
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
                newAdventureDTO.getHourlyPrice(),
                new Address(newAdventureDTO.getStreetAndNumber(),
                        newAdventureDTO.getCity(),
                        newAdventureDTO.getCountry(),
                        newAdventureDTO.getLatitude(),
                        newAdventureDTO.getLongitude()));
    }

    public FishingAdventure toModel(UpdateAdventureDTO updateAdventureDTO) {
        return new FishingAdventure(updateAdventureDTO.getName(),
                updateAdventureDTO.getDescription(),
                updateAdventureDTO.getBiography(),
                updateAdventureDTO.getMaxPeople(),
                updateAdventureDTO.getRulesOfConduct(),
                updateAdventureDTO.getAvailableEquipment(),
                updateAdventureDTO.getCancellationFee(),
                updateAdventureDTO.getHourlyPrice(),
                new Address(updateAdventureDTO.getStreetAndNumber(),
                        updateAdventureDTO.getCity(),
                        updateAdventureDTO.getCountry(),
                        updateAdventureDTO.getLatitude(),
                        updateAdventureDTO.getLongitude()));
    }

    public ClientsAdventureViewDTO toClientAdventureDTO(FishingAdventure adventure) {
        ClientsAdventureViewDTO returnDto = new ClientsAdventureViewDTO();
        returnDto.setId(adventure.getId());
        if(adventure.getPictures().isEmpty())
            returnDto.setImageUrl("No image");
        else
            returnDto.setImageUrl(adventure.getPictures().get(0).getLink());
        returnDto.setName(adventure.getName());
        returnDto.setAdress(adventure.getAddress().toString());
        returnDto.setDescription(adventure.getDescription());
        returnDto.setInstructorsDescription(adventure.getBiography());
        returnDto.setMark(adventure.getAverageMark());
        returnDto.setPrice(adventure.getHourlyPrice());
        return returnDto;
    }
}
