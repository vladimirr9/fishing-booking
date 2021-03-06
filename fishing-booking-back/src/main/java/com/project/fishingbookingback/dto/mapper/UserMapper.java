package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.request.AdminRegistrationDTO;
import com.project.fishingbookingback.dto.request.UserRegistrationDTO;
import com.project.fishingbookingback.dto.response.UserDetailsResponseDTO;
import com.project.fishingbookingback.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDetailsResponseDTO toResponseDTO(User user) {
        boolean firstLogin = false;
        if (user.getRole() == Role.ROLE_ADMIN) {
            firstLogin = ((Admin) user).isFirstLogin();
        }
        return new UserDetailsResponseDTO(user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getAddress().getStreetAndNumber(),
                user.getAddress().getCity(),
                user.getAddress().getCountry(),
                firstLogin);

    }

    public Admin toModel(AdminRegistrationDTO adminRegistrationDTO) {
        Admin admin = new Admin();
        admin.setEmail(adminRegistrationDTO.getEmail());
        admin.setPassword(adminRegistrationDTO.getPassword());
        admin.setFirstName(adminRegistrationDTO.getFirstName());
        admin.setLastName(adminRegistrationDTO.getLastName());
        admin.setPhoneNumber(adminRegistrationDTO.getPhoneNumber());
        admin.setAddress(new Address(adminRegistrationDTO.getStreetAndNumber(),
                adminRegistrationDTO.getCity(),
                adminRegistrationDTO.getCountry(),
                null, null));
        admin.setRole(Role.ROLE_ADMIN);
        admin.setFirstLogin(true);
        return admin;
    }

    public Client toModel(UserRegistrationDTO userRegistrationDTO){
        Client client = new Client();
        client.setEmail(userRegistrationDTO.getEmail());
        client.setPassword(userRegistrationDTO.getPassword());
        client.setFirstName(userRegistrationDTO.getFirstName());
        client.setLastName(userRegistrationDTO.getLastName());
        client.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        client.setAddress(new Address(userRegistrationDTO.getStreetAndNumber(),
                userRegistrationDTO.getCity(),
                userRegistrationDTO.getCountry(),
                null, null));
        client.setRole(Role.ROLE_CLIENT);
        client.setEnabled(false);

        return client;
    }

}
