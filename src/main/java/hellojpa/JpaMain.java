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
            memberA.setTeam(team);//연관관계의 주인에 값 설정
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setName("memberB");
            memberB.setTeam(team);//연관관계의 주인에 값 설정
            em.persist(memberB);

            team.getMembers().add(memberA);
            team.getMembers().add(memberB);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
            System.out.println("=========================1");
            List<Member> findTeamMembers = findTeam.getMembers(); //값이 없음; 영속성 컨텍스트에 값을 넣을때 line 14~16의 상태 그대로 들어가있음. 연관관계 매핑이 된 members 는 없는 상태
            System.out.println("=========================2");
            for (Member findTeamMember : findTeamMembers) {
                System.out.println("findTeamMember = " + findTeamMember.getName());
            }
            System.out.println("=========================3");
        });
    }
}
