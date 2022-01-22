package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.repository.BoatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoatServiceTest {

    @Mock
    private BoatRepository boatRepository;
    @Mock
    private UserService userService;
    @Mock
    private LoggedUserService loggedUserService;
    @Mock
    private BoatPromotionService boatPromotionService;
    private BoatService boatService;

    @BeforeEach
    void setUp() {

        boatRepository = mock(BoatRepository.class);
        userService = mock(UserService.class);
        loggedUserService = mock(LoggedUserService.class);
        boatPromotionService = mock(BoatPromotionService.class);
        boatService = new BoatService(boatRepository, userService, loggedUserService, boatPromotionService);
    }


    @Test
    void findById_throwsException_ifNoEntityFound() {
        when(boatRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> boatService.findByID(1L));
        
    }

    @Test
    void canFindAll() {
        boatService.getAll();
        verify(boatRepository).findAll();
    }

    @Test
    void canAddExteriorPicture() {
        Boat boat = new Boat();
        Picture picture = new Picture();
        when(boatRepository.findById(1L)).thenReturn(Optional.of(boat));

        boatService.addPicture(1L, false, picture);

        verify(boatRepository).save(boat);

    }


}
