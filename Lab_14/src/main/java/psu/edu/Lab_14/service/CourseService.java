package psu.edu.Lab_14.service;

import java.util.List;
import org.springframework.stereotype.Service;
import psu.edu.Lab_14.exception.CourseAlreadyExistsException;
import psu.edu.Lab_14.exception.CourseNotFoundException;
import psu.edu.Lab_14.model.Course;
import psu.edu.Lab_14.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        if (course.id() != null && courseRepository.existsById(course.id())) {
            throw new CourseAlreadyExistsException(course.id());
        }
        // Spring Data JDBC treats a record with a non-null id as an UPDATE,
        // so we pass a null id to force an INSERT and let the DB generate the id.
        Course newCourse = new Course(
                null,
                course.code(),
                course.name(),
                course.description(),
                course.credits());
        return courseRepository.save(newCourse);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    public void updateCourse(Long id, Course newValues) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        Course updatedCourse = new Course(
                id,
                newValues.code(),
                newValues.name(),
                newValues.description(),
                newValues.credits());

        courseRepository.save(updatedCourse);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }
}
