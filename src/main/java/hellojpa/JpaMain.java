package hellojpa;

import hellojpa.constants.RoleType;
import hellojpa.entity.Member;
import hellojpa.jpa.JpaCRUD;
import hellojpa.jpa.JpaDetachEntity;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member = new Member();
            member.setId("ID_A");
            member.setUsername("usernameA");
            em.persist(member);
        });
    }
}
