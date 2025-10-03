package model;

import jakarta.persistence.*;

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

    private boolean status;
    private String notes;

    public Attendance() {}

    public Attendance(Student student, TrainingSession trainingSession, boolean status) {
        this.student = student;
        this.trainingSession = trainingSession;
        this.status = status;
    }

    // Getters and Setters
}
