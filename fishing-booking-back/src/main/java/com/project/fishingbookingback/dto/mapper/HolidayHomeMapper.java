package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.request.NewHolidayHomeDTO;
import com.project.fishingbookingback.dto.response.ClientsHolidayHomeDTO;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.Appointment;
import com.project.fishingbookingback.model.AvailablePeriod;
import com.project.fishingbookingback.model.HolidayHome;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HolidayHomeMapper {

    public HolidayHome toModel(NewHolidayHomeDTO dto) {
        return new HolidayHome(dto.getName(),
                new Address(dto.getStreetAndNumber(),
                        dto.getCity(),
                        dto.getCountry(),
                        dto.getLatitude(),
                        dto.getLongitude()),
                dto.getDescription(),
                dto.getExterior(),
                dto.getInterior(),
                dto.getRoomsPerHome(),
                dto.getBedsPerRoom(),
                new ArrayList<AvailablePeriod>(),
                dto.getRulesOfConduct(),
                dto.getAdditionalInfo());
    }

    public ClientsHolidayHomeDTO toHolidayViewDTO(HolidayHome home){
        ClientsHolidayHomeDTO holidayHomeDTO = new ClientsHolidayHomeDTO();
        holidayHomeDTO.setId(home.getId());
        if(home.getExterior().isEmpty())
            holidayHomeDTO.setImageUrl("No image");
        else
            holidayHomeDTO.setImageUrl(home.getExterior().get(0).getLink());
        holidayHomeDTO.setName(home.getName());
        holidayHomeDTO.setAddress(home.getAddress().toString());
        holidayHomeDTO.setDescription(home.getDescription());
        holidayHomeDTO.setMark(home.getAverageMark());
        holidayHomeDTO.setPrice(home.getPricePerDay());
        holidayHomeDTO.setRules(home.getRulesOfConduct());
        return holidayHomeDTO;
    }
}
