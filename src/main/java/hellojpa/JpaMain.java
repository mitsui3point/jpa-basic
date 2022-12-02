package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.embeddable.Address;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            Member member = new Member("member1", new Address("homeCity", "street", "10000"));

            member.getAddressHistory().add(new Address("old1City", "street", "10000"));
            member.getAddressHistory().add(new Address("old2City", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            //값타입 collection 도 엔티티 내부에 String, int 필드 처럼 member 엔티티 하나에 소속되어 저장, 수정, 삭제된다.
            //그래서 값타입 collection 은 영속성 전이(Cascade), 고아 객체 제거 기능을 필수로 가지고 있다고 볼 수 있다.
            em.persist(member);
        });
    }
}
