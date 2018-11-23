package webdev.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.*;
import webdev.repositories.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayQuestionService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	EssayQuestionRepository essayQuestionRepository;
	
	@PostMapping("/api/exam/{examId}/question/essay")
	public EssayExamQuestion createEssayExamQuestion(@PathVariable("examId") int examId, @RequestBody EssayExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newQuestion.setExam(exam);
			return essayQuestionRepository.save(newQuestion);
		}
		return null;
	}
	
	@PostMapping("/api/question/essay/{questionId}/save")
	public EssayExamQuestion updateEssayExamQuestion(@PathVariable("questionId") int questionId, @RequestBody EssayExamQuestion newQuestion) {
		Optional<EssayExamQuestion> data = essayQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			EssayExamQuestion question = data.get();
			Course course = question.getExam().getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			question.setDescription(newQuestion.getDescription());
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			return essayQuestionRepository.save(question);
		}
		return null;
	}
}
