package com.example.StudentCourseApplication.Controller;

import com.example.StudentCourseApplication.DTO.*;
import com.example.StudentCourseApplication.Service.CourseService;
import com.example.StudentCourseApplication.Service.EnrollementService;
import com.example.StudentCourseApplication.Service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollementService enrollementService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentRespDTO>> retrieveStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @PostMapping("/students")
    public ResponseEntity<StudentRespDTO> createStudents(@RequestBody StudentReqDTO studentReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.createStudent(studentReqDTO));
    }

    @PutMapping("/students/{studentID}")
    public ResponseEntity<StudentRespDTO> updateStudent(@PathVariable Long studentID, @RequestBody StudentReqDTO studentReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(studentID, studentReqDTO));
    }

    @PatchMapping("/students/{studentID}")
    public ResponseEntity<StudentRespDTO> updateStudentDetails(@PathVariable Long studentID, @RequestBody StudentReqDTO studentReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentDetail(studentID, studentReqDTO));
    }

    @GetMapping("/students/{studentID}")
    public ResponseEntity<StudentRespDTO> searchStudent(@PathVariable Long studentID) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.searchStudent(studentID));
    }

    @DeleteMapping("/students/{studentID}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentID) {
        studentService.deleteStudent(studentID);
        return ResponseEntity.noContent().build();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/courses")
    public ResponseEntity<CourseRespDTO> createCourse(@RequestBody CourseReqDTO courseReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.createCourse(courseReqDTO));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseRespDTO>> retrieveCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourses());
    }

    @GetMapping("/courses/{courseID}")
    public ResponseEntity<CourseRespDTO> searchCourse(@PathVariable Long courseID) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCoursebyId(courseID));
    }

    @DeleteMapping("/courses/{courseID}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseID) {
        courseService.deleteCourse(courseID);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/courses/{courseID}")
    public ResponseEntity<CourseRespDTO> modifyCourse(@PathVariable Long courseID,@RequestBody CourseReqDTO courseReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourseDetail(courseID,courseReqDTO));
    }

    @PutMapping("/courses/{courseID}")
    public ResponseEntity<CourseRespDTO> updateCourse(@PathVariable Long courseID,@RequestBody CourseReqDTO courseReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseID,courseReqDTO));
    }

    @GetMapping("/students/{studentID}/courses")
    public ResponseEntity<List<CourseRespDTO>> getEnrolledCourses(@PathVariable Long studentID) {
        return ResponseEntity.ok(enrollementService.getCoursesByStudentID(studentID));
    }


    /////////////////////////////////////////////////////////////////////////////

    @PostMapping("/enroll")
    public ResponseEntity<EnrollementRespDTO> enrollStudent(@RequestParam Long studentID , @RequestParam Long courseID){
        return ResponseEntity.status(HttpStatus.OK).body(enrollementService.enrollStudents(studentID,courseID));
    }

    @DeleteMapping("/enroll")
    public ResponseEntity<Void> removeEnrolledStudent(@RequestParam Long studentID , @RequestParam Long courseID){
        enrollementService.removeEnrolledStudents(studentID,courseID);
        return ResponseEntity.noContent().build();
    }
}
