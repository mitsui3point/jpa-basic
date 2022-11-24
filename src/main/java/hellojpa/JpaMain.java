package hellojpa;

import hellojpa.constants.RoleType;
import hellojpa.entity.Member;
import hellojpa.jpa.JpaCRUD;
import hellojpa.jpa.JpaDetachEntity;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            Member member4 = new Member();
            member4.setUsername("D");

            Member member5 = new Member();
            member5.setUsername("E");

            System.out.println("====================");

            em.persist(member1);//call next value for MEMBER_SEQ 1, call next value for MEMBER_SEQ 51
            em.persist(member2);//memory
            em.persist(member3);//memory
            em.persist(member4);//memory
            em.persist(member5);//memory

            System.out.println("member1 = " + member1);
            System.out.println("member2 = " + member2);
            System.out.println("member3 = " + member3);
            System.out.println("member4 = " + member4);
            System.out.println("member5 = " + member5);

            System.out.println("====================");
        });
    }
}
