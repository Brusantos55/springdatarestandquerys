package com.example.last.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import com.example.last.entity.enums.PositionEnum;

@Projection(types = {Employee.class}, name = "empProjection")
@RepositoryRestResource
public interface EmpProjection { // /employees/{id}?projection=empProjection

    String getId(); 

    String getName();

    String getIsAdmin(); 

    @Value("#{target.getPosition().getPositionType()}")
    PositionEnum getPosition();

    Address getAddress();
}