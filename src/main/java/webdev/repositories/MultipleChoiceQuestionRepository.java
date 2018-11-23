package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.MultipleChoiceExamQuestion;

public interface MultipleChoiceQuestionRepository extends CrudRepository<MultipleChoiceExamQuestion, Integer> {

}
