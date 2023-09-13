package com.example.last.entity.especificaciones;

import com.example.last.entity.Project;
import com.example.last.entity.enums.ProjectState;
import com.example.last.entity.enums.ProjectType;
import com.example.last.entity.filters.ProjectFilter;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecifications {
   
    /**
     * especificaiones que comparan la igualdad del parametro del filtro con las instancias de la bd
     */
     public static Specification<Project> spbase(Long id) {
        return (project, cq, cb) -> cb.gt(project.get("id"), id);
    }

    public static Specification<Project> spId(Long id) {
        return (project, cq, cb) -> cb.equal(project.get("id"), id);
    }
    
    public static Specification<Project> spType(ProjectType type) {
        return (project, cq, cb) -> cb.equal(project.get("projectType"), type);
    }

    public static Specification<Project> spState (ProjectState state) {
        return (project, cq, cb) -> cb.equal(project.get("projectState"), state);
    }

    /**
     * especificacion que agrupa el resto
     * @param filter
     * @return una especificacion agrupando las demas en el caso de que el filtro sea valido para ellas
     */
    public static Specification<Project> filtrado(ProjectFilter filter){ 
        
        Specification<Project> spec = spbase(0L);

        if(filter.getId() != null) spec = spec.and(spId(filter.getId()));

        if(filter.getProjectType() != null) spec = spec.and(spType(filter.getProjectType()));

        if(filter.getProjectState() != null) spec = spec.and(spState(filter.getProjectState()));

        return spec;
    }

}
