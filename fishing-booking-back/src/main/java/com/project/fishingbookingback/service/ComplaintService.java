package com.project.fishingbookingback.service;

import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.NotAllowedException;
import com.project.fishingbookingback.model.Complaint;
import com.project.fishingbookingback.model.Reservation;
import com.project.fishingbookingback.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ReservationService reservationService;
    private final EmailService emailService;

    public ComplaintService(ComplaintRepository complaintRepository, ReservationService reservationService, EmailService emailService) {
        this.complaintRepository = complaintRepository;
        this.reservationService = reservationService;
        this.emailService = emailService;
    }

    public void createComplaint(Long reservationId, String content) {
        if (content.isBlank()) throw new NotAllowedException();
        Reservation reservation = reservationService.findByID(reservationId);
        if (reservation.getComplaint() != null) throw new NotAllowedException();
        Complaint complaint = new Complaint(reservation, content);
        reservation.setComplaint(complaint);
        complaintRepository.save(complaint);
    }

    public Complaint findByID(Long id) {
        return complaintRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Complaint.class.getSimpleName()));
    }

    @Transactional
    public void answerComplaint(Long id, String message) {
        Complaint complaint = complaintRepository.findOneById(id);
        emailService.sendSimpleMessage(complaint.getReservation().getOwnerEmail(), "Complaint Resolved", message);
        emailService.sendSimpleMessage(complaint.getReservation().getClient().getEmail(), "Complaint Resolved", message);
        reservationService.answerComplaint(complaint.getReservation().getId());
    }

    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }
}
