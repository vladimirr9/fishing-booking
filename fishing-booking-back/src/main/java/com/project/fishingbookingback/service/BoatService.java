package com.project.fishingbookingback.service;

import com.project.fishingbookingback.dto.request.NewBoatDTO;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.AdditionalService;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.AvailablePeriod;
import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.BoatOwner;
import com.project.fishingbookingback.model.BoatPromotion;
import com.project.fishingbookingback.model.BoatReservation;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.model.Promotion;
import com.project.fishingbookingback.model.Role;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.repository.BoatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BoatService {

    private final BoatRepository boatRepository;
    private final UserService userService;
    private final LoggedUserService loggedUserService;
    private final BoatPromotionService boatPromotionService;

    public BoatService(BoatRepository boatRepository, UserService userService, LoggedUserService loggedUserService, BoatPromotionService boatPromotionService) {
        this.boatRepository = boatRepository;
        this.userService = userService;
        this.loggedUserService = loggedUserService;
        this.boatPromotionService = boatPromotionService;
    }

    public List<Boat> getAvailableBoats(LocalDateTime from, LocalDateTime to) {
        List<Boat> availableBoats = new ArrayList<>();
        for (Boat boat : boatRepository.findAll())
            if (isBoatAvailable(boat, from, to))
                availableBoats.add(boat);

        return availableBoats;
    }

    public void reserveBoatPeriod(Long id, LocalDateTime from, LocalDateTime to) {
        AvailablePeriod availablePeriod = IsBoatAvailable(id, from, to);
        Boat boat = boatRepository.getById(id);
        boat.getAvailablePeriods().remove(availablePeriod);

        var sliceBefore = availablePeriod.sliceBefore(from);
        if (sliceBefore != null)
            boat.getAvailablePeriods().add(sliceBefore);

        var sliceAfter = availablePeriod.sliceAfter(to);
        if (sliceAfter != null)
            boat.getAvailablePeriods().add(sliceAfter);

        boatRepository.save(boat);
    }

    private AvailablePeriod IsBoatAvailable(Long id, LocalDateTime from, LocalDateTime to) {
        List<AvailablePeriod> availablePeriods = boatRepository.getById(id).getAvailablePeriods();
        for (AvailablePeriod availablePeriod : availablePeriods)
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return availablePeriod;
        throw new EntityNotFoundException(availablePeriods.getClass().toString());
    }

    private boolean isBoatAvailable(Boat boat, LocalDateTime from, LocalDateTime to) {
        for (AvailablePeriod availablePeriod : boat.getAvailablePeriods())
            if (availablePeriod.overlaps(from) && availablePeriod.overlaps(to))
                return true;
        return false;
    }


    public List<Boat> getAll() {
        return boatRepository.findAll();
    }

    public List<Boat> getBoatsForOwner(String ownerUsername, String search) {
        String boatSearch = search == null ? "" : search;
        return boatRepository.findByOwnerId(ownerUsername, boatSearch);
    }

    public Boat create(Boat boat) {
        BoatOwner boatOwner = (BoatOwner) userService.findByEmail(loggedUserService.getUsername());
        boat.setBoatOwner(boatOwner);
        return boatRepository.save(boat);
    }


    public Boat findByID(Long id) {
        return boatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Boat.class.getSimpleName()));
    }

    @Transactional(readOnly = true)
    public Boat findOneById(Long id){
        Boat boat = boatRepository.findOneById(id);
        if(boat==null) throw new EntityNotFoundException(Boat.class.getSimpleName());
        return boat;
    }

    public Boat update(long id, NewBoatDTO dto) {
        Boat boat = boatRepository.getById(id);
        boat.setName(dto.getName());
        boat.setAddress(new Address(dto.getStreetAndNumber(),
                dto.getCity(),
                dto.getCountry(),
                dto.getLatitude(),
                dto.getLongitude()));
        boat.setDescription(dto.getDescription());
        boat.setRulesOfConduct(dto.getRulesOfConduct());
        boat.setAdditionalInfo(dto.getAdditionalInfo());
        boat.setCabin(dto.getCabin());
        boat.setCancellationFeePercentage(dto.getCancellationFeePercentage());
        boat.setCapacity(dto.getCapacity());
        boat.setEngineNumber(dto.getEngineNumber());
        boat.setEnginePower(dto.getEnginePower());
        boat.setFishfinder(dto.getFishfinder());
        boat.setFishingEquipment(dto.getFishingEquipment());
        boat.setVhf(dto.getVhf());
        boat.setGps(dto.getGps());
        boat.setRadar(dto.getRadar());
        boat.setPricePerDay(dto.getPricePerDay());
        boat.setType(dto.getType());
        boat.setLength(dto.getLength());
        boat.setMaxSpeed(dto.getMaxSpeed());
        return boatRepository.save(boat);
    }

    public void deleteBoat(Long id) {
        Boat boat = boatRepository.getById(id);
        checkIfAllowed(boat);
        boatRepository.deleteById(id);
    }

    public void deletePicture(Long id, Long id_picture, Boolean is_interior) {
        Boat boat = findByID(id);
        checkIfAllowed(boat);
        (is_interior ? boat.getInterior() : boat.getExterior()).removeIf(picture -> picture.getId().equals(id_picture));
        boatRepository.save(boat);
    }

    private void checkIfAllowed(Boat boat) {
        String username = loggedUserService.getUsername();
        User user = userService.findByEmail(username);
        if (!username.equals(boat.getBoatOwner().getEmail()) && user.getRole() != Role.ROLE_ADMIN) {
            throw new NotAllowedException();
        }
    }

    public Boat addPicture(Long id, Boolean is_interior, Picture picture) {
        Boat boat = findByID(id);
        boat.addPicture(is_interior, picture);
        return boatRepository.save(boat);
    }

    public BoatPromotion addPromotion(Long id, Promotion promotion) {
        Boat boat = findByID(id);
        checkIfAllowed(boat);
        BoatPromotion boatPromotion = new BoatPromotion(promotion.getFromTime(), promotion.getToTime(), promotion.getPrice(), promotion.getValidUntil(), boat);
        return boatPromotionService.addPromotion(boatPromotion);
    }

    public void deletePromotion(Long id, Long id_promotion) {
        Boat boat = findByID(id);
        checkIfAllowed(boat);
        boatPromotionService.deletePromotion(id_promotion);
    }

    public List<BoatPromotion> getPromotions(Long id) {
        return boatPromotionService.getPromotions(id);
    }

    public AvailablePeriod addAvailablePeriod(Long id, AvailablePeriod newAvailablePeriod) {
        var boat = findByID(id);
        boat.getAvailablePeriods().add(newAvailablePeriod);
        boatRepository.save(boat);
        return newAvailablePeriod;
    }

    public void addReservationRemoveOverlapping(BoatReservation newBoatReservation) {
        var reservations = newBoatReservation.getBoat().getReservations();
        reservations.removeIf(reservation -> reservation.overlaps(newBoatReservation));
        reservations.add(newBoatReservation);
        boatRepository.save(newBoatReservation.getBoat());
    }

    public Collection<BoatPromotion> getAllPromotions() {
        String email = loggedUserService.getUsername();
        return boatPromotionService.getAllForBoatOwner(email);
    }

    public Boat addService(Long id, AdditionalService additionalService) {
        Boat boat = findByID(id);
        boat.addService(additionalService);
        return boatRepository.save(boat);
    }

    public void deleteAdditionalService(Long id, Long id_service) {
        Boat boat = findByID(id);
        checkIfAllowed(boat);
        boat.getAdditionalService().removeIf(service -> service.getId().equals(id_service));
        boatRepository.save(boat);
    }

    public void save(Boat boat) {
        boatRepository.save(boat);
    }

    public void updateAverageMark(Long id, Double average) {
        Boat boat = findByID(id);
        boat.setAverageMark(average);
        boatRepository.save(boat);
    }
}
