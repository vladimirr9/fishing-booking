package com.project.fishingbookingback.service;

import com.project.fishingbookingback.dto.request.NewHolidayHomeDTO;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.HolidayHomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayHomeService {
    private final UserService userService;
    private final HolidayHomeRepository holidayHomeRepository;
    private final LoggedUserService loggedUserService;

    public HolidayHomeService(UserService userService, HolidayHomeRepository holidayHomeRepository, LoggedUserService loggedUserService) {
        this.userService = userService;
        this.holidayHomeRepository = holidayHomeRepository;
        this.loggedUserService = loggedUserService;
    }

    public HolidayHome create(HolidayHome holidayHome) {
        HomeOwner homeOwner = (HomeOwner) userService.findByEmail(loggedUserService.getUsername());
        holidayHome.setHomeOwner(homeOwner);
        return holidayHomeRepository.save(holidayHome);
    }

    public List<HolidayHome> getAll(){
        return holidayHomeRepository.findAll();
    }

    public HolidayHome findByID(Long id) {
        return holidayHomeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(HolidayHome.class.getSimpleName()));
    }

    public List<HolidayHome> getHolidayHomes(String homeOwnerUsername, String holidayHomeName) {
        String holidayHomeSearch = holidayHomeName == null ? "" : holidayHomeName;
        return holidayHomeRepository.findByHomeOwnerId(homeOwnerUsername, holidayHomeSearch);
    }

    public HolidayHome update(long id, NewHolidayHomeDTO dto) {
        var holidayHome = holidayHomeRepository.getById(id);
        holidayHome.setName(dto.getName());
        holidayHome.setAddress(new Address(dto.getStreetAndNumber(),
                dto.getCity(),
                dto.getCountry(),
                dto.getLatitude(),
                dto.getLongitude()));
        holidayHome.setDescription(dto.getDescription());
        holidayHome.setRoomsPerHome(dto.getRoomsPerHome());
        holidayHome.setBedsPerRoom(dto.getBedsPerRoom());
        holidayHome.setRulesOfConduct(dto.getRulesOfConduct());
        holidayHome.setAdditionalInfo(dto.getAdditionalInfo());
        return holidayHomeRepository.save(holidayHome);
    }

    public void deleteHolidayHome(Long id) {
        HolidayHome holidayHome = holidayHomeRepository.getById(id);
        checkIfAllowed(holidayHome);
        holidayHomeRepository.deleteById(id);
    }

    public void deletePicture(Long id, Long id_picture, Boolean is_interior) {
        HolidayHome holidayHome = findByID(id);
        checkIfAllowed(holidayHome);
        (is_interior ? holidayHome.getInterior() : holidayHome.getExterior()).removeIf(picture -> picture.getId().equals(id_picture));
        holidayHomeRepository.save(holidayHome);
    }

    private void checkIfAllowed(HolidayHome holidayHome) {
        String username = loggedUserService.getUsername();
        if (!username.equals(holidayHome.getHomeOwner().getEmail())) {
            throw new NotAllowedException();
        }
    }

    public HolidayHome addPicture(Long id, Boolean is_interior, Picture picture) {
        HolidayHome holidayHome = findByID(id);
        checkIfAllowed(holidayHome);
        holidayHome.addPicture(is_interior, picture);
        return holidayHomeRepository.save(holidayHome);
    }
}
