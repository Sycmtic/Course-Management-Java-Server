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
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueFalseQuestionService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseQuestionRepository;
	
	@PostMapping("/api/exam/{examId}/question/truefalse")
	public TrueOrFalseExamQuestion createTrueOrFalseExamQuestion(@PathVariable("examId") int examId, @RequestBody TrueOrFalseExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newQuestion.setExam(exam);
			return trueFalseQuestionRepository.save(newQuestion);
		}
		return null;
	}
	
	@PostMapping("/api/question/truefalse/{questionId}/save")
	public TrueOrFalseExamQuestion updateTrueOrFalseExamQuestion(@PathVariable("questionId") int questionId, @RequestBody TrueOrFalseExamQuestion newQuestion) {
		Optional<TrueOrFalseExamQuestion> data = trueFalseQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			TrueOrFalseExamQuestion question = data.get();
			Course course = question.getExam().getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			question.setDescription(newQuestion.getDescription());
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			question.setTrue(newQuestion.isTrue());
			return trueFalseQuestionRepository.save(question);
		}
		return null;
	}
}
