package dao;



import model.TrainingSession;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TrainingSessionDAO extends GenericDAO<TrainingSession> {
    public TrainingSessionDAO(EntityManager entityManager) {
        super(TrainingSession.class, entityManager);
    }

    // List all training sessions held in a specific location in Criteria API
    public List<TrainingSession> getSessionsByLocation(String location) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(TrainingSession.class);
        var root = cq.from(TrainingSession.class);
        cq.select(root).where(cb.equal(root.get("location"), location));
        return entityManager.createQuery(cq).getResultList();
    }
}
