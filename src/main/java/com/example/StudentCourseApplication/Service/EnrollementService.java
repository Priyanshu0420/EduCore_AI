package com.example.StudentCourseApplication.Service;

import com.example.StudentCourseApplication.DTO.CourseRespDTO;
import com.example.StudentCourseApplication.DTO.EnrollementReqDTO;
import com.example.StudentCourseApplication.DTO.EnrollementRespDTO;
import com.example.StudentCourseApplication.Entity.Course;
import com.example.StudentCourseApplication.Entity.Student;
import com.example.StudentCourseApplication.Repository.CourseRepository;
import com.example.StudentCourseApplication.Repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrollementService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public EnrollementRespDTO enrollStudents(Long studentID, Long courseID) {
        Student student= studentRepository.findById(studentID).orElseThrow(()-> new EntityNotFoundException("Student not Found!!"));
        Course course=courseRepository.findById(courseID).orElseThrow(()-> new EntityNotFoundException("Course not Found!!"));
        if(!student.getCourseList().contains(course)){
            student.getCourseList().add(course);
        }

        studentRepository.save(student);
        return new EnrollementRespDTO(
                student.getStudent_ID(),
                course.getCourse_ID(),
                course.getCourse_Name(),
                student.getStudent_Name()
        );
    }

    public void removeEnrolledStudents(Long studentID, Long courseID) {
        Student student= studentRepository.findById(studentID).orElseThrow(()-> new EntityNotFoundException("Student not Found!!"));
        Course course=courseRepository.findById(courseID).orElseThrow(()-> new EntityNotFoundException("Course not Found!!"));
        if(!student.getCourseList().contains(course)){
            student.getCourseList().remove(course);
        }
        studentRepository.save(student);
    }

    public List<CourseRespDTO> getCoursesByStudentID(Long studentID) {

        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        return student.getCourseList()
                .stream()
                .map(course -> modelMapper.map(course, CourseRespDTO.class))
                .toList();
    }
}
