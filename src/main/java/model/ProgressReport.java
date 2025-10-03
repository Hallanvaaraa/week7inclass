package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProgressReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Version
    private Long version;

    private LocalDateTime reportDate = LocalDateTime.now();

    private String achievements;
    private String areasForImprovement;

    public ProgressReport() {}

    public ProgressReport(Student student, String achievements, String areasForImprovement) {
        this.student = student;
        this.achievements = achievements;
        this.areasForImprovement = areasForImprovement;
    }

    // Getters and Setters

}
