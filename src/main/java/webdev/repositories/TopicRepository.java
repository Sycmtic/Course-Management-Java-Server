package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Lesson;
import webdev.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
	@Query("SELECT t FROM Topic t WHERE t.lesson=:lesson")
    Iterable<Topic> findTopicByLesson(@Param("lesson") Lesson l);
}