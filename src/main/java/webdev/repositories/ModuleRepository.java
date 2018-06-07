package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Course;
import webdev.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer>{
	@Query("SELECT m FROM Module m WHERE m.course=:course")
    Iterable<Module> findModuleByCourse(@Param("course") Course c);
}