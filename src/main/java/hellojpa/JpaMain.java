package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.embeddable.Address;
import hellojpa.entity.embeddable.Period;

import java.time.LocalDateTime;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            Member member = new Member();
            member.setName("member");
            member.setHomeAddress(new Address("city","addr","zipcode"));
            member.setWorkPeriod(new Period(LocalDateTime.now(), LocalDateTime.of(2023, 03, 22, 10, 00)));
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getHomeAddress());
        });
    }
}
