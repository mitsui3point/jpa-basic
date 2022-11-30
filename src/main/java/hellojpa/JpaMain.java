package hellojpa;

import hellojpa.context.JpaCode;
import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());//Proxy
            System.out.println("findMember1 = " + findMember.getClass());

            findMember.getName();
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(findMember));
        });
    }
}
