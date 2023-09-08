package com.example.last.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.last.entity.EmpPredicate;
import com.example.last.entity.Employee;
import com.example.last.repository.EmployeeRepo;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("employees")
public class EmpdlsController {

    private final EmployeeRepo employeeRepo;

    @ResponseBody
    @GetMapping("{search}")
    public Iterable<Employee> search(@PathVariable String search) {
        EmpPredicate builder = new EmpPredicate();

        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        BooleanExpression exp = builder.build();
        return employeeRepo.findAll(exp);
    }
}
