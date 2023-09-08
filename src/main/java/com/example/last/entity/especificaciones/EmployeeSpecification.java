package com.example.last.entity.especificaciones;
// package com.example.last.entity.filters;

// import javax.persistence.criteria.CriteriaBuilder;
// import javax.persistence.criteria.CriteriaQuery;
// import javax.persistence.criteria.Predicate;
// import javax.persistence.criteria.Root;

// import com.example.last.entity.Employee;
// import com.example.last.repository.Specification;


    //https://medium.com/@cmmapada/advanced-search-and-filtering-using-spring-data-jpa-specification-and-criteria-api-b6e8f891f2bf


// public class EmployeeSpecification implements  
//              Specification<Employee> {

//     private final SearchCriteria searchCriteria;

//     public EmployeeSpecification(final SearchCriteria searchCriteria){
//         super(); this.searchCriteria = searchCriteria;
//     }

//     @Override
//     public Predicate toPredicate(Root<Employee> root, CriteriaQuery query, CriteriaBuilder cb){

//         String strToSearch = searchCriteria.getValue().toString().toLowerCase();
     
//         switch(SearchOperations.getSimpleOperation(
//                         searchCriteria.getOperation())){
            
//             case CONTAINS:
//                 return cb.like(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          "%" + strToSearch + "%");
                         
//             case DOES_NOT_CONTAIN:
//                 return cb.notLike(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          "%" + strToSearch + "%");
            
//             case EQUAL:
//                 return cb.equal(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          strToSearch);

//             case NOT_EQUAL:
//                 return cb.equal(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          strToSearch);

//             case BEGINS_WITH:
//                 return cb.like(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          "%" + strToSearch);

//             case DOES_NOT_BEGIN_WITH:
//                 return cb.notLike(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          "%" + strToSearch);

//             case ENDS_WITH:
//                 return cb.like(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          strToSearch + "%");

//             case DOES_NOT_END_WITH:
//                 return cb.notLike(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          strToSearch + "%");

//             case NUL:
//                 return cb.isNull(cb.lower(root
//                          .get(searchCriteria.getFilterKey())));

//             case NOT_NULL:
//                 return cb.isNotNull(cb.lower(root
//                          .get(searchCriteria.getFilterKey())));

//             case GREATER_THAN:
//                 return cb.greaterThan(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          "%" + strToSearch + "%");

//             case GREATER_THAN_EQUAL:
//                 return cb.greaterThanOrEqualTo(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          "%" + strToSearch + "%");

//             case LESS_THAN:
//                 return cb.lessThan(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          "%" + strToSearch + "%");

//             case LESS_THAN_EQUAL:
//                 return cb.lessThanOrEqualTo(cb.lower(root
//                          .get(searchCriteria.getFilterKey())), 
//                          strToSearch);

//             default: return null;
//         }
//     }
// }
