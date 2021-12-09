package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.request.NewHolidayHomeDTO;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.Appointment;
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
                new ArrayList<Appointment>(),
                dto.getRulesOfConduct(),
                dto.getAdditionalInfo());
    }
}
