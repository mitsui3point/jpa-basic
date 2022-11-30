package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import java.util.List;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {

            Team teamA = new Team();
            teamA.setName("team1");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("team2");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setName("member1");
            memberA.setTeam(teamA);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setName("member2");
            memberB.setTeam(teamB);
            em.persist(memberB);

            em.flush();
            em.clear();


            //N+1 문제 fetch join 으로 해결
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

            //SQL: select * from member,
            //SQL: select * from team where TEAM_ID = xxx,
            //  EAGER 이므로 team 도 필요함.
            //  member 의 조회된 건수만큼의 team 테이블 쿼리가 나간다.


        });
    }
}
