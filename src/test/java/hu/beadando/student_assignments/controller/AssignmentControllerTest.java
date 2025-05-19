package hu.beadando.student_assignments.controller;

import hu.beadando.student_assignments.model.Assignment;
import hu.beadando.student_assignments.model.Student;
import hu.beadando.student_assignments.repository.AssignmentRepository;
import hu.beadando.student_assignments.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentControllerTest {

    private AssignmentController controller;
    private AssignmentRepository assignmentRepository;
    private StudentRepository studentRepository;

    // Ez a metódus minden teszt előtt lefut, és példányosítja a mockokat és a kontrollert
    @BeforeEach
    void setUp() {
        assignmentRepository = mock(AssignmentRepository.class);
        studentRepository = mock(StudentRepository.class);
        controller = new AssignmentController(assignmentRepository, studentRepository);
    }

    // Teszteli, hogy a beadandók listázása működik-e, és helyes nézetet ad vissza
    @Test
    void testListAssignments() {
        Model model = mock(Model.class);
        when(assignmentRepository.findAll()).thenReturn(List.of()); // üres lista visszaadása

        String view = controller.listAssignments(model);

        verify(model).addAttribute(eq("assignments"), any()); // ellenőrzi, hogy hozzáadott attribútumot
        assertEquals("assignments/list", view); // ellenőrzi, hogy a visszaadott nézet neve helyes-e
    }

    // Teszteli az új beadandó létrehozó űrlap megjelenítését
    @Test
    void testShowCreateForm() {
        Model model = mock(Model.class);
        when(studentRepository.findAll()).thenReturn(List.of()); // üres lista diákokból

        String view = controller.showCreateForm(model);

        verify(model).addAttribute(eq("assignment"), any(Assignment.class)); // új beadandó objektum
        verify(model).addAttribute(eq("students"), any()); // diákok listája
        assertEquals("assignments/new", view); // ellenőrzi, hogy a megfelelő nézetet kapjuk
    }

    // Teszteli, hogy egy új beadandót elment-e és átirányít
    @Test
    void testCreateAssignment() {
        Assignment assignment = new Assignment("Teszt Cím", LocalDate.now(), "Leadandó", null);

        String view = controller.createAssignment(assignment);

        verify(assignmentRepository).save(assignment); // elmenti-e a repository-ba
        assertEquals("redirect:/assignments", view); // átirányít a listához
    }

    // Teszteli a saveAssignment metódust, ami a hallgató ID alapján állítja be a kapcsolatot
    @Test
    void testSaveAssignment() {
        Student student = new Student("Teszt Elek", "teszt@example.com", 2);
        student.setId(1L);
        Assignment assignment = new Assignment("Cím", LocalDate.now(), "Leadandó", null);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        String view = controller.saveAssignment(assignment, 1L);

        verify(assignmentRepository).save(assignment); // menti a beadandót
        assertEquals(student, assignment.getStudent()); // ellenőrzi, hogy a hallgató hozzá van rendelve
        assertEquals("redirect:/assignments", view); // átirányít a listához
    }

    // Teszteli a szerkesztő űrlap betöltését meglévő beadandó esetén
    @Test
    void testShowEditForm() {
        Assignment assignment = new Assignment("Edit cím", LocalDate.now(), "Folyamatban", null);
        assignment.setId(1L);

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(studentRepository.findAll()).thenReturn(List.of());

        Model model = mock(Model.class);
        String view = controller.showEditForm(1L, model);

        verify(model).addAttribute("assignment", assignment);
        verify(model).addAttribute(eq("students"), any());
        assertEquals("assignments/edit", view);
    }

    // Teszteli, hogy a meglévő beadandó módosítását menti-e és hozzárendeli-e a hallgatót
    @Test
    void testUpdateAssignment() {
        Assignment assignment = new Assignment("Update cím", LocalDate.now(), "Kész", null);
        Student student = new Student("Teszt Elek", "teszt@example.com", 3);
        student.setId(5L);

        when(studentRepository.findById(5L)).thenReturn(Optional.of(student));

        String view = controller.updateAssignment(10L, assignment, 5L);

        assertEquals(10L, assignment.getId()); // ID beállítva a meglévő entitásra
        assertEquals(student, assignment.getStudent()); // kapcsolódó hallgató
        verify(assignmentRepository).save(assignment); // mentés
        assertEquals("redirect:/assignments", view); // átirányítás
    }

    // Teszteli, hogy a beadandót törli a repository-ból
    @Test
    void testDeleteAssignment() {
        String view = controller.deleteAssignment(100L);

        verify(assignmentRepository).deleteById(100L); // hívja a törlést
        assertEquals("redirect:/assignments", view); // visszairányít a listához
    }
}
