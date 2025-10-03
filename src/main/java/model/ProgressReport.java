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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public boolean isLoaded() {
        return loaded;
    }
    public Long getVersion() {
        return version;
    }
    public LocalDateTime getReportDate() {
        return reportDate;
    }
    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
    public String getAchievements() {
        return achievements;
    }
    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
    public String getAreasForImprovement() {
        return areasForImprovement;
    }
    public void setAreasForImprovement(String areasForImprovement) {
        this.areasForImprovement = areasForImprovement;
    }
}
