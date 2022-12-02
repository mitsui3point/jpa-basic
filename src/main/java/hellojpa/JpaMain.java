package hellojpa;

import hellojpa.entity.AddressEntity;
import hellojpa.entity.Member;
import hellojpa.entity.embeddable.Address;

import java.util.List;
import java.util.Set;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            Member member = new Member("member1", new Address("homeCity", "street", "10000"));

            //값타입 Address -> AddressEntity 로 승급
            AddressEntity old1City = new AddressEntity("old1City", "street", "10000");
            member.getAddressHistory().add(old1City);
            AddressEntity old2City = new AddressEntity("old2City", "street", "10000");
            member.getAddressHistory().add(old2City);

            //값타입 사용시기; [치킨, 피자, 족발] 체크박스
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================ start ================");
            Member findMember = em.find(Member.class, member.getId());

//            //homeCity -> newCity
////            findMember.getHomeAddress().setCity("newCity");
//            Address original = findMember.getHomeAddress();
//            findMember.putHomeAddress(new Address("newCity", original.getStreet(), original.getZipcode()));
//
//            //치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            //old1City -> newCity
            AddressEntity findAddress = em.find(AddressEntity.class, old1City.getId());
            findAddress.setAddress(new Address("newCity", findAddress.getAddress().getStreet(), findAddress.getAddress().getZipcode()));

        });
    }
}
