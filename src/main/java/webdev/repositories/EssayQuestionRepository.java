package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.EssayExamQuestion;

public interface EssayQuestionRepository extends CrudRepository<EssayExamQuestion, Integer> {

}
