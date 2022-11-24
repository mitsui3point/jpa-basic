package hellojpa.jpa;

import hellojpa.entity.MemberJpa;

import javax.persistence.EntityManager;

public class JpaPersistence {

    /**
     * 비영속 -> 영속
     * entityManager.persist()
     */
    public static MemberJpa persist(EntityManager em, long id, String name) {
        //비영속 상태
        MemberJpa memberJpa = new MemberJpa(id, name);

        //영속
        System.out.println("=== BEFORE persist ===");
        em.persist(memberJpa);
        System.out.println("=== AFTER persist ===");
        return memberJpa;
    }

    /**
     * 영속 -> 영속성 컨텍스트에서 분리, 준영속 상태
     */
    public static void detach(EntityManager em, MemberJpa persistedMemberJpa) {
        em.detach(persistedMemberJpa);
    }

    /**
     * 영속 -> 객체 삭제 및 RDB 데이터 제거
     */
    public static void remove(EntityManager em, MemberJpa persistedMemberJpa) {
        em.remove(persistedMemberJpa);
    }

    /**
     * 영속성 컨텍스트 1차 캐시 find()
     */
    public static void firstCacheFind(EntityManager em, long id) {
        System.out.println("==1st find==");
        em.find(MemberJpa.class, id);
        System.out.println("==//1st find==\n");

        System.out.println("==2nd find==");
        em.find(MemberJpa.class, id);
        System.out.println("==//2nd find==");
    }

    /**
     * 영속 엔티티 동일성 보장
     * (== 비교 보장; 같은 레퍼런스를 할당받은 변수 비교와 같은 맥락)
     */
    public static void findOneEqual(EntityManager em, long id) {
        MemberJpa memberJpa1 = em.find(MemberJpa.class, id);
        MemberJpa memberJpa2 = em.find(MemberJpa.class, id);
        System.out.println("member1 == member2: " + (memberJpa1 == memberJpa2));
    }

    /**
     * 영속 엔티티 트랜잭션을 지원하는 쓰기지연
     * (== 비교 보장; 같은 레퍼런스를 할당받은 변수 비교와 같은 맥락)
     */
    public static void transactionalWriteBehind(EntityManager em, MemberJpa memberJpa1, MemberJpa memberJpa2) {
        em.persist(memberJpa1);
        em.persist(memberJpa2);
        System.out.println("==persist end==");
    }

    /**
     * 영속 엔티티 수정
     * 1.flush() ->
     *      1차 캐시 조회
     *          엔티티(setName()하여 변경된)와
     *          스냅샷(em.find()하여 얻어낸 초기 조회값) 비교 ->
     *      변경된 컬럼 대상으로 update sql 생성 ->
     *      쓰기 지연 SQL 저장소에 update sql 저장 ->
     *      flush -> commit
     */
    public static void setEntity(EntityManager em, long id, String name) {
        //영속 엔티티 조회
        MemberJpa memberJpa = em.find(MemberJpa.class, id);
        //영속 엔티티 데이터 수정
        memberJpa.setName(name);
        System.out.println("==set name end==");
    }
}
