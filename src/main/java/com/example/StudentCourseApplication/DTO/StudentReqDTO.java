package com.example.StudentCourseApplication.DTO;

import com.example.StudentCourseApplication.Entity.type.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class StudentReqDTO {

    private String student_Name;

    private String student_Email;

    private LocalDate dob;

    private Gender gender;

}
