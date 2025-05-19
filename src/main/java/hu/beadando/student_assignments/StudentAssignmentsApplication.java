package hu.beadando.student_assignments;

import hu.beadando.student_assignments.model.Assignment;
import hu.beadando.student_assignments.model.Student;
import hu.beadando.student_assignments.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentAssignmentsApplication implements CommandLineRunner {

	private final StudentRepository studentRepository;

	public StudentAssignmentsApplication(final StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(final String[] args) {
		SpringApplication.run(StudentAssignmentsApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		final Student student = new Student(
				"Teszt Elek",
				"teszt.elek@example.com",
				2
		);

		final Assignment assignment1 = new Assignment(
				"Java Beadandó",
				LocalDate.now().plusDays(7),
				"Leadandó",
				student
		);

		final Assignment assignment2 = new Assignment(
				"Spring Boot Beadandó",
				LocalDate.now().plusDays(14),
				"Leadandó",
				student
		);

		student.setAssignments(List.of(assignment1, assignment2));
		studentRepository.save(student);
	}
}
