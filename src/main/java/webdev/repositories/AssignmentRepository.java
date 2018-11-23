package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Assignment;
import webdev.models.Topic;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
	@Query("SELECT a FROM Assignment a WHERE a.topic=:topic")
    Iterable<Assignment> findAssignmentByTopic(@Param("topic") Topic t);
}
