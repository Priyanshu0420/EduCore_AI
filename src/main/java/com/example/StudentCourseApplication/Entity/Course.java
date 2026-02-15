package com.example.StudentCourseApplication.Entity;

import com.example.StudentCourseApplication.Entity.type.Credits;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_ID;

    @Column(nullable = false)
    private String course_Name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Credits course_Credits;

    //  helps AI understand course type
    private String category;

    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList;

}
