package com.example.last.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Address;

public interface AddressRepo extends
    PagingAndSortingRepository<Address, Long>{
    
}
