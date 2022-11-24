package hellojpa;

import hellojpa.entity.Member;
import hellojpa.jpa.JpaPersistence;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            JpaPersistence.setEntity(em, 160L, "zzzzz!");
        });
    }
}
