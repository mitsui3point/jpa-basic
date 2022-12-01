package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.embeddable.Address;
import hellojpa.entity.embeddable.Period;

import java.time.LocalDateTime;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            Address address = new Address("city", "address", "zipcode");
            Address newAddress = new Address("newcity", address.getAddress(), address.getZipcode());//값 타입 복사

            Member member1 = new Member();
            member1.setName("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setHomeAddress(newAddress);
            em.persist(member2);

            em.flush();
            em.clear();

            //member1, member2 의도된 값이 입력됨
            System.out.println("member1.getHomeAddress().getCity() = " + member1.getHomeAddress().getCity());
            System.out.println("member2.getHomeAddress().getCity() = " + member2.getHomeAddress().getCity());

        });
    }
}
