package psu.edu.Lab_14.repository;

import org.springframework.data.repository.ListCrudRepository;
import psu.edu.Lab_14.model.Course;

public interface CourseRepository extends ListCrudRepository<Course, Long> {
}
