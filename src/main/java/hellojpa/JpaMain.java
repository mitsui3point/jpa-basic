package hellojpa;

import hellojpa.entity.Member;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember1 = em.getReference(Member.class, member.getId());//Proxy
            System.out.println("findMember1 = " + findMember1.getClass());

            Member findMember2 = em.find(Member.class, member.getId());//expected Member but Proxy
            System.out.println("findMember2 = " + findMember2.getClass());

            System.out.println("findMember1 == findMember2 : " + (findMember1 == findMember2));// == 비교 메커니즘을 맞추기 위해 타입을 맞춤
        });
    }
}
