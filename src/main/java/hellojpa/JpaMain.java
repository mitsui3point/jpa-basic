package hellojpa;

import hellojpa.entity.Member;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member1 = new Member();
            member1.setName("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member1");
            em.persist(member2);

            em.flush();
            em.clear();

            Member findMember1 = em.find(Member.class, member1.getId());
            Member findMember2 = em.getReference(Member.class, member2.getId());

            externalLogic(findMember1, findMember2);
        });
    }

    private static void externalLogic(Member member1, Member member2) {
        System.out.println("member1 = " + member1.getClass());
        System.out.println("member2 = " + member2.getClass());
        boolean result = member1.getClass() == member2.getClass();
        System.out.println("member1 == member2 = " + result);
        boolean result1 = member1 instanceof Member;
        boolean result2 = member2 instanceof Member;
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
    }
}
