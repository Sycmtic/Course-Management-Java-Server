package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Topic;
import webdev.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	@Query("SELECT w FROM Widget w WHERE w.topic=:topic")
    Iterable<Widget> findWidgetByTopic(@Param("topic") Topic t);
}
