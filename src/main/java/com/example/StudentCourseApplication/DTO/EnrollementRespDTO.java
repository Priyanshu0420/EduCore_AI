package com.example.StudentCourseApplication.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EnrollementRespDTO {

    private Long studentID;

    private Long courseID;

    private String courseName;

    private String studentName;

}
