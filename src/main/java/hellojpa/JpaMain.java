package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import java.util.List;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {

            Member memberA = new Member();
            memberA.setName("memberA");
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setName("memberB");
            em.persist(memberB);

            Team team = new Team();
            team.setName("teamA");

            // create one-to-many row hellojpa.entity.Team.members
            team.getMembers().add(memberA);
            team.getMembers().add(memberB);

            memberA.setTeam(team);
            memberB.setTeam(team);

            em.persist(team);

//            em.flush();
//            em.clear();

            Member findMemberA = em.find(Member.class, memberA.getId());
            System.out.println("findMemberA.getTeam() = " + findMemberA.getTeam().getName());
        });
    }
}
