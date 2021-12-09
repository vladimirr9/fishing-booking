package com.project.fishingbookingback.service;

import com.project.fishingbookingback.dto.request.ChangePasswordRequstDTO;
import com.project.fishingbookingback.dto.request.UserDetailsRequestDTO;
import com.project.fishingbookingback.exception.BadRoleException;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.BoatOwner;
import com.project.fishingbookingback.model.FishingInstructor;
import com.project.fishingbookingback.model.HomeOwner;
import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.model.User;
import com.project.fishingbookingback.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final LoggedUserService loggedUserService;

    public UserService(AddressService addressService, UserRepository userRepository, LoggedUserService loggedUserService) {
        this.addressService = addressService;
        this.userRepository = userRepository;
        this.loggedUserService = loggedUserService;
    }

    public User findByID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName()));
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException(User.class.getSimpleName());
        }
        return user;
    }


    public User createUser(ProviderRegistration providerRegistration) {
        User user = switch (providerRegistration.getRole()) {
            case ROLE_HOME_OWNER -> new HomeOwner(providerRegistration);
            case ROLE_BOAT_OWNER -> new BoatOwner(providerRegistration);
            case ROLE_FISHING_INSTRUCTOR -> new FishingInstructor(providerRegistration);
            default -> throw new BadRoleException("Can only register boat owner, fishing instructor, and home owner");
        };
        user.setAddress(addressService.insert(user.getAddress()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDetailsRequestDTO userDetailsRequestDTO) {
        checkIfAllowed(userDetailsRequestDTO.getEmail());
        User user = findByID(id);
        user.setFirstName(userDetailsRequestDTO.getFirstName());
        user.setLastName(userDetailsRequestDTO.getLastName());
        user.setPhoneNumber(userDetailsRequestDTO.getPhoneNumber());
        user.getAddress().setStreetAndNumber(userDetailsRequestDTO.getStreetAndNumber());
        user.getAddress().setCountry(userDetailsRequestDTO.getCountry());
        user.getAddress().setCity(userDetailsRequestDTO.getCity());
        return userRepository.save(user);
    }

    public void changePassword(String email, ChangePasswordRequstDTO changePasswordRequstDTO) {
        checkIfAllowed(email);
        User user = findByEmail(email);
        if (user.getPassword().equals(changePasswordRequstDTO.getCurrentPassword())) {
            user.setPassword(changePasswordRequstDTO.getNewPassword());
        }
        userRepository.save(user);
    }

    private void checkIfAllowed(String email) {
        String userEmail = loggedUserService.getUsername();
        if (!userEmail.equals(email)) {
            throw new NotAllowedException();
        }
    }


}
