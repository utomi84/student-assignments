package hu.beadando.student_assignments.controller;

import hu.beadando.student_assignments.model.Assignment;
import hu.beadando.student_assignments.model.Student;
import hu.beadando.student_assignments.repository.AssignmentRepository;
import hu.beadando.student_assignments.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;

    public AssignmentController(AssignmentRepository assignmentRepository,
                                StudentRepository studentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }

    // Listázza a beadandókat
    @GetMapping
    public String listAssignments(final Model model) {
        model.addAttribute("assignments", assignmentRepository.findAll());
        return "assignments/list";
    }

    // Új beadandó létrehozása (űrlap)
    @GetMapping("/new")
    public String showCreateForm(final Model model) {
        model.addAttribute("assignment", new Assignment());
        model.addAttribute("students", studentRepository.findAll());
        return "assignments/new";
    }

    // Új beadandó mentése az űrlapról
    @PostMapping("/new")
    public String createAssignment(@ModelAttribute final Assignment assignment) {
        assignmentRepository.save(assignment);
        return "redirect:/assignments";
    }

    // Beadandó mentése meglévő hallgatóval
    @PostMapping
    public String saveAssignment(@ModelAttribute final Assignment assignment,
                                 @RequestParam final Long studentId) {
        final Student student = studentRepository.findById(studentId).orElseThrow();
        assignment.setStudent(student);
        assignmentRepository.save(assignment);
        return "redirect:/assignments";
    }

    // Beadandó szerkesztése (űrlap)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable final Long id, final Model model) {
        final Assignment assignment = assignmentRepository.findById(id).orElseThrow();
        model.addAttribute("assignment", assignment);
        model.addAttribute("students", studentRepository.findAll());
        return "assignments/edit";
    }

    // Beadandó módosítása
    @PostMapping("/edit/{id}")
    public String updateAssignment(@PathVariable final Long id,
                                   @ModelAttribute final Assignment assignment,
                                   @RequestParam final Long student) {
        final Student selectedStudent = studentRepository.findById(student).orElseThrow();
        assignment.setStudent(selectedStudent);
        assignment.setId(id); // szükséges a meglévő entitás módosításához
        assignmentRepository.save(assignment);
        return "redirect:/assignments";
    }

    // Beadandó törlése
    @GetMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable final Long id) {
        assignmentRepository.deleteById(id);
        return "redirect:/assignments";
    }
}
