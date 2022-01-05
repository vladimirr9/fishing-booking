package com.project.fishingbookingback.dto.mapper;

import com.project.fishingbookingback.dto.response.ReservationDTO;
import com.project.fishingbookingback.model.AdventureReservation;
import com.project.fishingbookingback.model.BoatReservation;
import com.project.fishingbookingback.model.HolidayHomeReservation;
import com.project.fishingbookingback.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public ReservationDTO toDTO(Reservation reservation){
        if(reservation.getClass()== HolidayHomeReservation.class)
            return holidayHomeReservationDTO((HolidayHomeReservation) reservation);
        else if(reservation.getClass() == BoatReservation.class)
            return boatReservationDTO((BoatReservation) reservation);
        else
            return adventureReservationDTO((AdventureReservation) reservation);
    }

    private ReservationDTO holidayHomeReservationDTO(HolidayHomeReservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setName(reservation.getHolidayHome().getName());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setAddress(reservation.getHolidayHome().getAddress().toString());
        reservationDTO.setMark(reservation.getMark());
        if(reservation.getHolidayHome().getExterior().isEmpty())
            reservationDTO.setImgUrl("");
        else
            reservationDTO.setImgUrl(reservation.getHolidayHome().getExterior().get(0).getLink());
        reservationDTO.setDurationInHours(reservation.getDurationInHours());
        reservationDTO.setPrice(reservation.getPrice());
        

        return reservationDTO;
    }

    private ReservationDTO boatReservationDTO(BoatReservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setName(reservation.getBoat().getName());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setAddress(reservation.getBoat().getAddress().toString());
        reservationDTO.setMark(reservation.getMark());
        if(reservation.getBoat().getExterior().isEmpty())
            reservationDTO.setImgUrl("");
        else
            reservationDTO.setImgUrl(reservation.getBoat().getExterior().get(0).getLink());
        reservationDTO.setDurationInHours(reservation.getDurationInHours());
        reservationDTO.setPrice(reservation.getPrice());

        return reservationDTO;
    }

    private ReservationDTO adventureReservationDTO(AdventureReservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setName(reservation.getAdventure().getName());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setAddress(reservation.getAdventure().getAddress().toString());
        reservationDTO.setMark(reservation.getMark());
        if(reservation.getAdventure().getPictures().isEmpty())
            reservationDTO.setImgUrl("");
        else
            reservationDTO.setImgUrl(reservation.getAdventure().getPictures().get(0).getLink());
        reservationDTO.setDurationInHours(reservation.getDurationInHours());
        reservationDTO.setPrice(reservation.getPrice());

        return reservationDTO;
    }

}
