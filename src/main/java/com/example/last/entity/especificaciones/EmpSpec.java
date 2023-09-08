package com.example.last.entity.especificaciones;

import com.example.last.entity.Employee;
import com.example.last.entity.filters.EmployeeFilter;
import org.springframework.data.jpa.domain.Specification;

public class EmpSpec {
    
    public static Specification<Employee> spName(String name) {
        return (employee, cq, cb) -> cb.equal(employee.get("name"), name);
    }
    
    public static Specification<Employee> spBirthdate(String birthdate) {
        return (employee, cq, cb) -> cb.like(employee.get("birthdate").as(String.class), birthdate);
    }

    public static Specification<Employee> spProjectEmpId (Long id) {
        return (employee, cq, cb) -> cb.equal(employee.get("projects").get("employee").get("id"), id);
    }

    public static Specification<Employee> spProjectId (Long id){
        return (employee, cq, cb) -> cb.equal(employee.get("projects").get("project").get("id"), id);

    }

    public static Specification<Employee> spPosition (Long id) {
        return (employee, cq, cb) -> cb.equal(employee.get("position").get("id"), id);//positionType
    }

    public static Specification<Employee> filtrado(EmployeeFilter filter){ 
        
        Specification<Employee> spec = spPosition(filter.getPosition());

        if(filter.getName() != null) spec = spec.and(spName(filter.getName()));

        if(filter.getBirthdate() != null) spec = spec.and(spBirthdate(filter.getBirthdate().toString()));

        if(filter.getProject() != null) spec = spec.and(spProjectEmpId(filter.getId())).and(spProjectId(filter.getProject()));

        return spec;
    }

}
