package com.example.demo.Controllers;

import com.example.demo.Services.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Models.Student;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/templates/students";
    }

    @GetMapping("/templates/students")
    public String list(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "list";
    }

    @GetMapping("/students/create")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/templates/students")
    public String create(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/templates/students";
    }

    @GetMapping("/students/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "view";
    }

    @GetMapping("/students/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "form";
    }

    @PostMapping("/students/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.save(student);
        return "redirect:/templates/students";
    }

    @PostMapping("/students/{id}/delete")
    public String delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return "redirect:/templates/students";
    }
}