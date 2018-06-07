package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Lesson;
import webdev.models.Module;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
	@Query("SELECT l FROM Lesson l WHERE l.module=:module")
    Iterable<Lesson> findLessonByModule(@Param("module") Module m);
}