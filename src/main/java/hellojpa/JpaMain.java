package hellojpa;

import hellojpa.context.JpaPersistenceContext;
import hellojpa.jpa.JpaCRUD;
import hellojpa.jpa.JpaPersistence;

public class JpaMain {
    public static void main(String[] args) {
        JpaPersistenceContext.create(em -> {
            JpaCRUD.selectMembersByNameWithPaging(em, 0, 1, "helloJpa");
        });
    }
}
