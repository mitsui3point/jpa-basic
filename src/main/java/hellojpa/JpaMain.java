package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import java.util.List;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member memberA = new Member();
            memberA.setName("memberA");
            memberA.setTeam(team);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setName("memberB");
            memberB.setTeam(team);
            em.persist(memberB);

            em.flush();//1차캐시 비우고 다시 조회하기 위한 목적
            em.clear();

            Member findMember = em.find(Member.class, memberA.getId());
            List<Member> members = findMember.getTeam().getMembers();//member -> team -> member ; 양방향 연관관계
            for (Member m : members) {
                System.out.println("member = " + m.getName());
            }
        });
    }
}
