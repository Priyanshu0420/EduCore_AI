package com.example.StudentCourseApplication.Service;

import com.example.StudentCourseApplication.DTO.CourseReqDTO;
import com.example.StudentCourseApplication.DTO.CourseRespDTO;
import com.example.StudentCourseApplication.DTO.StudentReqDTO;
import com.example.StudentCourseApplication.DTO.StudentRespDTO;
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
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;


    public CourseRespDTO createCourse(CourseReqDTO courseReqDTO) {
        Course course = modelMapper.map(courseReqDTO, Course.class);
        course = courseRepository.save(course);
        return modelMapper.map(course, CourseRespDTO.class);
    }

    public List<CourseRespDTO> getCourses() {
        List<Course> courseList = courseRepository.findAll();
        return courseList.stream().map(course -> modelMapper.map(course, CourseRespDTO.class)).toList();

    }

    public CourseRespDTO getCoursebyId(Long courseID) {
        Course course = courseRepository.findById(courseID).orElseThrow(() -> new EntityNotFoundException("Course not found!!"));
        return modelMapper.map(course, CourseRespDTO.class);
    }

    public void deleteCourse(Long courseID) {
        if (!courseRepository.existsById(courseID)) {
            throw new EntityNotFoundException("Course not found!!");
        }
        courseRepository.deleteById(courseID);
    }

    public CourseRespDTO updateCourseDetail(Long courseID, CourseReqDTO courseReqDTO) {
        Course existingCourse = courseRepository.findById(courseID)
                .orElseThrow(() -> new EntityNotFoundException("Course not found!!"));

        if (courseReqDTO.getCourse_Name() != null) {
            existingCourse.setCourse_Name(courseReqDTO.getCourse_Name());
        }
        if (courseReqDTO.getDescription() != null) {
            existingCourse.setDescription(courseReqDTO.getDescription());
        }
        if (courseReqDTO.getCourse_Credits() != null) {
            existingCourse.setCourse_Credits(courseReqDTO.getCourse_Credits());
        }

        Course updatedCourses = courseRepository.save(existingCourse);

        return new CourseRespDTO(
                updatedCourses.getCourse_ID(),
                updatedCourses.getCourse_Name(),
                updatedCourses.getDescription(),
                updatedCourses.getCourse_Credits()
        );
    }

    public CourseRespDTO updateCourse(Long courseID, CourseReqDTO courseReqDTO) {
        Course existingCourse = courseRepository.findById(courseID).orElseThrow(() -> new EntityNotFoundException("Course not found!!"));
        modelMapper.map(courseReqDTO, existingCourse);
        existingCourse = courseRepository.save(existingCourse);
        return modelMapper.map(existingCourse, CourseRespDTO.class);

    }



}
