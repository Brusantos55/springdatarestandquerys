package com.example.last.response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.last.entity.Address;
import com.example.last.entity.Employee;
import com.example.last.enumeration.PositionEnum;

// /employees/{id}?projection=empProjection
@Projection(types = {Employee.class}, name = "empProjection")
public interface EmployeeProjection { 

    String getId(); 

    String getName();

    String getIsAdmin(); 

    @Value("#{target.getPosition().getPositionType()}")
    PositionEnum getPosition();

    Address getAddress();
}