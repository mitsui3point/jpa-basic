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
            Member findMember = em.find(Member.class, member.getId());

            //homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity");
            Address original = findMember.getHomeAddress();
            findMember.putHomeAddress(new Address("newCity", original.getStreet(), original.getZipcode()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //old1City -> newCity
            /**기본적으로 remove 대상을 찾을 때 equals() 메서드(Address @Override equals())를 사용.
             * Address 내에 equals(), hashCode() @Override 메서드가 구현되어있지 않으면 값비교를 제대로 하지 못해서 타겟 데이터가 삭제되지 않음*/
            Address old1City = new Address("old1City", "street", "10000");
            findMember.getAddressHistory().remove(old1City);
            findMember.getAddressHistory().add(new Address("newCity", old1City.getStreet(), old1City.getZipcode()));
        });
    }
}
