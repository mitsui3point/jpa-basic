package hellojpa;

import hellojpa.entity.Member;

import java.util.List;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            List<Member> members = em.createQuery(
                    "select m from Member m where m.name like '%kim%'",
                            Member.class)
                    .getResultList();
            for (Member member : members) {
                System.out.println("member = " + member.getId());
            }
        });
    }
}
