package dao;

import model.Student;
import jakarta.persistence.EntityManager;
import model.TrainingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO(EntityManager entityManager) {
        super(Student.class, entityManager);
    }

    // Get all training sessions attended by a student using JPQL
    public List<TrainingSession> getSessionsByStudentId(Long studentId) {
        return entityManager.createQuery(
                "SELECT a.trainingSession FROM Attendance a WHERE a.student.id = :studentId",
                TrainingSession.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    // List all students with a specific rank
    public List<Student> getStudentsByRank(model.enums.Rank rank) {
        return entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.rank = :rank",
                Student.class)
                .setParameter("rank", rank)
                .getResultList();
    }

    // List all students with progress reports in the last three months
    public List<Student> getActiveStudents() {
        LocalDateTime cutoff = LocalDateTime.now().minusMonths(3);
        return entityManager.createQuery(
                "SELECT DISTINCT pr.student FROM ProgressReport pr WHERE pr.reportDate >= :cutoff",
                Student.class)
                .setParameter("cutoff", cutoff)
                .getResultList();
    }

    // List all students who joined in the last six months in Criteria API
    public List<Student> getRecentJoiners() {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Student.class);
        var root = cq.from(Student.class);
        cq.select(root).where(cb.greaterThanOrEqualTo(root.get("joinDate"), java.time.LocalDate.now().minusMonths(6)));
        return entityManager.createQuery(cq).getResultList();
    }
}
