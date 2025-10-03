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

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    private boolean loaded;

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

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PostLoad
    protected void afterLoad() {
        loaded = true;
    }

    // Getters and Setters

}
