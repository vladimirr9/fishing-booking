package com.project.fishingbookingback.dto.mapper;


import com.project.fishingbookingback.dto.request.NewBoatDTO;
import com.project.fishingbookingback.dto.response.ClientsBoatViewDTO;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.service.LoggedUserService;
import org.springframework.stereotype.Component;

@Component
public class BoatMapper {

    private final LoggedUserService loggedUserService;

    public BoatMapper(LoggedUserService loggedUserService) {
        this.loggedUserService = loggedUserService;
    }

    public ClientsBoatViewDTO toBoatViewDTO(Boat boat){
        ClientsBoatViewDTO boatViewDTO = new ClientsBoatViewDTO();
        boatViewDTO.setId(boat.getId());
        if(boat.getExterior().isEmpty())
            boatViewDTO.setImageUrl("No image");
        else
            boatViewDTO.setImageUrl(boat.getExterior().get(0).getLink());
        boatViewDTO.setName(boat.getName());
        boatViewDTO.setAddress(boat.getAddress().toString());
        boatViewDTO.setDescription(boat.getDescription());
        boatViewDTO.setMark(boat.getAverageMark());
        boatViewDTO.setPrice(boat.getPricePerDay());
        boatViewDTO.setSubscribed(boat.isClientSubscribed(loggedUserService.getUsername()));
        return boatViewDTO;
    }

    public Boat toModel(NewBoatDTO dto) {
        return new Boat(
                dto.getName(),
                dto.getType(),
                dto.getLength(),
                dto.getEngineNumber(),
                dto.getEnginePower(),
                dto.getMaxSpeed(),
                dto.getGps(),
                dto.getRadar(),
                dto.getVhf(),
                dto.getFishfinder(),
                dto.getCabin(),
                new Address(
                        dto.getStreetAndNumber(),
                        dto.getCity(),
                        dto.getCountry(),
                        dto.getLatitude(),
                        dto.getLongitude()
                ),
                dto.getDescription(),
                dto.getCapacity(),
                dto.getRulesOfConduct(),
                dto.getAdditionalInfo(),
                dto.getFishingEquipment(),
                dto.getCancellationFeePercentage(),
                dto.getPricePerDay()
        );
    }
}
