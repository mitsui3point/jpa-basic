package hellojpa;

import hellojpa.entity.Member;
import hellojpa.jpa.JpaCRUD;
import hellojpa.jpa.JpaFlush;
import hellojpa.jpa.JpaPersistence;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            JpaPersistence.persist(em, 200L, "member200");
            JpaPersistence.persist(em, 210L, "member210");
            JpaPersistence.persist(em, 220L, "member220");
            JpaFlush.jpqlSelect(em);
        });
    }
}
