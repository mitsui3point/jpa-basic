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
            member.setUsername("usernameA");
            System.out.println("====================");
            em.persist(member);
            System.out.println("member.getId() = " + member.getId());
            System.out.println("====================");
        });
    }
}
