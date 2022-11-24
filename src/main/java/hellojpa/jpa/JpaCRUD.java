package hellojpa.jpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaCRUD {

    /**
     * 회원 엔티티 목록 조회
     * JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
     */
    public static List<Member> selectMembersByNameWithPaging(EntityManager em, int start, int end, String searchNameCondition) {
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
    public static void updateMemberName(EntityManager em, Long id) {
        Member member = selectMember(em, id);
        member.setName("helloJpa");
    }

    /**
     * 회원 엔티티 삭제
     */
    public static void deleteMember(EntityManager em, Long id) {
        Member member = selectMember(em, id);
        em.remove(member);
    }

    /**
     * 회원 엔티티 단건 조회
     */
    public static Member selectMember(EntityManager em, long id) {
        Member member = em.find(Member.class, id);
        System.out.println("member = " + member);

        return member;
    }

    /**
     * 회원 엔티티 등록
     */
    public static void insertMember(EntityManager em, long id, String name) {
        Member member = new Member(id, name);

        em.persist(member);
    }
}
