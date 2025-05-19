package hu.beadando.student_assignments.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Egységteszt a Student entitáshoz.
 * Célja: getter/setter metódusok lefedettségének növelése.
 */
public class StudentTest {

    @Test
    void testStudentGettersAndSetters() {
        // Létrehozunk egy Student objektumot
        Student student = new Student();

        // Teszt adatok
        Long id = 1L;
        String name = "Teszt Elek";
        String email = "teszt@pelda.hu";
        int studyYear = 3;

        // Setterek meghívása
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setStudyYear(studyYear);

        // Getterek ellenőrzése
        assertEquals(id, student.getId());
        assertEquals(name, student.getName());
        assertEquals(email, student.getEmail());
        assertEquals(studyYear, student.getStudyYear());
    }
}
