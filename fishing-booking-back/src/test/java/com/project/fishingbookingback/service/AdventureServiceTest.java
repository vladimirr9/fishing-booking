package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.model.FishingAdventure;
import com.project.fishingbookingback.model.Picture;
import com.project.fishingbookingback.repository.AdventureRepository;
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
class AdventureServiceTest {

    @Mock
    private AdventureRepository adventureRepository;
    @Mock
    private UserService userService;
    @Mock
    private LoggedUserService loggedUserService;
    @Mock
    private FishingPromotionService fishingPromotionService;
    private AdventureService adventureService;

    @BeforeEach
    void setUp() {

        adventureRepository = mock(AdventureRepository.class);
        userService = mock(UserService.class);
        loggedUserService = mock(LoggedUserService.class);
        fishingPromotionService = mock(FishingPromotionService.class);
        adventureService = new AdventureService(userService, adventureRepository, loggedUserService, fishingPromotionService);
    }


    @Test
    void findById_throwsException_ifNoEntityFound() {
        when(adventureRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> adventureService.findByID(1L));
    }

    @Test
    void canFindAll() {
        adventureService.getAllAdventures();
        verify(adventureRepository).findAll();
    }

    @Test
    void canAddPicture() {
        FishingAdventure fishingAdventure = new FishingAdventure("Nesto", "lepo", "Dobro", 5, "Tekst", "Sve", 0.15, 250d, new Address());
        Picture picture = new Picture();
        when(adventureRepository.findById(1L)).thenReturn(Optional.of(fishingAdventure));

        adventureService.addPicture(1L, picture);

        verify(adventureRepository).save(fishingAdventure);

    }


}
