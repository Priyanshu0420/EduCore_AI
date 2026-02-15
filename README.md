🎓 EduCore AI
📌 Overview

EduCore AI is a Spring Boot–based backend application that manages student records, course details, enrollment workflows, and provides AI-powered course recommendations. The system follows a clean layered architecture ensuring scalability, maintainability, and high performance.

It exposes RESTful APIs for student management, course management, enrollment operations, and intelligent course recommendations. The AI logic is integrated directly inside the Service Layer, enabling seamless business logic processing and optimized performance.

🤖 AI Course Recommendation System

EduCore AI includes an intelligent recommendation engine implemented , using DTOs for request and response handling and Controllers for API exposure.

The recommendation engine suggests courses based on:

Student preferences

Enrollment history

Course metadata (credits, type, etc.)

Academic learning patterns

This enables personalized course suggestions instead of generic course listings.

✨ Key Features

👨‍🎓 Student Registration & Management

📚 Course Creation & Management

🔗 Student–Course Enrollment (Many-to-Many Mapping)

🤖 AI Logic Implemented

📦 DTO-Based Request & Response Handling

🌐 REST Controller-Based API Exposure

🧩 Clean Layered Architecture

⚡ Scalable and Production-Ready Design

🏗️ Project Architecture
Controller → DTO → Service (Business Logic + AI Logic) → Repository → Entity → Database

🗂️ Project Structure
src/
 └── main/
     └── java/com/example/StudentCourseApplication/
         ├── Config/
         ├── Controller/        → API Endpoints
         ├── DTO/               → Request / Response Models
         ├── Entity/            → Database Models
         ├── Repository/        → Database Access Layer
         ├── Service/           → Business Logic + AI Recommendation Logic
         └── StudentCourseApplication.java

🛠️ Tech Stack
Technology	Usage
Java 21	Core Programming
Spring Boot	Backend Framework
Spring Data JPA	ORM
Hibernate	Persistence Provider
REST APIs	Communication
Lombok	Boilerplate Reduction
🧬 Database Design Highlights

Student ↔ Course → Many-to-Many Relationship

Join Table → student_course

Enum Support → Gender, Credits

AI Input Sources → Student Data + Course Data + Enrollment Data

🚀 Getting Started
✅ Prerequisites

Java 21+

Maven

MySQL / H2 (based on configuration)

▶️ Run the Project
git clone <your-repo-url>
cd EduCore-AI
mvn spring-boot:run

🔗 API Modules

Student APIs

Course APIs

Enrollment APIs

AI Recommendation APIs

💡 Use Cases

Smart College Course Recommendation

Training Institute Learning Path Suggestions

Personalized Education Platforms

🔮 Future Enhancements

🔐 Learning JWT Authentication

📱 Mobile / Web UI Integration

🧠 Advanced Machine Learning Models

👨‍💻 Author
Priyanshu Thakur
