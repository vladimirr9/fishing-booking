package com.project.fishingbookingback.service;

import com.project.fishingbookingback.model.Address;
import com.project.fishingbookingback.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddressService {
    private AddressRepository repository;

    public AddressService(AddressRepository addressRepository) {
        this.repository = addressRepository;
    }

    public Address insert(Address address) {
        Objects.requireNonNull(address);
        return repository.save(address);
    }

}
