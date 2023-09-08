package com.example.last.repository;

import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.example.last.entity.Employee;
import com.example.last.entity.QEmployee;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import java.util.List;

//set the projection as the default view on collection results  @RepositoryRestResource(excerptProjection = EmpProjection.class)
public interface EmployeeRepo extends 
    PagingAndSortingRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee>,
            QueryByExampleExecutor<Employee>
                ,QuerydslPredicateExecutor<Employee>, 
                QuerydslBinderCustomizer<QEmployee>{

    @Query("SELECT e FROM Employee e WHERE e.isAdmin = 1")
    List<Employee> findAllAdminEmployees();

    @Query( nativeQuery = true,  value =
         "SELECT * FROM employee e, position p where e.position = p.id and p.position_type = 0")
    List<Employee> findAllCEO();

    @Query(value = "SELECT u FROM Employee u ORDER BY id")
    Page<Employee> findAllEmployeeWithPagination(Pageable pageable);

    @Override
    public default void customize(
      QuerydslBindings bindings, QEmployee root) {// ?name=contains(a)&name=or(contains(o))&sort=name,desc&page=1&size=2
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);      
        bindings.bind(root.name).all((path, values) -> ExpressionProviderFactory.getPredicate(path, values)); // los numeros solo se pueden igualar
        // bindings.bind(root.position.positionType).all((path, values) -> ExpressionProviderFactory.getPredicate(path, values)); sin este bind funciona http://localhost:8080/employees?position.salary=CEO
        // bindings.bind(root.birthdate).all((path, values) -> ExpressionProviderFactory.getPredicate(path, values));
        // bindings.bind(root.timeLogged).all((path, values) -> ExpressionProviderFactory.getPredicate(path, values));
        //bindings.excluding(root.timeLogged);

       // bindings.bind(Long.class). investigar de bindings
      }
} 