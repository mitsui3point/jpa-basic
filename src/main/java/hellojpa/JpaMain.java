package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.item.Item;
import hellojpa.entity.item.Movie;

import java.time.LocalDateTime;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member = new Member();
            member.setName("member1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getCreatedBy());
            System.out.println("findMember = " + findMember.getCreatedDate());
        });
    }
}
