package com.project.fishingbookingback.service;

import com.project.fishingbookingback.dto.request.NewComplaintDTO;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.model.Complaint;
import com.project.fishingbookingback.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository repository;
    private final ReservationService reservationService;

    public ComplaintService(ComplaintRepository repository, ReservationService reservationService) {
        this.repository = repository;
        this.reservationService = reservationService;
    }

    public Complaint findByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Complaint.class.getSimpleName()));
    }

    public Complaint addComplaint(NewComplaintDTO dto) {
        return repository.save(
                new Complaint(
                reservationService.findById(dto.getReservationId()),
                dto.getContent()
        ));
    }

    public void deleteComplaint(Long id_complaint) {
        findByID(id_complaint);
        repository.deleteById(id_complaint);
    }
}
