package hellojpa;

import hellojpa.jpa.JpaDetachEntity;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            JpaDetachEntity.closeEntitySet(em, 160L, 210L, 300L, 310L);
        });
    }
}
