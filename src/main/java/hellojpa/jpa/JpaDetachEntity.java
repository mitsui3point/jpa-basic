package hellojpa.jpa;

import hellojpa.entity.MemberJpa;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Optional;

public class JpaDetachEntity {

    /**
     * 특정 영속 엔티티 -> 준영속 엔티티
     *      영속성 컨텍스트에서 관리하던 영속 엔티티를
     *      영속성 컨텍스트에서 관리하지 않는 준영속 엔티티로 변경
     *          (특정 1차 캐시 날려버림)
     * em.detach
     */
    public static void detachedEntitySet(EntityManager em, long id, String updateName) {
        MemberJpa entityMemberJpa = em.find(MemberJpa.class, id);
        entityMemberJpa.setName(updateName);
        em.detach(entityMemberJpa);
    }

    /**
     * 전체 영속 엔티티 -> 준영속 엔티티
     *      영속성 컨텍스트에서 관리하던 영속 엔티티를
     *      영속성 컨텍스트에서 관리하지 않는 준영속 엔티티로 모두 변경
     *          (1차 캐시를 전부 날려버림)
     * em.clear
     */
    public static void clearEntitySet(EntityManager em, Long... ids) {
        String updateName = "updateName";
        for (long id : ids) {
            em.find(MemberJpa.class, id)
                    .setName(updateName);
        }
        em.clear();
        System.out.println("==AFTER clear==");
        Optional<Long> first = Arrays.stream(ids).findFirst();
        MemberJpa memberJpa = em.find(MemberJpa.class, first.get());
    }

    /**
     * 영속성 컨텍스트 종료
     * em.close
     */
    public static void closeEntitySet(EntityManager em, Long... ids) {
        String updateName = "updateName";
        for (long id : ids) {
            em.find(MemberJpa.class, id)
                    .setName(updateName);
        }
        em.close();
        System.out.println("==AFTER close==");
        Optional<Long> first = Arrays.stream(ids).findFirst();
        MemberJpa memberJpa = em.find(MemberJpa.class, first.get());
    }
}
