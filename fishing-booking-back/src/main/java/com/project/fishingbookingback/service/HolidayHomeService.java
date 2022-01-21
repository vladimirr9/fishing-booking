package com.project.fishingbookingback.service;

import com.project.fishingbookingback.dto.request.NewHolidayHomeDTO;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.*;
import com.project.fishingbookingback.repository.HolidayHomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HolidayHomeService {
    private final UserService userService;
    private final HolidayHomeRepository holidayHomeRepository;
    private final LoggedUserService loggedUserService;
    private final HolidayHomePromotionService holidayHomePromotionService;

    public HolidayHomeService(UserService userService, HolidayHomeRepository holidayHomeRepository, LoggedUserService loggedUserService, HolidayHomePromotionService holidayHomePromotionService) {
        this.userService = userService;
        this.holidayHomeRepository = holidayHomeRepository;
        this.loggedUserService = loggedUserService;
        this.holidayHomePromotionService = holidayHomePromotionService;
    }

    public HolidayHome create(HolidayHome holidayHome) {
        HomeOwner homeOwner = (HomeOwner) userService.findByEmail(loggedUserService.getUsername());
        holidayHome.setHomeOwner(homeOwner);
        return holidayHomeRepository.save(holidayHome);
    }

    public List<HolidayHome> getAll() {
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
        User user = userService.findByEmail(username);
        if (!username.equals(holidayHome.getHomeOwner().getEmail()) && user.getRole() != Role.ROLE_ADMIN) {
            throw new NotAllowedException();
        }
    }

    public HolidayHome addPicture(Long id, Boolean is_interior, Picture picture) {
        HolidayHome holidayHome = findByID(id);
        checkIfAllowed(holidayHome);
        holidayHome.addPicture(is_interior, picture);
        return holidayHomeRepository.save(holidayHome);
    }

    public List<HolidayHome> getAvailableHomes(LocalDateTime from, LocalDateTime to) {
        List<HolidayHome> availableHomes = new ArrayList<>();
        for (HolidayHome holidayHome : holidayHomeRepository.findAll())
            if (isHomeAvailable(holidayHome, from, to))
                availableHomes.add(holidayHome);

        return availableHomes;
    }

    private boolean isHomeAvailable(HolidayHome holidayHome, LocalDateTime from, LocalDateTime to) {
        for (AvailablePeriod availablePeriod : holidayHome.getAvailablePeriods())
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return true;
        return false;
    }

    public void reserveHomePeriod(Long boatId,LocalDateTime from, LocalDateTime to){
        AvailablePeriod availablePeriod = IsHomeAvailable(boatId,from,to);
        HolidayHome home = holidayHomeRepository.getById(boatId);
        home.getAvailablePeriods().remove(availablePeriod);

        var sliceBefore = availablePeriod.sliceBefore(from);
        if(sliceBefore!=null)
            home.getAvailablePeriods().add(sliceBefore);

        var sliceAfter = availablePeriod.sliceAfter(to);
        if(sliceAfter!= null)
            home.getAvailablePeriods().add(sliceAfter);

        holidayHomeRepository.save(home);
    }

    private AvailablePeriod IsHomeAvailable(Long id, LocalDateTime from, LocalDateTime to) {
        List<AvailablePeriod> availablePeriods = holidayHomeRepository.getById(id).getAvailablePeriods();
        for (AvailablePeriod availablePeriod : availablePeriods)
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return availablePeriod;

        throw new EntityNotFoundException(availablePeriods.getClass().toString());
    }

    public HolidayHomePromotion addPromotion(Long id, Promotion promotion) {
        HolidayHome holidayHome = findByID(id);
        checkIfAllowed(holidayHome);
        HolidayHomePromotion holidayHomePromotion = new HolidayHomePromotion(promotion.getFromTime(), promotion.getToTime(), promotion.getPrice(), promotion.getValidUntil(), holidayHome);

        return holidayHomePromotionService.addPromotion(holidayHomePromotion);
    }

    public void deletePromotion(Long id, Long id_promotion) {
        HolidayHome holidayHome = findByID(id);
        checkIfAllowed(holidayHome);
        holidayHomePromotionService.deletePromotion(id_promotion);
    }

    public List<HolidayHomePromotion> getPromotions(Long id) {
        return holidayHomePromotionService.getPromotions(id);
    }

    public AvailablePeriod addAvailablePeriod(Long id, AvailablePeriod newAvailablePeriod) {
        var holidayHome = findByID(id);
        holidayHome.getAvailablePeriods().add(newAvailablePeriod);
        holidayHomeRepository.save(holidayHome);
        return newAvailablePeriod;
    }

    public void addReservationRemoveOverlapping(HolidayHomeReservation newHomeReservation) {
        var reservations = newHomeReservation.getHolidayHome().getReservations();
        reservations.removeIf(reservation -> reservation.overlaps(newHomeReservation));
        reservations.add(newHomeReservation);
        holidayHomeRepository.save(newHomeReservation.getHolidayHome());
    }

    public Collection<HolidayHomePromotion> getAllPromotions() {
        String email = loggedUserService.getUsername();
        return holidayHomePromotionService.getAllForHomeOwner(email);
    }

    public HolidayHome addService(Long id, AdditionalService additionalService) {
        HolidayHome holidayHome = findByID(id);
        holidayHome.addService(additionalService);
        return holidayHomeRepository.save(holidayHome);
    }

    public void deleteAdditionalService(Long id, Long id_service) {
        HolidayHome home = findByID(id);
        checkIfAllowed(home);
        home.getAdditionalService().removeIf(service -> service.getId().equals(id_service));
        holidayHomeRepository.save(home);
    }

    public void save(HolidayHome home) {
        holidayHomeRepository.save(home);
    }
}

