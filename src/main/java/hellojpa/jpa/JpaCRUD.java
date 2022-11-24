package hellojpa.jpa;

import hellojpa.entity.MemberJpa;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaCRUD {

    /**
     * 회원 엔티티 목록 조회
     * JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
     */
    public static List<MemberJpa> selectMembersByNameWithPaging(EntityManager em, int start, int end, String searchNameCondition) {
        List<MemberJpa> memberJpas = em.createQuery("select m from MemberJpa m where m.name = :name", MemberJpa.class)
                .setParameter("name", searchNameCondition)
                .setFirstResult(start)
                .setMaxResults(end)
                .getResultList();

        for (MemberJpa memberJpa : memberJpas) {
            System.out.println("member.getName() = " + memberJpa.getName());
        }

        return memberJpas;
    }

    /**
     * 회원 엔티티 수정
     */
    public static void updateMemberName(EntityManager em, Long id) {
        MemberJpa memberJpa = selectMember(em, id);
        memberJpa.setName("helloJpa");
    }

    /**
     * 회원 엔티티 삭제
     */
    public static void deleteMember(EntityManager em, Long id) {
        MemberJpa memberJpa = selectMember(em, id);
        em.remove(memberJpa);
    }

    /**
     * 회원 엔티티 단건 조회
     */
    public static MemberJpa selectMember(EntityManager em, long id) {
        MemberJpa memberJpa = em.find(MemberJpa.class, id);
        System.out.println("member = " + memberJpa);

        return memberJpa;
    }

    /**
     * 회원 엔티티 등록
     */
    public static void insertMember(EntityManager em, long id, String name) {
        MemberJpa memberJpa = new MemberJpa(id, name);

        em.persist(memberJpa);
    }
}
