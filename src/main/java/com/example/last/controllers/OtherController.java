package com.example.last.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.last.entity.APIResponse;
import com.example.last.entity.Address;
import com.example.last.entity.Employee;
import com.example.last.entity.Position;
import com.example.last.entity.Project;
import com.example.last.entity.filters.ProjectFilter;
import com.example.last.repository.AddressRepo;
import com.example.last.service.PositionService;
import com.example.last.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequiredArgsConstructor
public class OtherController {

    private final AddressRepo addresRepo;
    private final ProjectService projectService;
    private final PositionService positionService;

    /**
     * controllador con hateoas y responseObject
     */
    @PostMapping("/address/pageAndSort/{page}/{pageSize}/{field}")
    public APIResponse<Page<Address>> filterByExample(@PathVariable int page, 
            @PathVariable int pageSize, @PathVariable String field){

        Page<Address> addresses = addresRepo.findAll(
                            PageRequest.of(page, pageSize)
                            .withSort(Sort.by(field)));

        APIResponse<Page<Address>> resp = new APIResponse<>(addresses);

        resp.add(linkTo(methodOn(OtherController.class)
        .filterByExample(page,pageSize,field)).withSelfRel());

        return resp;
    }

    /**
     * controllador para filtrar mediante especificaciones
     */
    @PostMapping("/project/filterSpecications")
    @Operation(summary = "Filter Projects",
			description = "Retrives all rojects that match the spec filter",
			responses = {@ApiResponse(responseCode = "200", 
            description = "Projects retrived successfully")})
    public Page<Project> filterSpAndPaged(@RequestBody ProjectFilter filter){
        return projectService.filterProjectsSp(filter);
    }

    /**
     * controlador de queryByExample
     */
    @PostMapping("/position/queryByExample")
    public Iterable<Position> filterByExample(@RequestBody Position ex){
        return positionService.filterByExample(ex);
    }
    
}
