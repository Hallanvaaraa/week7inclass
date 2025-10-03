package dao;

import model.Instructor;
import jakarta.persistence.EntityManager;

import java.util.List;

public class InstructorDAO extends GenericDAO<Instructor> {
    public InstructorDAO(EntityManager entityManager) {
        super(Instructor.class, entityManager);
    }

    // Search instructors by specialization
    public List<Instructor> findBySpecialization(String specialization) {
        return entityManager.createQuery(
                "SELECT i FROM Instructor i WHERE i.specialization = :specialization", Instructor.class)
                .setParameter("specialization", specialization)
                .getResultList();
    }

    // List all instructors with more than five years of experience in Criteria API
    public List<Instructor> findExperiencedInstructors() {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Instructor.class);
        var root = cq.from(Instructor.class);
        cq.select(root).where(cb.greaterThan(root.get("yearsOfExperience"), 5));
        return entityManager.createQuery(cq).getResultList();
    }
}
