package com.example.StudentCourseApplication.Service;

import com.example.StudentCourseApplication.DTO.StudentReqDTO;
import com.example.StudentCourseApplication.DTO.StudentRespDTO;
import com.example.StudentCourseApplication.Entity.Student;
import com.example.StudentCourseApplication.Repository.CourseRepository;
import com.example.StudentCourseApplication.Repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentRespDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .map(student -> modelMapper.map(student, StudentRespDTO.class))
                .collect(Collectors.toList());
    }

    public StudentRespDTO createStudent(StudentReqDTO studentReqDTO) {
        Student newStudent=modelMapper.map(studentReqDTO,Student.class);
        Student student=studentRepository.save(newStudent);
        return modelMapper.map(student,StudentRespDTO.class);
    }

    public StudentRespDTO updateStudent(Long studentID, StudentReqDTO studentReqDTO) {
        Student existingStudent = studentRepository.findById(studentID).orElseThrow(()-> new EntityNotFoundException("Student not found!!"));
        modelMapper.map(studentReqDTO,existingStudent);
        existingStudent=studentRepository.save(existingStudent);
        return modelMapper.map(existingStudent,StudentRespDTO.class);
    }

    public StudentRespDTO updateStudentDetail(Long studentID, StudentReqDTO studentReqDTO) {
        Student existingStudent=studentRepository.findById(studentID).orElseThrow(()-> new EntityNotFoundException("Student not found!!"));
        if(studentReqDTO.getStudent_Name() !=null){
            existingStudent.setStudent_Name(studentReqDTO.getStudent_Name());
        }
        if(studentReqDTO.getStudent_Email() !=null){
            existingStudent.setStudent_Email(studentReqDTO.getStudent_Email());
        }
        if (studentReqDTO.getDob() !=null){
            existingStudent.setDob(studentReqDTO.getDob());
        }
        if(studentReqDTO.getGender() !=null){
            existingStudent.setGender(studentReqDTO.getGender());
        }
        Student updatedStudent= studentRepository.save(existingStudent);
        return new StudentRespDTO(
                updatedStudent.getStudent_ID(),
                updatedStudent.getStudent_Email(),
                updatedStudent.getStudent_Name(),
                updatedStudent.getDob(),
                updatedStudent.getGender()
        );
    }

    public StudentRespDTO searchStudent(Long studentID) {
        Student existingStudent=studentRepository.findById(studentID).orElseThrow(()-> new EntityNotFoundException("Student not found!!"));
        return modelMapper.map(existingStudent,StudentRespDTO.class);
    }

    public void deleteStudent(Long studentID) {
        if(!studentRepository.existsById(studentID)) {
            throw new EntityNotFoundException("Student not found!!");
        }
        studentRepository.deleteById(studentID);
    }
}

