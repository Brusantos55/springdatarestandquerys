package com.example.last.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.last.entity.APIResponse;
import com.example.last.entity.Address;
import com.example.last.repository.AddressDao;

import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressDao addresRepo;

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

        resp.add(linkTo(methodOn(AddressController.class)
        .filterByExample(page,pageSize,field)).withSelfRel());

        return resp;
    }
    
}
