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
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksQuestionService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	FillInTheBlanksQuestionRepository fillInTheBlanksQuestionRepository;
	
	@PostMapping("/api/exam/{examId}/question/blanks")
	public FillInTheBlanksExamQuestion createFillInTheBlanksExamQuestion(@PathVariable("examId") int examId, @RequestBody FillInTheBlanksExamQuestion newQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newQuestion.setExam(exam);
			return fillInTheBlanksQuestionRepository.save(newQuestion);
		}
		return null;
	}
	
	@PostMapping("/api/question/blanks/{questionId}/save")
	public FillInTheBlanksExamQuestion updateMultipleChoiceExamQuestion(@PathVariable("questionId") int questionId, @RequestBody FillInTheBlanksExamQuestion newQuestion) {
		Optional<FillInTheBlanksExamQuestion> data = fillInTheBlanksQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			FillInTheBlanksExamQuestion question = data.get();
			Course course = question.getExam().getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			question.setDescription(newQuestion.getDescription());
			question.setTitle(newQuestion.getTitle());
			question.setPoints(newQuestion.getPoints());
			question.setBlanks(newQuestion.getBlanks());
			return fillInTheBlanksQuestionRepository.save(question);
		}
		return null;
	}
}