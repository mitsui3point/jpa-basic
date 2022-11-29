package hellojpa;

import hellojpa.entity.Locker;
import hellojpa.entity.Member;
import hellojpa.entity.Team;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("memberA");
            em.persist(member);

            Locker locker = new Locker();
            locker.setName("locker");
            em.persist(locker);

            member.setLocker(locker);
            member.setTeam(team);

            locker.setMember(member);

            System.out.println("locker = " + locker.getId() + ", " + locker.getName());
            System.out.println("team = " + team.getId() + ", " + team.getName());
            System.out.println("member = " + member.getId() + ", " + member.getName());
            System.out.println("member.getLocker() = " + member.getLocker().getName());
            System.out.println("locker.getMember() = " + locker.getMember().getName());

            em.flush();
            em.clear();

        });
    }
}
