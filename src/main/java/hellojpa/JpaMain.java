package hellojpa;

import hellojpa.jpa.JpaPersistence;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            JpaPersistence.remove(em,
                    JpaPersistence.persist(em, 102L, "helloJPA2"));
        });
    }
}
