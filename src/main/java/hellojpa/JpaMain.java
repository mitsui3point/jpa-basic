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

            team.addMember(memberA);
            team.addMember(memberB);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
            List<Member> findTeamMembers = findTeam.getMembers();

            System.out.println("findTeam = " + findTeam.getName());
            for (Member findTeamMember : findTeamMembers) {
                System.out.println("findTeamMember = " + findTeamMember.getName());
            }
        });
    }
}
