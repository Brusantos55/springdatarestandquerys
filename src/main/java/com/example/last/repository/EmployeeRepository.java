package com.example.last.repository;

import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.last.entity.Employee;
import com.example.last.entity.QEmployee;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

//set the projection as the default view on collection results  @RepositoryRestResource(excerptProjection = EmpProjection.class)
/**
 * repo con querydls por defecto
 * ejemplo consulta: ?name=contains(a)&name=or(contains(o))&sort=name,desc&page=1&size=2
 */
public interface EmployeeRepository extends 
    PagingAndSortingRepository<Employee, Long>,
                QuerydslPredicateExecutor<Employee>, 
                QuerydslBinderCustomizer<QEmployee>{

    @Override
    public default void customize(
      // los numeros solo se pueden igualar
      QuerydslBindings bindings, QEmployee root) {

        bindings.bind(String.class).first(
            (SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase); 

        bindings.bind(root.name).all(ExpressionProviderFactory::getPredicate); 
        
      }
} 