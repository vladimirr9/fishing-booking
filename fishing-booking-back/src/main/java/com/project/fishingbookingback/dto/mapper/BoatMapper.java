package com.project.fishingbookingback.dto.mapper;


import com.project.fishingbookingback.dto.response.ClientsBoatViewDTO;
import com.project.fishingbookingback.model.Boat;
import org.springframework.stereotype.Component;

@Component
public class BoatMapper {
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
        boatViewDTO.setMark(0);
        boatViewDTO.setPrice(boat.getPricePerDay());
        return boatViewDTO;
    }
}
