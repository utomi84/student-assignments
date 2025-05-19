package hu.beadando.student_assignments.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Egységteszt az Assignment entitáshoz.
 * Célja: getter/setter metódusok lefedettségének növelése.
 */
public class AssignmentTest {

    @Test
    void testAssignmentGettersAndSetters() {
        // Létrehozunk egy Assignment objektumot
        Assignment assignment = new Assignment();

        // Teszt adatok
        Long id = 1L;
        String title = "Teszt beadandó";
        LocalDate dueDate = LocalDate.of(2025, 5, 30);
        String status = "Leadva";

        // Egy teszt Student példány hozzárendelése
        Student student = new Student();
        student.setId(2L);
        student.setName("Teszt Elek");
        student.setEmail("teszt@pelda.hu");
        student.setStudyYear(2);

        // Setterek meghívása
        assignment.setId(id);
        assignment.setTitle(title);
        assignment.setDueDate(dueDate);
        assignment.setStatus(status);
        assignment.setStudent(student);

        // Getterek ellenőrzése
        assertEquals(id, assignment.getId());
        assertEquals(title, assignment.getTitle());
        assertEquals(dueDate, assignment.getDueDate());
        assertEquals(status, assignment.getStatus());
        assertEquals(student, assignment.getStudent());
    }
}
