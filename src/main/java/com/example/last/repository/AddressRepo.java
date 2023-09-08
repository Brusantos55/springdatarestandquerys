package com.example.last.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Address;


/**
 * 
 * endpoints autogenerados requieren la url del recurso que se relaciona en el body del post put y patch, pero solo el id en la url del put y patch.
 * por ejemplo si quiero cambiar la address del empleado 2 puedo hacer un patch con la url: "localhost.../employee/2" y body {"address": "localhost.../address/4"}
 */
public interface AddressRepo extends
    PagingAndSortingRepository<Address, Long>{
    
}
