package com.example.last.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.last.entity.APIResponse;
import com.example.last.entity.Employee;
import com.example.last.entity.filters.EmployeeFilter;
import com.example.last.service.EmployeeService;
import com.example.last.exceptions.RespStaException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/customEmployees") //formato recomendado url/uri REST.
@RequiredArgsConstructor            //cant parse json | propiedad para que todo lo que recibe y envia sea json
public class EmployeeController {

    private final EmployeeService employeeService;
    
    @GetMapping("{field}")
    @Operation(summary = "Retrives all Employees sorted by field",
			description = "Retrives all Employees sorted by the field especified on the path",
			responses = {@ApiResponse(responseCode = "200", description = "Employees retrived successfully")})
    public Iterable<Employee> sortEmployeesByField(@PathVariable String field){
        return employeeService.sortEmployeesByField(field);
    }

    @GetMapping("/pagination/{page}/{pageSize}")
    @Operation(summary = "Retrives all Employees paged",
			description = "Retrives all Employees paged as especified on the path",
			responses = {@ApiResponse(responseCode = "200", description = "Employees retrived successfully")})
    public Iterable<Employee> pagedEmployees(@PathVariable int page, @PathVariable int pageSize){
        return employeeService.pagedEmployees(page, pageSize);
    }

    @GetMapping("/pagination/{page}/{pageSize}/{field}")
    @Operation(summary = "Retrives all Employees paged and sorted",
			description = "Retrives all Employees paged and sorted as especified on the path",
			responses = {@ApiResponse(responseCode = "200", description = "Employees retrived successfully")})
    public Iterable<Employee> pagedAndSortedEmployees(@PathVariable int page, @PathVariable int pageSize, @PathVariable String field){
        return employeeService.pagedAndSortedEmployees(page, pageSize, field);
    }

    /**
     * 
     * Saves the number of employees specified in the path
     * @param quantity numero de elementos que se van a intentar crear
     * @return devuelve un mensaje segun se desarrolle el proceso
     */
    @PostMapping("{quantity}")
    @Operation(summary = "Saves up to 5 Employees",
			description = "Saves the number of employees specified in the path",
			responses = {@ApiResponse(responseCode = "200", 
            description = "Employees saved successfully")})
    public String getPlayerById(@PathVariable int quantity){
        return employeeService.createRamdomEmployees(quantity);
    }

    @PostMapping("/ceo")
    @Operation(summary = "Retrives all CEO Employees",
			description = "Retrives all CEO employees",
			responses = {@ApiResponse(responseCode = "200", 
            description = "Employees retrived successfully")})
    public List<Employee> getAllCEOEmployees(){
        return employeeService.findAllCEO();
    }

    @PostMapping("/admin")
    @Operation(summary = "Retrives all Admin Employees",
			description = "Retrives all Admin employees sorted as especified on the body sort object",
			responses = {@ApiResponse(responseCode = "200", description = "Employees retrived successfully")})
    public APIResponse<List<Employee>> getAllAdminEmployees(){
        List<Employee> employees = employeeService.findAllAdminEmployees();
        APIResponse<List<Employee>> resp = new APIResponse<>(employees);
        resp.add(linkTo(methodOn(EmployeeController.class).getAllAdminEmployees()).withSelfRel());
        resp.add(linkTo(methodOn(EmployeeController.class).pagedAndSortedEmployees(0,2,"name")).withRel("el bueno"));
        return resp;
    }

    @DeleteMapping()
    @Operation(summary = "error",
			description = "error")
    public void error(){
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Error!!", new RespStaException());
    }

    @PostMapping("/filter")
    @Operation(summary = "Filter Employees",
			description = "Retrives all employees that match the filter",
			responses = {@ApiResponse(responseCode = "200", 
            description = "Employees retrived successfully")})
    public List<Employee> filterEmployees(@RequestBody EmployeeFilter filter){
        return employeeService.filterEmployees(filter);
    }

} //HATEOAS hiperlink as the enginee of aplication service
