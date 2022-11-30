package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {

            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            //Member @ManyToOne hellojpa.entity.Team
            //Member @ManyToOne(fetch = FetchType.LAZY) hellojpa.entity.Team$HibernateProxy$biOUnHhi
            System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());

            System.out.println("======================");
            findMember.getTeam().getName();
            System.out.println("======================");
        });
    }
}
