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

            em.flush();
            em.clear();

            /**
             * RDB 확인
             * 		: memberA.getTeam() == null 이므로
             * 		  rollback 되어 sysout 으로 확인 불가
             * ============================================================
             * SELECT * FROM MEMBER ;
             * MEMBER_ID	USERNAME	TEAM_ID
             * 2			memberA		1(지정됨)
             * 3			memberB		1(지정됨)
             * ============================================================
             * SELECT * FROM TEAM ;
             * TEAM_ID	TEAM_NAME
             * 1		teamA
             */
        });
    }
}
