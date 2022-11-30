package hellojpa.context;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public interface JpaCode {
//    public void execute(EntityManager em);
    public void execute(EntityManager em, EntityManagerFactory emf);
}
