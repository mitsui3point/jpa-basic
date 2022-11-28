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
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setName("memberB");
            em.persist(memberB);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
            Member member1 = em.find(Member.class, memberA.getId());//1차 캐시
            Member member2 = em.find(Member.class, memberB.getId());//1차 캐시

            System.out.println("findTeam = " + findTeam.getName());
            System.out.println("member1 = " + member1.getName());
            System.out.println("member2 = " + member2.getName());
        });
    }
}
