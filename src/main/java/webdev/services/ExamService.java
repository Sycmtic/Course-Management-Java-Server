package webdev.services;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import webdev.models.*;
import webdev.repositories.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
	@Autowired
	FillInTheBlanksQuestionRepository fillInTheBlanksQuestionRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseQuestionRepository;
	@Autowired
	EssayQuestionRepository essayQuestionRepository;
	
	@GetMapping("/api/choice/{questionId}")
	public MultipleChoiceExamQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceExamQuestion> data = multipleChoiceQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/blanks/{questionId}")
	public FillInTheBlanksExamQuestion findFillInQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillInTheBlanksExamQuestion> data = fillInTheBlanksQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/truefalse/{questionId}")
	public TrueOrFalseExamQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueOrFalseExamQuestion> data = trueFalseQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/essay/{questionId}")
	public EssayExamQuestion findEssayQuestionById(@PathVariable("questionId") int questionId) {
		Optional<EssayExamQuestion> data = essayQuestionRepository.findById(questionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExams() {
		return examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/exam")
	public Iterable<Exam> findExamsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			return examRepository.findExamByTopic(topic);
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExam(@PathVariable("topicId") int topicId, @RequestBody Exam newExam) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			Course course = topic.getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newExam.setTopic(topic);
			return examRepository.save(newExam);
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/save")
	public Exam updateExam(@PathVariable("examId") int examId, @RequestBody Exam newExam) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			exam.setDescription(newExam.getDescription());
			exam.setTitle(newExam.getTitle());
			return examRepository.save(exam);
		}
		return null;
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
		}
		examRepository.deleteById(examId);
	}
	
	@PostMapping("api/exam/{examId}/essay")
	public EssayExamQuestion createEssayQuestion(@PathVariable("examId") int examId, @RequestBody EssayExamQuestion newEssayExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newEssayExamQuestion.setExam(exam);
			return essayQuestionRepository.save(newEssayExamQuestion);
		}
		return null;
	}
	
	@PostMapping("api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createMultipleChoiceQuestion(@PathVariable("examId") int examId, @RequestBody MultipleChoiceExamQuestion newMultipleChoiceExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newMultipleChoiceExamQuestion.setExam(exam);
			return multipleChoiceQuestionRepository.save(newMultipleChoiceExamQuestion);
		}
		return null;
	}
	
	@PostMapping("api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion createFillInTheBlanksQuestion(@PathVariable("examId") int examId, @RequestBody FillInTheBlanksExamQuestion newFillInTheBlanksExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newFillInTheBlanksExamQuestion.setExam(exam);
			return fillInTheBlanksQuestionRepository.save(newFillInTheBlanksExamQuestion);
		}
		return null;
	}
	
	@PostMapping("api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueFalseQuestion(@PathVariable("examId") int examId, @RequestBody TrueOrFalseExamQuestion newTrueOrFalseExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			Course course = exam.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newTrueOrFalseExamQuestion.setExam(exam);
			return trueFalseQuestionRepository.save(newTrueOrFalseExamQuestion);
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public Iterable<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
}
