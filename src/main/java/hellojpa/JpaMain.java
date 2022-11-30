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
            member.setName("name");

            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());//1.데이터베이스를 통해서 실제 엔티티 객체 조회
            Member findMember = em.getReference(Member.class, member.getId());//2.데이터배이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회

            //1.hellojpa.entity.Member
            //2.hellojpa.entity.Member$HibernateProxy$FuPunnFY
            System.out.println("before  findMember.getClass() = " + findMember.getClass());
            System.out.println("findMember.name = " + findMember.getName());
            System.out.println("after   findMember.getClass() = " + findMember.getClass());
        });
    }
}
