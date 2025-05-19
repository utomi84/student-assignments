package hu.beadando.student_assignments.repository;

import hu.beadando.student_assignments.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
