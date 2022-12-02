package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.embeddable.Address;

import java.util.List;
import java.util.Set;

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

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================ start ================");
            Member findMember = em.find(Member.class, member.getId());//지연로딩 member 만 로딩한다.

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            List<Address> addressHistories = findMember.getAddressHistory();
            for (Address addressHistory : addressHistories) {
                System.out.println("addressHistory = " + addressHistory.getCity());
            }

        });
    }
}
