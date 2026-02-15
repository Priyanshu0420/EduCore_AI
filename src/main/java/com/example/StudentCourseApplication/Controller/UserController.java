package com.example.StudentCourseApplication.Controller;

import com.example.StudentCourseApplication.DTO.CourseRespDTO;
import com.example.StudentCourseApplication.DTO.StudentReqDTO;
import com.example.StudentCourseApplication.DTO.StudentRespDTO;
import com.example.StudentCourseApplication.Service.CourseService;
import com.example.StudentCourseApplication.Service.EnrollementService;
import com.example.StudentCourseApplication.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollementService enrollementService;

    @GetMapping("/students/{studentID}")
    public ResponseEntity<StudentRespDTO> searchStudent(@PathVariable Long studentID) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.searchStudent(studentID));
    }


    @PatchMapping("/students/{studentID}")
    public ResponseEntity<StudentRespDTO> updateStudentDetails(@PathVariable Long studentID, @RequestBody StudentReqDTO studentReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentDetail(studentID, studentReqDTO));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseRespDTO>> retrieveCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourses());
    }

    @GetMapping("/students/{studentID}/courses")
    public ResponseEntity<List<CourseRespDTO>> getEnrolledCourses(@PathVariable Long studentID) {
        return ResponseEntity.ok(enrollementService.getCoursesByStudentID(studentID));
    }
}
