package com.example.last.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.last.filter.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

/**
 * clase que utiliza searchcriteria para 
 * generar los predicados de comparacion 
 * de numeros para querydls personalizado
 */
public class PredicateBuilder {
    private List<SearchCriteria> params;

    public PredicateBuilder() {
        params = new ArrayList<>();
    }
    public PredicateBuilder(List<SearchCriteria> paramsC) {
        params = paramsC;
    }
    public PredicateBuilder(SearchCriteria parm){
        params = new ArrayList<>();
        params.add(parm);
    }


    public PredicateBuilder with(
      String key, String operation, Object value) {
  
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.isEmpty()) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {

            Predicate predicate = new Predicate(param);

            return predicate.getPredicate();

        }).filter(Objects::nonNull).collect(Collectors.toList());
        
        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }        
        return result;
    }
}

