package com.example.StudentCourseApplication.DTO;

import com.example.StudentCourseApplication.Entity.type.Credits;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseRespDTO {
    private Long courseID;

    private String courseName;

    private String description;

    private Credits courseCredit;
}
