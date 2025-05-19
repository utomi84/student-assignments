package hu.beadando.student_assignments.repository;

import hu.beadando.student_assignments.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
