package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Exam;
import webdev.models.Topic;

public interface ExamRepository extends CrudRepository<Exam, Integer> {
	@Query("SELECT e FROM Exam e WHERE e.topic=:topic")
    Iterable<Exam> findExamByTopic(@Param("topic") Topic t);
}
