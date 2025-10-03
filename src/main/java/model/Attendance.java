package model;

import jakarta.persistence.*;
import model.enums.AttendanceStatus;

import java.time.LocalDateTime;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "training_session_id", nullable = false)
    private TrainingSession trainingSession;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    private boolean loaded;

    @Convert(converter = AttendanceStatusConverter.class)
    @Column(nullable = false)
    private AttendanceStatus status;

    private String notes;

    public Attendance() {}

    public Attendance(Student student, TrainingSession trainingSession, AttendanceStatus status) {
        this.student = student;
        this.trainingSession = trainingSession;
        this.status = status;
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
    public TrainingSession getTrainingSession() {
        return trainingSession;
    }
    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
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
    public AttendanceStatus getStatus() {
        return status;
    }
    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
