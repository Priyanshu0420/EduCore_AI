package com.example.StudentCourseApplication.Controller;

import com.example.StudentCourseApplication.Service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<String>> getRecommendations(@PathVariable Long studentId) {
        List<String> recommendedCourses = recommendationService.getRecommendations(studentId);
        return ResponseEntity.ok(recommendedCourses);
    }
}
