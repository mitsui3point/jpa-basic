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
            team.getMembers().add(memberA);//역방향(주인이 아닌 방향)만 연관관계 설정
            team.getMembers().add(memberB);//역방향(주인이 아닌 방향)만 연관관계 설정
            em.persist(team);

            em.flush();
            em.clear();

            /**
             * RDB 확인
             * 		: memberA.getTeam() == null 이므로
             * 		  rollback 되어 sysout 으로 확인 불가
             * ============================================================
             * SELECT * FROM MEMBER ;
             * MEMBER_ID	USERNAME	TEAM_ID
             * 1			memberA 	null
             * 2			memberB 	null
             * ============================================================
             * SELECT * FROM TEAM ;
             * TEAM_ID		TEAM_NAME
             * 3			teamA
             * ============================================================
             */
        });
    }
}
