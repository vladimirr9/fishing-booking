package com.project.fishingbookingback.dto.mapper;


import com.project.fishingbookingback.dto.response.ReservationDTO;
import com.project.fishingbookingback.model.AdventureReservation;
import com.project.fishingbookingback.model.BoatReservation;
import com.project.fishingbookingback.model.HolidayHomeReservation;
import com.project.fishingbookingback.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class ReservationMapper {


    public ReservationDTO toDTO(Reservation reservation) {
        if (reservation.getClass() == HolidayHomeReservation.class)
            return holidayHomeReservationDTO((HolidayHomeReservation) reservation);
        else if (reservation.getClass() == BoatReservation.class)
            return boatReservationDTO((BoatReservation) reservation);
        else
            return adventureReservationDTO((AdventureReservation) reservation);
    }


    private ReservationDTO holidayHomeReservationDTO(HolidayHomeReservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setApproved(reservation.isApproved());
        reservationDTO.setName(reservation.getHolidayHome().getName());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setAddress(reservation.getHolidayHome().getAddress().toString());
        reservationDTO.setMark(reservation.getReview()!=null && reservation.getReview().isApproved() ? reservation.getReview().getMark() : 0);
        reservationDTO.setReviewPresent(reservation.getReview()!=null);
        reservationDTO.setReportPresent(reservation.getReport()!=null);
        reservationDTO.setComplaintPresent(reservation.getComplaint()!=null);
        if (reservation.getHolidayHome().getExterior().isEmpty())
            reservationDTO.setImgUrl("");
        else
            reservationDTO.setImgUrl(reservation.getHolidayHome().getExterior().get(0).getLink());
        reservationDTO.setDurationInHours(ChronoUnit.HOURS.between(reservation.getStartDate(), reservation.getEndDate()));
        reservationDTO.setPrice(reservation.getPrice());
        reservationDTO.setClient(reservation.getClient());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setOngoing();


        return reservationDTO;
    }

    private ReservationDTO boatReservationDTO(BoatReservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setApproved(reservation.isApproved());
        reservationDTO.setName(reservation.getBoat().getName());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setAddress(reservation.getBoat().getAddress().toString());
        reservationDTO.setMark(reservation.getReview()!=null && reservation.getReview().isApproved() ? reservation.getReview().getMark() : 0);
        reservationDTO.setReviewPresent(reservation.getReview()!=null);
        reservationDTO.setReportPresent(reservation.getReport()!=null);
        reservationDTO.setComplaintPresent(reservation.getComplaint()!=null);
        if (reservation.getBoat().getExterior().isEmpty())
            reservationDTO.setImgUrl("");
        else
            reservationDTO.setImgUrl(reservation.getBoat().getExterior().get(0).getLink());
        reservationDTO.setDurationInHours(ChronoUnit.HOURS.between(reservation.getStartDate(), reservation.getEndDate()));
        reservationDTO.setPrice(reservation.getPrice());
        reservationDTO.setClient(reservation.getClient());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setOngoing();
        return reservationDTO;
    }

    private ReservationDTO adventureReservationDTO(AdventureReservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setApproved(reservation.isApproved());
        reservationDTO.setName(reservation.getAdventure().getName());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setAddress(reservation.getAdventure().getAddress().toString());
        reservationDTO.setMark(reservation.getReview()!=null && reservation.getReview().isApproved() ? reservation.getReview().getMark() : 0);
        if (reservation.getAdventure().getPictures().isEmpty())
            reservationDTO.setImgUrl("");
        else
            reservationDTO.setImgUrl(reservation.getAdventure().getPictures().get(0).getLink());
        reservationDTO.setDurationInHours(ChronoUnit.HOURS.between(reservation.getStartDate(), reservation.getEndDate()));
        reservationDTO.setPrice(reservation.getPrice());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setReviewPresent(reservation.getReview()!=null);
        reservationDTO.setReportPresent(reservation.getReport()!=null);
        reservationDTO.setComplaintPresent(reservation.getComplaint()!=null);
        reservationDTO.setId(reservation.getId());
        reservationDTO.setClient(reservation.getClient());
        reservationDTO.setOngoing();
        return reservationDTO;
    }

}
