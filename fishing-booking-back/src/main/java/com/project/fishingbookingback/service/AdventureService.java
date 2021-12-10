package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.AdditionalService;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.FishingInstructor;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.model.Role;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.repository.AdventureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService {
    private final UserService userService;
    private final AdventureRepository adventureRepository;
    private final LoggedUserService loggedUserService;

    public AdventureService(UserService userService, AdventureRepository adventureRepository, LoggedUserService loggedUserService) {
        this.userService = userService;
        this.adventureRepository = adventureRepository;
        this.loggedUserService = loggedUserService;
    }

    public FishingAdventure create(FishingAdventure fishingAdventure) {

        FishingInstructor fishingInstructor = (FishingInstructor) userService.findByEmail(loggedUserService.getUsername());
        fishingAdventure.setFishingInstructor(fishingInstructor);
        return adventureRepository.save(fishingAdventure);
    }

    public FishingAdventure findByID(Long id) {
        return adventureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(FishingAdventure.class.getSimpleName()));
    }

    public FishingAdventure addService(Long id, AdditionalService additionalService) {
        FishingAdventure fishingAdventure = findByID(id);
        fishingAdventure.addService(additionalService);
        return adventureRepository.save(fishingAdventure);
    }

    public FishingAdventure addPicture(Long id, Picture picture) {
        FishingAdventure fishingAdventure = findByID(id);
        fishingAdventure.addPicture(picture);
        return adventureRepository.save(fishingAdventure);
    }


    public List<FishingAdventure> getAdventures(String instructorUsername, String adventureName) {
        String adventureSearch = adventureName == null ? "" : adventureName;
        return adventureRepository.findByFishingInstructorId(instructorUsername, adventureSearch);
    }

    public void deleteAdventure(Long id) {
        FishingAdventure fishingAdventure = findByID(id);
        checkIfAllowed(fishingAdventure);
        adventureRepository.deleteById(id);
    }

    public FishingAdventure updateAdventure(Long id, FishingAdventure updatedFishingAdventure) {
        FishingAdventure fishingAdventure = findByID(id);
        checkIfAllowed(fishingAdventure);
        fishingAdventure.setName(updatedFishingAdventure.getName());
        fishingAdventure.setDescription(updatedFishingAdventure.getDescription());
        fishingAdventure.setBiography(updatedFishingAdventure.getBiography());
        fishingAdventure.setMaxPeople(updatedFishingAdventure.getMaxPeople());
        fishingAdventure.setRulesOfConduct(updatedFishingAdventure.getRulesOfConduct());
        fishingAdventure.setAvailableEquipment(updatedFishingAdventure.getAvailableEquipment());
        fishingAdventure.setCancellationFee(updatedFishingAdventure.getCancellationFee());
        fishingAdventure.setHourlyPrice(updatedFishingAdventure.getHourlyPrice());
        fishingAdventure.setAddress(updatedFishingAdventure.getAddress());
        return adventureRepository.save(fishingAdventure);
    }

    public void deleteAdditionalService(Long id, Long id_service) {
        FishingAdventure fishingAdventure = findByID(id);
        checkIfAllowed(fishingAdventure);
        fishingAdventure.getAdditionalService().removeIf(service -> service.getId().equals(id_service));
        adventureRepository.save(fishingAdventure);
    }

    public void deletePicture(Long id, Long id_picture) {
        FishingAdventure fishingAdventure = findByID(id);
        checkIfAllowed(fishingAdventure);
        fishingAdventure.getPictures().removeIf(picture -> picture.getId().equals(id_picture));
        adventureRepository.save(fishingAdventure);
    }

    private void checkIfAllowed(FishingAdventure fishingAdventure) {
        String email = loggedUserService.getUsername();
        User user = userService.findByEmail(email);
        if (!email.equals(fishingAdventure.getFishingInstructor().getEmail()) && user.getRole() != Role.ROLE_ADMIN) {
            throw new NotAllowedException();
        }
    }


}
