package hellojpa.jpa;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaFlush {

    /**
     * flush
     *      영속성 컨텍스트의 변경 내역을 데이터베이스에 동기화;
     *          (쓰기 지연 SQL 저장소 내 저장된 SQL 만 RDBMS 에 전송후 비움)
     *      영속성 컨텍스트를 비우지 않음
     *          (이름때문에 혼동이 올수 있다.)
     *      트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨
     */
    public static void flush(EntityManager em) {
        System.out.println("==BEFORE flush==");
        em.flush();
        System.out.println("==AFTER flush==");
    }

    /**
     * jpql auto flush
     * jpql createQuery 로 조회하는 데이터는
     *      1차 캐시 내 엔티티를 조회하는 것이 아닌
     *      실제 RDBMS 에 데이터를 조회하는것이기 때문에,
     *      RDBMS 에 엔티티 변경사항들을 반영한 후에
     *      RDBMS 에서 jpql createQuery 조회를 할 수 있다.
     */
    public static List jpqlSelect(EntityManager em) {
        System.out.println("==BEFORE jpql==");
        List members = em.createQuery("select m from Member m").getResultList();
        System.out.println("==AFTER jpql==");
        return members;
    }
}
