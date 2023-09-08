package com.example.last.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import com.example.last.entity.filters.SearchCriteria;
import com.example.last.service.EmpPredicateService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

public class EmpPredicate {
    private List<SearchCriteria> params;

    public EmpPredicate() {
        params = new ArrayList<>();
    }
    public EmpPredicate(List<SearchCriteria> paramsC) {
        params = paramsC;
    }
    public EmpPredicate(SearchCriteria parm){
        params = new ArrayList<>();
        params.add(parm);
    }


    public EmpPredicate with(
      String key, String operation, Object value) {
  
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.isEmpty()) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {

            EmpPredicateService predicate = new EmpPredicateService(param);

            return predicate.getPredicate();

        }).filter(Objects::nonNull).collect(Collectors.toList());
        
        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }        
        return result;
    }
}

