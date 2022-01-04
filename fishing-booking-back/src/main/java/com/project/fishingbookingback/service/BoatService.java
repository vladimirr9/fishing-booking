package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.Boat;
import com.project.fishingbookingback.repository.BoatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatService {

    private final BoatRepository boatRepository;

    public BoatService(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public List<Boat> getAll(){
        return boatRepository.findAll();
    }
}
