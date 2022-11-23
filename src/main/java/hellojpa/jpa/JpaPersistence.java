package hellojpa.jpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;

public class JpaPersistence {
    /**
     * 비영속 -> 영속
     * entityManager.persist()
     */
    public static Member persist(EntityManager em, long id, String name) {
        //비영속 상태
        Member member = new Member();
        member.setId(id);
        member.setName(name);

        //영속
        System.out.println("=== BEFORE ===");
        em.persist(member);
        System.out.println("=== AFTER ===");
        return member;
    }

    /**
     * 영속 -> 영속성 컨텍스트에서 분리, 준영속 상태
     */
    public static void detach(EntityManager em, Member persistedMember) {
        em.detach(persistedMember);
    }

    /**
     * 영속 -> 객체 삭제 및 RDB 데이터 제거
     */
    public static void remove(EntityManager em, Member persistedMember) {
        em.remove(persistedMember);
    }
}
