package com.example.last.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.last.entity.Employee;
import com.example.last.entity.EmployeeProjectFK;
import com.example.last.entity.Project;
import com.example.last.exception.NameRepeatedException;
import com.example.last.filter.EmployeeFilter;
import com.example.last.repository.EmployeeProjectFKRepository;
import com.example.last.repository.EmployeeRepository;
import com.example.last.repository.EmployeeCriteriaRepository;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final EmployeeCriteriaRepository employeeRepoCustom;
    private final EmployeeProjectFKRepository fkRepo;
  
    /**
     * 
     * crea hasta 6 empleados con informacion semi-aleatoria
     * @param n numero de elementos que se van a intentar crear
     * @return devuelve un mensaje segun se desarrolle el proceso
     */
    @Transactional
    public String createRamdomEmployees(int n){
        log.trace("transaccion iniciada");
        String salida = "nuevos Empleados guardados";
        int i;

        for( i=0 ; i<n ; i++ ){

            Employee emp = new Employee();

            emp.setName(customName(i));
            emp.setIsAdmin((i%2==0));
            emp.setLastLogin(ZonedDateTime.now());
            // no necesito re-usar ese Random #ignoreSonarLint
            emp.setTimeLogged(new Random().nextInt(2453)*(double)i);

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

        String name;
        
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
            default:
            	name="br1";
        }

        return name;
    }

    /** ejemplos paging and sorting */
    public Iterable<Employee> sortEmployeesByField(String field){
        return employeeRepo.findAll(Sort.by(field).ascending());
    }
    
    public Page<Employee> pagedEmployees(int page, int pageSize){
        return employeeRepo.findAll(PageRequest.of(page, pageSize));
    }
    
    public Page<Employee> pagedAndSortedEmployees(int page, int pageSize, String field){
        return employeeRepo.findAll(PageRequest.of(page, pageSize).withSort(Sort.by(field)));
    }
    
    /** querydls */
    public Iterable<Employee> findAllByWebQuerydsl(Predicate predicate){
        return employeeRepo.findAll(predicate);
    }
    
    /**
     * filtro con criteria
     */
    public List<Employee> filterEmployees(EmployeeFilter filter){
        return employeeRepoCustom.filterEmployees(filter);
    }

    /**
     * consulta dos repositorios
     */
    public List<Project> findProjectsByEmpId(Long id){
        Employee emp = employeeRepo.findById(id).orElse(null);
        List<EmployeeProjectFK> fks = fkRepo.findAllByEmployee(emp);
        return fks.stream().map(fk -> fk.getProject()).collect(Collectors.toList());
    }
}
