package com.example.last.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.last.auxiliary.Predicate;
import com.example.last.entity.Employee;
import com.example.last.repository.EmployeeDao;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dls/employees")
public class EmployeeDlsController {

    private final EmployeeDao employeeRepo;

    /**
     * controlador de querydls personalizado para comparar numeros
     * @param search string con el nombre del campo a filtrar y la operacion( : igualdad, <, >)
     * @return el resultado del filtro
     */
    @GetMapping("{search}")
    public Iterable<Employee> search(@PathVariable String search) {
        Predicate builder = new Predicate();

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
