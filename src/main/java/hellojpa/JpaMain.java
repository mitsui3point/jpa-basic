package hellojpa;

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
            member.setTeam(team);
            em.persist(member);

            em.flush();//1차캐시 비우고 다시 조회하기 위한 목적
            em.clear();

            Member findMember = em.find(Member.class, member.getId());//member 1차캐시에서 제거됨, 1차캐시 재등록

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            Team newTeam = new Team();
            newTeam.setName("newTeam");
            em.persist(newTeam);

            findMember.setTeam(newTeam);

            Team findAfterSetTeam = em.find(Member.class,
                            member.getId())
                    .getTeam();
            System.out.println("findAfterSetTeam = " + findAfterSetTeam.getName());
        });
    }
}
