package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.TrueOrFalseExamQuestion;

public interface TrueFalseQuestionRepository extends CrudRepository<TrueOrFalseExamQuestion, Integer> {

}
