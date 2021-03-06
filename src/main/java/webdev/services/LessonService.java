package webdev.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Course;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moudleId}/lesson")
	public Lesson createLesson(@PathVariable("moudleId") int moudleId, @RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moudleId);
		if (data.isPresent()) {
			Module module = data.get();
			Course course = module.getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newLesson.setModule(module);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			Module module = lesson.getModule();
			Course course = module.getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
		}
		lessonRepository.deleteById(lessonId);
	}
	
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll();
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable("lessonId") int id) {
		Optional<Lesson> data = lessonRepository.findById(id);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Iterable<Lesson> findAllLessonsForModule(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if (data.isPresent()) {
			Module module = data.get();
			return lessonRepository.findLessonByModule(module);
		}
		return null;
	}
}
