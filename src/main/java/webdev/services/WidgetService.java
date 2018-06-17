package webdev.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Course;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	TopicRepository topicRepository;
	 
	@GetMapping("/api/widget")
	public Iterable<Widget> findAllWidgets() {
		return widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int id) {
		Optional<Widget> data = widgetRepository.findById(id);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/topic/{topicId}/widget")
	public Iterable<Widget> findAllWidgetsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			return widgetRepository.findWidgetByTopic(topic);
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topicId}/widget")
	public Widget createWidget(@PathVariable("topicId") int topicId, @RequestBody Widget newWidget) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			Course course = topic.getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			newWidget.setTopic(topic);
			return widgetRepository.save(newWidget);
		}
		return null;
	}
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			Widget widget = data.get();
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setText(newWidget.getText());
			Course course = widget.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			widgetRepository.save(widget);
			return widget;
		}
		return null;
	}
	
	@PostMapping("/api/topic/{topicId}/widget/save")
	public void saveWidget(@PathVariable("topicId") int topicId, @RequestBody Widget[] Widgets) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			Course course = topic.getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
			for (Widget widget : widgetRepository.findWidgetByTopic(topic)) {
				widgetRepository.delete(widget);
			}
			for (Widget widget : Widgets) {
				widget.setTopic(topic);
				widgetRepository.save(widget);
			}
		}
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if (data.isPresent()) {
			Widget widget = data.get();
			Course course = widget.getTopic().getLesson().getModule().getCourse();
			course.setModified(new Timestamp(System.currentTimeMillis()));
		}
		widgetRepository.deleteById(widgetId);
	}
}
