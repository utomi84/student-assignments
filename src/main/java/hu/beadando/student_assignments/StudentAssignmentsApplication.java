package hu.beadando.student_assignments;

import hu.beadando.student_assignments.model.Assignment;
import hu.beadando.student_assignments.model.Student;
import hu.beadando.student_assignments.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class StudentAssignmentsApplication implements CommandLineRunner {

	private final StudentRepository studentRepository;
	private final Environment environment;

	public StudentAssignmentsApplication(final StudentRepository studentRepository,
										 final Environment environment) {
		this.studentRepository = studentRepository;
		this.environment = environment;
	}

	public static void main(final String[] args) {
		SpringApplication.run(StudentAssignmentsApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		if (List.of(environment.getActiveProfiles()).contains("test")) {
			return;
		}

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
