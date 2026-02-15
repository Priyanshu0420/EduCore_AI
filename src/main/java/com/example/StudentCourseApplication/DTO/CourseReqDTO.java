package com.example.StudentCourseApplication.DTO;

import com.example.StudentCourseApplication.Entity.type.Credits;
import lombok.*;

@Data
@RequiredArgsConstructor
public class CourseReqDTO {

    private String course_Name;

    private String description;

    private Credits course_Credits;
}
