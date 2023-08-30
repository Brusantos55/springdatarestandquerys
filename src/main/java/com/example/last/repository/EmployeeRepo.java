package com.example.last.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Employee;

import java.util.List;


//@RepositoryRestResource(collectionResourceRel = "empleados", path = "empleados") genera los endpoints inecesario
public interface EmployeeRepo extends 
    PagingAndSortingRepository<Employee, Long>{ // porporciona consultas para paginar y ordenar y extiende CrudRepo
    
    //List<Empleado> findByName(@Param("name") String name);   consulta personalizada que compara con el nombre
    
    /**
     * 
     * devuelve los empleados admin de la base de datos
     * @return a list of employees that are admin
     * @see https://www.baeldung.com/spring-data-jpa-query
     */
    @Query("SELECT e FROM Employee e WHERE e.isAdmin = 1")
    List<Employee> findAllAdminEmployees();

    /**
     * 
     * devuelve los empleados CEO
     * @return a list of employees that are CEO
     */
    @Query( nativeQuery = true,  value = //countQuery = "SELECT count(*) FROM employee",
         "SELECT * FROM employee e, position p where e.position = p.id_position and p.position = 0")
    List<Employee> findAllCEO();

    @Query(value = "SELECT u FROM Employee u ORDER BY id")
    Page<Employee> findAllEmployeeWithPagination(Pageable pageable);

    
} 
//HATEOAS hiperlink as the enginee of aplication service
