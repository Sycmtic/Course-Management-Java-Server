package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksQuestionRepository extends CrudRepository<FillInTheBlanksExamQuestion, Integer> {

}
