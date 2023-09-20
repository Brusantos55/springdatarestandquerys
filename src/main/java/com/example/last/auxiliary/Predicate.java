package com.example.last.auxiliary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.last.entity.filters.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

/**
 * clase que utiliza searchcriteria para 
 * generar los predicados de comparacion 
 * de numeros para querydls personalizado
 */
public class Predicate {
    private List<SearchCriteria> params;

    public Predicate() {
        params = new ArrayList<>();
    }
    public Predicate(List<SearchCriteria> paramsC) {
        params = paramsC;
    }
    public Predicate(SearchCriteria parm){
        params = new ArrayList<>();
        params.add(parm);
    }


    public Predicate with(
      String key, String operation, Object value) {
  
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.isEmpty()) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {

            PredicateBuilder predicate = new PredicateBuilder(param);

            return predicate.getPredicate();

        }).filter(Objects::nonNull).collect(Collectors.toList());
        
        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }        
        return result;
    }
}

