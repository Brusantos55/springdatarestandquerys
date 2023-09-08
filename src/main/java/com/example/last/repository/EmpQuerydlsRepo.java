// package com.example.last.repository;

// import javax.transaction.Transactional;

// import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.querydsl.QuerydslPredicateExecutor;
// import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
// import org.springframework.data.querydsl.binding.QuerydslBindings;
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.http.MediaType;

// import com.example.last.entity.QEmployee;
// import com.example.last.entity.Employee;
// import com.querydsl.core.BooleanBuilder;
// import com.querydsl.core.types.Predicate;

// @Transactional
// @RepositoryRestResource(collectionResourceRel = "querydls", path = "/querydls")
// @RequestMapping(value = "/querydls")
// public interface EmpQuerydlsRepo extends 
//         JpaRepository<Employee, String>, 
//         QuerydslPredicateExecutor<Employee>,
//         QuerydslBinderCustomizer<QEmployee> {

//     @RequestMapping(path = {"/search"}, produces = {MediaType.APPLICATION_JSON_VALUE}, method = {
//             RequestMethod.GET,
//             RequestMethod.POST
//     })

//     default Iterable<Employee> searchEmployee(@QuerydslPredicate(root = Employee.class) Predicate predicate) {
//         if (predicate == null || (BooleanBuilder.class.isAssignableFrom(predicate.getClass())
//                 && !((BooleanBuilder) predicate).hasValue())) {
//             return null;
//         } else {
//             return this.findAll(predicate);
//         }
//     }

//     @Override
//     default void customize(QuerydslBindings bindings, QEmployee root) {

//         bindings.bind(root.name).all((path, values) -> ExpressionProviderFactory.getPredicate(path, values));
//         bindings.bind(root.birthdate).all((path, values) -> ExpressionProviderFactory.getPredicate(path, values));
//     }
// }
