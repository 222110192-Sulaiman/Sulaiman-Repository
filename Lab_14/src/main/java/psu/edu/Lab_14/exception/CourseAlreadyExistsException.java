package psu.edu.Lab_14.exception;

public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException(Long id) {
        super("Course with id " + id + " already exists");
    }
}
