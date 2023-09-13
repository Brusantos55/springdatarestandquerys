package com.example.last.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Address;


/**
 * repo con las querys personalizadas native y normal
 * 
 * endpoints autogenerados requieren la url del recurso que se relaciona en el body del post put y patch, pero solo el id en la url del put y patch.
 * por ejemplo si quiero cambiar la address del empleado 2 puedo hacer un patch con la url: "localhost.../employee/2" y body {"address": "localhost.../address/4"}
 */
public interface AddressRepo extends
    PagingAndSortingRepository<Address, Long>{
    
    @Query("SELECT a FROM Address a WHERE a.streetType = 0")
    List<Address> findAllAvenues();

    // @Query("SELECT e FROM Employee e WHERE e.address = 
    // (SELECT a FROM Address a where a.streetType = 0)") 
    // List<Employee> findAllEmpWhoLivesInAvenues(); 

    @Query( nativeQuery = true,  value =                // esto quizas es mejor seleccionar todos 
    "SELECT * FROM address a where mod(zipCode,2)=0;") // y hacer la operacion en el servicio?
    List<Address> findAllEvenZip();

    Page<Address> findAll(Pageable pageable);
}
