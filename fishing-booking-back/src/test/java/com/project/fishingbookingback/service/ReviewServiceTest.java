package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.Review;
import com.project.fishingbookingback.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private  ReservationService reservationService;
    @Mock
    private  ReviewRepository reviewRepository;
    @Mock
    private  AdventureService adventureService;
    @Mock
    private  HolidayHomeService holidayHomeService;
    @Mock
    private  BoatService boatService;
    @Mock
    private  EmailService emailService;

    private ReviewService reviewService;

    @BeforeEach
    void setUp(){
        reservationService = mock(ReservationService.class);
        reviewRepository = mock(ReviewRepository.class);
        adventureService = mock(AdventureService.class);
        holidayHomeService = mock(HolidayHomeService.class);
        boatService = mock(BoatService.class);
        emailService = mock(EmailService.class);

        reviewService = new ReviewService(reservationService,reviewRepository,adventureService,holidayHomeService,boatService,emailService);
    }

    @Test
    void findById_throwsException_ifNoEntityFound() {
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> reviewService.findByID(1L));
    }

    @Test
    void canFindAll() {
        reviewService.findALl();
        verify(reviewRepository).findAll();
    }

    @Test
    void canCreate(){
        Review review = new Review();
        reviewService.updateReview(review);
        verify(reviewRepository).save(review);
    }
}
