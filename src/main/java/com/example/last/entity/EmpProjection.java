package com.example.last.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import com.example.last.entity.enums.PositionEnum;

@Projection(types = {Employee.class}, name = "empProjection")
@RepositoryRestResource
public interface EmpProjection { // http://localhost:8080/employees/1?projection=empProjection

    String getId(); 

    String getName();

    String getIsAdmin(); 

    @Value("#{target.getPosition().getPositionType()}")
    PositionEnum getPosition();

    Address getAddress();
}
//https://stackoverflow.com/questions/36701358/how-to-use-projection-interfaces-with-pagination-in-spring-data-jpa
//