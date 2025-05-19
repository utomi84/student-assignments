package hu.beadando.student_assignments.controller;

import hu.beadando.student_assignments.model.Student;
import hu.beadando.student_assignments.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Listázza a hallgatókat
    @GetMapping
    public String listStudents(final Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students/list";
    }

    // Új hallgató létrehozása (űrlap)
    @GetMapping("/new")
    public String newStudentForm(final Model model) {
        model.addAttribute("student", new Student());
        return "students/new";
    }

    // Hallgató mentése
    @PostMapping
    public String saveStudent(@ModelAttribute final Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // Hallgató szerkesztése (űrlap)
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable final Long id, final Model model) {
        final Student student = studentRepository.findById(id).orElseThrow();
        model.addAttribute("student", student);
        return "students/edit";
    }

    // Hallgató módosítása
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable final Long id,
                                @ModelAttribute final Student updatedStudent) {
        updatedStudent.setId(id);
        studentRepository.save(updatedStudent);
        return "redirect:/students";
    }

    // Hallgató törlése
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable final Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}
