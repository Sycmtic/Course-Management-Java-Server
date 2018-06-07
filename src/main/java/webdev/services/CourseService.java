package webdev.services;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Course;
import webdev.models.Hello;
import webdev.models.User;
import webdev.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll();
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int id) {
		Optional<Course> data = courseRepository.findById(id);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		course.setCreated(new Timestamp(System.currentTimeMillis()));
		return courseRepository.save(course);
	}
	
	@DeleteMapping("/api/course/{courseId}") 
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
}