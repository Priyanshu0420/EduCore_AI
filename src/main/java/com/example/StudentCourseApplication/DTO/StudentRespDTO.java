package com.example.StudentCourseApplication.DTO;

import com.example.StudentCourseApplication.Entity.type.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StudentRespDTO {
    private Long studentID;

    private String studentName;

    private String student_Email;

    private LocalDate dob;

    private Gender gender;
}
