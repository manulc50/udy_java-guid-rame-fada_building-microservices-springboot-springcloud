package net.mlorenzo.springbootrestapi.controller;

import net.mlorenzo.springbootrestapi.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Joe", "Buck");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "jhon doe")
                .body(student);
    }

    @GetMapping()
    public List<Student> getStudents() {
        return List.of(
                new Student(1, "Joe", "Buck"),
                new Student(2, "Jim", "Bean"),
                new Student(3, "Jhon", "Doe"));
    }

    @GetMapping("{id}/{first-name}/{last-name}")
    public Student getStudentPathVariable(@PathVariable int id,
                                          @PathVariable(value = "first-name") String firstName,
                                          @PathVariable(name = "last-name") String lastName) {
        return new Student(id, firstName, lastName);
    }

    @GetMapping("query")
    public Student getStudentRequestParam(@RequestParam("id") int idStudent,
                                          @RequestParam(required = false) String firstName,
                                          @RequestParam(defaultValue = "Doe") String lastName) {
        if(firstName == null)
            firstName = "Jhon";
        return new Student(idStudent, firstName, lastName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return student;
    }

    @PutMapping("{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable int id) {
        return new Student(id, student.getFirstName(), student.getLastName());
    }

    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(value = "id") int studentId) {
        System.out.println("Student with id " + studentId + " deleted successfully!");
        return ResponseEntity.noContent().build();
    }
}
