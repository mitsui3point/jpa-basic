package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//application loading 시점에 한개만 생성

        EntityManager em = emf.createEntityManager();//실제 transaction 단위당 entityManager 를 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            List<Member> members = selectMembersByNameWithPaging(em, 0, 3, "helloJpa");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    /**
     * 회원 엔티티 목록 조회
     * JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
     */
    private static List<Member> selectMembersByNameWithPaging(EntityManager em, int start, int end, String searchNameCondition) {
        List<Member> members = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", searchNameCondition)
                .setFirstResult(start)
                .setMaxResults(end)
                .getResultList();

        for (Member member : members) {
            System.out.println("member.getName() = " + member.getName());
        }

        return members;
    }

    /**
     * 회원 엔티티 수정
     */
    private static void updateMemberName(EntityManager em, Long id) {
        Member member = selectMember(em, id);
        member.setName("helloJpa");
    }

    /**
     * 회원 엔티티 삭제
     */
    private static void deleteMember(EntityManager em, Long id) {
        Member member = selectMember(em, id);
        em.remove(member);
    }

    /**
     * 회원 엔티티 단건 조회
     */
    private static Member selectMember(EntityManager em, long id) {
        Member member = em.find(Member.class, id);
        System.out.println("member = " + member);

        return member;
    }

    /**
     * 회원 엔티티 등록
     */
    private static void insertMember(EntityManager em, long id, String name) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);

        em.persist(member);
    }
}
