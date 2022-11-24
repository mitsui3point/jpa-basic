package hellojpa;

import hellojpa.jpa.JpaCRUD;
import hellojpa.jpa.JpaDetachEntity;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            JpaCRUD.selectMember(em, 160L);
        });
    }
}
