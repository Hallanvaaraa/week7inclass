
import dao.StudentDAO;
import dao.InstructorDAO;
import dao.TrainingSessionDAO;
import model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.enums.AttendanceStatus;
import model.enums.Rank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create the EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aikidoPU");
        EntityManager em = emf.createEntityManager();

        // Start a transaction
        em.getTransaction().begin();

        // Initialize DAO classes
        StudentDAO studentDAO = new StudentDAO(em);
        InstructorDAO instructorDAO = new InstructorDAO(em);
        TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO(em);

        // Add sample students
        Student student1 = new Student("Seppo Taalasmaa", "seppo@salkkarit.com", Rank.KYU_5, LocalDateTime.now());
        Student student2 = new Student("Jane Smith", "jane@example.com", Rank.KYU_6, LocalDateTime.now());

        // Save students using the DAO (no need to call em.persist directly)
        studentDAO.save(student1);
        studentDAO.save(student2);

        // Add an instructor
        Instructor instructor = new Instructor("Sensei Aki", "Aikido Throws", 10);
        instructorDAO.save(instructor);

        // Add training sessions
        TrainingSession tr1 = new TrainingSession(LocalDate.now(), "Espoo Sportshall", 2, instructor);
        TrainingSession tr2 = new TrainingSession(LocalDate.of(2025, 9, 13), "Myllypuro Sports Centre", 1, instructor);
        trainingSessionDAO.save(tr1);
        trainingSessionDAO.save(tr2);

        // Add attendances
        Attendance at1 = new Attendance(student1, tr1, AttendanceStatus.LATE);
        at1.setNotes("Showed up 30 mins into the session");
        Attendance at2 = new Attendance(student2, tr1, AttendanceStatus.PRESENT);
        at2.setNotes("Attended full session");
        Attendance at3 = new Attendance(student1, tr2, AttendanceStatus.ABSENT);
        at3.setNotes("Did not inform about absence");
        em.persist(at1);
        em.persist(at2);
        em.persist(at3);

        // Add a progress report
        ProgressReport pr1 = new ProgressReport(student1, "Good progress on throws.", "Needs to improve footwork.");
        em.persist(pr1);

        // Commit the transaction
        em.getTransaction().commit();

        List<TrainingSession> s1sessions = studentDAO.getSessionsByStudentId(student1.getId());
        System.out.println("Training sessions attended by " + student1.getName() + ":");
        for (TrainingSession ts : s1sessions) {
            System.out.println("- " + ts.getDate() + " at " + ts.getLocation());
        }

        List<Student> kyu5Students = studentDAO.getStudentsByRank(Rank.KYU_5);
        System.out.println("Students with rank Kyu 5:");
        for (Student s : kyu5Students) {
            System.out.println("- " + s.getName());
        }

        List<Student> activeStudents = studentDAO.getActiveStudents();
        System.out.println("Active students with progress reports in the last 3 months:");
        for (Student s : activeStudents) {
            System.out.println("- " + s.getName());
        }

        List<Student> recentJoiners = studentDAO.getRecentJoiners();
        System.out.println("Students who joined in the last 6 months:");
        for (Student s : recentJoiners) {
            System.out.println("- " + s.getName());
        }

        List<Instructor> aikidoInstructors = instructorDAO.findBySpecialization("Aikido Throws");
        System.out.println("Instructors specializing in Aikido Throws:");
        for (Instructor i : aikidoInstructors) {
            System.out.println("- " + i.getName());
        }

        List<Instructor> experiencedInstructors = instructorDAO.findExperiencedInstructors();
        System.out.println("Instructors with more than 5 years of experience:");
        for (Instructor i : experiencedInstructors) {
            System.out.println("- " + i.getName() + ", " + i.getExperienceYears() + " years");
        }

        List<TrainingSession> helsinkiSessions = trainingSessionDAO.getSessionsByLocation("Helsinki Dojo");
        System.out.println("Training sessions held in Helsinki Dojo:");
        for (TrainingSession ts : helsinkiSessions) {
            System.out.println("- " + ts.getDate() + " at " + ts.getLocation());
        }


        // Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
