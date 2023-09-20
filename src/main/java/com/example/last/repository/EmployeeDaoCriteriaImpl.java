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

/**
 * repo con consultas mediante criteria
 */
@Repository
@RequiredArgsConstructor
public class EmployeeDaoCriteriaImpl implements IEmployeeDaoCriteria{
    
    private final EntityManager em;

    public List<Employee> filterEmployees(EmployeeFilter filter) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    
        Root<Employee> employee = cq.from(Employee.class);
        
        cq = buildQuery(employee, cq, cb, filter);
    
        return em.createQuery(cq).getResultList();
    }

    private CriteriaQuery<Employee> buildQuery(Root<Employee> employee, CriteriaQuery<Employee> cq, CriteriaBuilder cb, EmployeeFilter filter){
        
        List<Predicate> predicates = buildPredicates(employee, cb, filter);

        if (filter.getSortBy() != null) {

            if(filter.isSortDesc()){
                return cq.where(predicates.toArray(new Predicate[0])).orderBy(cb.desc(employee.get(filter.getSortBy())));

            } else {
                return cq.where(predicates.toArray(new Predicate[0])).orderBy(cb.asc(employee.get(filter.getSortBy())));
            
            }
        } else {
            return cq.where(predicates.toArray(new Predicate[0]));
        }
    }

    private List<Predicate> buildPredicates(Root<Employee> employee, CriteriaBuilder cb, EmployeeFilter filter){
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(cb.equal(employee.get("name"), filter.getName()));
        }

        if (filter.getBirthdate() != null && filter.getBirthdate2() != null){

            predicates.add(cb.greaterThanOrEqualTo(employee.get("birthdate"), filter.getBirthdate()));
            predicates.add(cb.lessThanOrEqualTo(employee.get("birthdate"), filter.getBirthdate2()));

        } else if (filter.getBirthdate() != null){

            predicates.add(cb.equal(employee.get("birthdate"), filter.getBirthdate()));
        }
        
        if (filter.getLastLogin() != null) {
            predicates.add(cb.equal(employee.get("lastLogin"), filter.getLastLogin()));
        }

        if (filter.getIsAdmin() != null) {
            predicates.add(cb.equal(employee.get("isAdmin"), filter.getIsAdmin()));
        }

        if (filter.getTimeLogged() != null) {
            predicates.add(cb.equal(employee.get("timeLogged"), filter.getTimeLogged()));
        }

        if (filter.getPosition() != null) {
            predicates.add(cb.equal(employee.get("position").get("idPosition"), filter.getPosition()));
        }

        if (filter.getAddress() != null) {
            predicates.add(cb.equal(employee.get("address").get("idAddress"), filter.getAddress()));
        }

        return predicates;
    }

}
