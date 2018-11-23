package webdev.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Course;
import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceQuestionService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
	
	@PostMapping("/api/exam/{examId}/question/choice")
	public MultipleChoiceExamQuestion createMultipleChoiceExamQuestion(@PathVariable("examId") int examId, @RequestBody MultipleChoiceExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newQuestion.setExam(exam);
			return multipleChoiceQuestionRepository.save(newQuestion);
		}
		return null;
	}
	
	@PostMapping("/api/question/choice/{questionId}/save")
	public MultipleChoiceExamQuestion updateMultipleChoiceExamQuestion(@PathVariable("questionId") int questionId, @RequestBody MultipleChoiceExamQuestion newQuestion) {
		Optional<MultipleChoiceExamQuestion> data = multipleChoiceQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			MultipleChoiceExamQuestion question = data.get();
			Course course = question.getExam().getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			question.setDescription(newQuestion.getDescription());
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			question.setOptions(newQuestion.getOptions());
			question.setCorrectOption(newQuestion.getCorrectOption());
			return multipleChoiceQuestionRepository.save(question);
		}
		return null;
	}
}
