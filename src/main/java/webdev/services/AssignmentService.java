package webdev.services;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Assignment;
import webdev.models.Course;
import webdev.models.Topic;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	@Autowired
	AssignmentRepository assignmentRepository;
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignment() {
		return assignmentRepository.findAll();
	}
	
	@GetMapping("/api/assignment/{aid}")
	public Assignment findAssignmentById(@PathVariable("aid") int id) {
		Optional<Assignment> data = assignmentRepository.findById(id);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/assignment")
	public Iterable<Assignment> findAllAssignmentsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			return assignmentRepository.findAssignmentByTopic(topic);
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment createAssignment(@PathVariable("topicId") int topicId, @RequestBody Assignment newAssignment) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			Course course = topic.getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newAssignment.setTopic(topic);
			return assignmentRepository.save(newAssignment);
		}
		return null;
	}
	
	@DeleteMapping("/api/assignment/{aid}")
	public void deleteLesson(@PathVariable("aid") int aid) {
		Optional<Assignment> data = assignmentRepository.findById(aid);
		if (data.isPresent()) {
			Assignment assignment = data.get();
			Course course = assignment.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
		}
		assignmentRepository.deleteById(aid);
	}
	
	@PostMapping("/api/assignment/{assignmentId}/save")
	public Assignment updateAssignment(@PathVariable("assignmentId") int assignmentId, @RequestBody Assignment newAssignment) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		if (data.isPresent()) {
			Assignment assignment = data.get();
			Course course = assignment.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			assignment.setDescription(newAssignment.getDescription());
			assignment.setTitle(newAssignment.getTitle());
			assignment.setPoints(newAssignment.getPoints());
			return assignmentRepository.save(assignment);
		}
		return null;
	}
}
