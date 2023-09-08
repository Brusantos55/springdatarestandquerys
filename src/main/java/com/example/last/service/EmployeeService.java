package com.example.last.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.last.entity.Employee;
import com.example.last.entity.EmployeeProjectFK;
import com.example.last.entity.Project;
import com.example.last.entity.especificaciones.EmpSpec;
import com.example.last.entity.filters.EmployeeFilter;
import com.example.last.repository.EmpProjectFKRepo;
import com.example.last.repository.EmployeeRepo;
import com.example.last.repository.EmployeeRepoCustom;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import com.example.last.exceptions.NameRepeatedException;

@Service
@CommonsLog
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeRepoCustom employeeRepoCustom;
    private final EmpProjectFKRepo fkRepo;
  
    /**
     * 
     * crea hasta 6 empleados con informacion semi-aleatoria
     * @param n numero de elementos que se van a intentar crear
     * @return devuelve un mensaje segun se desarrolle el proceso
     * @see https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
     * @see https://www.baeldung.com/spring-boot-logging https://www.digitalocean.com/community/tutorials/logger-in-java-logging-example#logger-in-java
     */
    @Transactional
    public String createRamdomEmployees(int n){
        log.trace("transaccion iniciada");
        String salida = "nuevos Empleados guardados";
        int i;

        for( i=0 ; i<n ; i++ ){

            Employee emp = new Employee();

            emp.setName(customName(i));
            emp.setIsAdmin((i%2==0)?true:false);
            emp.setLastLogin(ZonedDateTime.now());
            emp.setTimeLogged(new Random().nextDouble(2453)*i);

            log.info("creado elemento "+i);

            employeeRepo.save(emp);

            if(i>4) {
                log.warn("Elemento repetido, ROLLING BACK!");
                throw new NameRepeatedException(emp.getName());
            } 
        }

        salida=i+" "+salida;

        return salida;
    }

    private String customName(int n){

        String name="br1";

        switch (n) {
            case 1:
                name="Jose";
                break;
            case 2:
                name="Antonio";
                break;
            case 3:
                name="Tomas";
                break;
            case 4:
                name="Juan";
                break;
        }

        return name;
    }

    public List<Employee> filterEmployees(EmployeeFilter filter){
        return employeeRepoCustom.filterEmployees(filter);
    }

    public List<Employee> findAllCEO(){
        return employeeRepo.findAllCEO();
    }
    
    public List<Employee> findAllAdminEmployees(){
        return employeeRepo.findAllAdminEmployees();
    }
    
    public Iterable<Employee> sortEmployeesByField(String field){
        return employeeRepo.findAll(Sort.by(field).ascending());
    }
    
    public Page<Employee> pagedEmployees(int page, int pageSize){
        return employeeRepo.findAll(PageRequest.of(page, pageSize));
    }
    
    public Page<Employee> pagedAndSortedEmployees(int page, int pageSize, String field){
        return employeeRepo.findAll(PageRequest.of(page, pageSize).withSort(Sort.by(field)));
    }
    
    public Page<Employee> filterEmployeesSp(EmployeeFilter filter){
        return employeeRepo.findAll(EmpSpec.filtrado(filter), PageRequest.of(filter.getPage(), filter.getPageSize()));
    }

    public Iterable<Employee> filterByExample(Employee ex){ 
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return employeeRepo.findAll(Example.of(ex, matcher));
    }

    public List<Project> findProjectsByEmpId(Long id){
        Employee emp = employeeRepo.findById(id).orElse(null);
        List<EmployeeProjectFK> fks = fkRepo.findAllByEmployee(emp);
        return fks.stream().map(fk -> fk.getProject()).collect(Collectors.toList());//projects;List<Project> projects = 
    }

    public Iterable<Employee> findAllByWebQuerydsl(Predicate predicate){
        return employeeRepo.findAll(predicate);
    }
}
