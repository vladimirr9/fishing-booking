package com.project.fishingbookingback.service;

import com.project.fishingbookingback.dto.request.NewHolidayHomeDTO;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.AdventureRepository;
import com.project.fishingbookingback.repository.HolidayHomeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayHomeService {
    private final UserService userService;
    private final HolidayHomeRepository holidayHomeRepository;
    private final LoggedUserService loggedUserService;

    public HolidayHomeService(UserService userService, AdventureRepository adventureRepository, HolidayHomeRepository holidayHomeRepository, LoggedUserService loggedUserService) {
        this.userService = userService;
        this.holidayHomeRepository = holidayHomeRepository;
        this.loggedUserService = loggedUserService;
    }

    public HolidayHome create(HolidayHome holidayHome) {
        HomeOwner homeOwner = (HomeOwner) userService.findByEmail(loggedUserService.getUsername());
        holidayHome.setHomeOwner(homeOwner);
        return holidayHomeRepository.save(holidayHome);
    }


    public List<HolidayHome> getHolidayHomes(String homeOwnerUsername) {
        return holidayHomeRepository.findByHomeOwnerId(homeOwnerUsername);
    }

    public HolidayHome update(long id, NewHolidayHomeDTO dto) {
        var holidayHome = holidayHomeRepository.getById(id);
        holidayHome.setAddress(new Address(dto.getStreetAndNumber(),
                dto.getCity(),
                dto.getCountry(),
                dto.getLatitude(),
                dto.getLongitude()));
        holidayHome.setDescription(dto.getDescription());
        holidayHome.setRoomsPerHome(dto.getRoomsPerHome());
        holidayHome.setBedsPerRoom(dto.getBedsPerRoom());
        holidayHome.setRulesOfConduct(dto.getRulesOfConduct());
        holidayHome.setAditionalInfo(dto.getAdditionalInfo());
        return holidayHomeRepository.save(holidayHome);
    }

    public void deleteHolidayHome(Long id) {
        HolidayHome holidayHome = holidayHomeRepository.getById(id);
        String userEmail = loggedUserService.getUsername();
        if (!userEmail.equals(holidayHome.getHomeOwner().getEmail())) {
            throw new NotAllowedException();
        }
        holidayHomeRepository.deleteById(id);
    }
}

