package com.example.StudentCourseApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EnrollementReqDTO {

    private Long studentID;

    private Long courseID;
}
