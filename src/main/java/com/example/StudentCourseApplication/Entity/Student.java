package com.example.StudentCourseApplication.Entity;

import com.example.StudentCourseApplication.Entity.type.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_ID;

    @Column(nullable = false)
    private String student_Name;

    @Column(nullable = false, unique = true)
    private String student_Email;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // helps AI recommend relevant courses
    private String preferredCategory;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courseList;

    public List<String> getCompletedCourseNames() {
        if(courseList == null) return List.of();
        return courseList.stream()
                .map(Course::getCourse_Name)
                .collect(Collectors.toList());
    }
}
