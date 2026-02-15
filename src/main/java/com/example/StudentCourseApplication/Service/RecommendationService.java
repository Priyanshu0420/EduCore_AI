package com.example.StudentCourseApplication.Service;

import com.example.StudentCourseApplication.DTO.AIRequest;
import com.example.StudentCourseApplication.DTO.AIResponse;
import com.example.StudentCourseApplication.Entity.Student;
import com.example.StudentCourseApplication.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecommendationService {

    @Value("${ai.api.url}") private String apiUrl;
    @Value("${ai.api.key}") private String apiKey;
    @Value("${ai.model}") private String model;

    private final RestTemplate restTemplate;
    private final StudentRepository studentRepository;

    public RecommendationService(RestTemplate restTemplate, StudentRepository studentRepository) {
        this.restTemplate = restTemplate;
        this.studentRepository = studentRepository;
    }

    public List<String> getRecommendations(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        String prompt = buildPrompt(student);

        AIRequest request = new AIRequest();
        request.setModel(model);
        request.setMessages(List.of(new AIRequest.Message("user", prompt)));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AIRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<AIResponse> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, entity, AIResponse.class
            );

            String content = response.getBody().getChoices().get(0).getMessage().getContent();

            return List.of(content.replace("[","").replace("]","").replace("\"","").split(","));
        } catch (HttpClientErrorException.TooManyRequests e) {
            System.out.println("AI API quota exceeded. Returning fallback courses.");
            return getMockRecommendations();
        } catch (Exception e) {
            System.out.println("AI API call failed: " + e.getMessage());
            return getMockRecommendations();
        }
    }

    private String buildPrompt(Student student) {
        String completedCourses = student.getCourseList() == null ? "" :
                student.getCourseList().stream()
                        .map(c -> c.getCourse_Name())
                        .reduce((a,b) -> a + ", " + b).orElse("");

        return String.format(
                "Recommend 5 courses for a student named '%s'. " +
                        "Preferred Category: %s. " +
                        "Completed Courses: %s. " +
                        "Return only course names in a JSON array format.",
                student.getStudent_Name(),
                student.getPreferredCategory() != null ? student.getPreferredCategory() : "General",
                completedCourses
        );
    }

    // Fallback / Mock courses
    private List<String> getMockRecommendations() {
        return List.of(
                "Spring Security",
                "Microservices Architecture",
                "Docker for Developers",
                "Cloud Computing Basics",
                "Advanced Java Programming"
        );
    }
}
