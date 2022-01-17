package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.Complaint;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ReservationService reservationService;

    public ComplaintService(ComplaintRepository complaintRepository, ReservationService reservationService) {
        this.complaintRepository = complaintRepository;
        this.reservationService = reservationService;
    }

    public void createComplaint(Long reservationId, String content){
        if(content.isBlank()) throw new NotAllowedException();
        Reservation reservation = reservationService.findByID(reservationId);
        if(reservation.getComplaint()!=null) throw new NotAllowedException();
        Complaint complaint = new Complaint(reservation,content);
        reservation.setComplaint(complaint);
        complaintRepository.save(complaint);
    }
}
