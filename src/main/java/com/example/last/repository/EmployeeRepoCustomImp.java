package com.example.last.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.last.entity.Employee;
import com.example.last.entity.filters.EmployeeFilter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeRepoCustomImp implements EmployeeRepoCustom{
    
    private final EntityManager em;

    public List<Employee> filterEmployees(EmployeeFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    
        Root<Employee> employee = cq.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getId() != null) {
            predicates.add(cb.equal(employee.get("idEmp"), filter.getId()));
        }
        if (filter.getName() != null) {
            predicates.add(cb.equal(employee.get("name"), filter.getName()));
        }
        if (filter.getBirthdate() != null) {
            predicates.add(cb.equal(employee.get("birthdate"), filter.getBirthdate()));
        }
        if (filter.getLastLogin() != null) {
            predicates.add(cb.equal(employee.get("lastLogin"), filter.getLastLogin()));
        }
        if (filter.getIsAdmin() != null) {
            predicates.add(cb.equal(employee.get("isAdmin"), filter.getIsAdmin()));
        }
        if (filter.getTimeLogged() >= 0.0) {
            predicates.add(cb.equal(employee.get("timeLogged"), filter.getTimeLogged()));
        }
        if (filter.getProjects() != null) {
            predicates.add(cb.equal(employee.get("projects"), filter.getProjects()));
        }
        if (filter.getPosition() != null) {
            predicates.add(cb.equal(employee.get("position"), filter.getPosition()));
        }
        if (filter.getAddress() != null) {
            predicates.add(cb.equal(employee.get("address"), filter.getAddress()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
    
        return em.createQuery(cq).getResultList();
    }

}
