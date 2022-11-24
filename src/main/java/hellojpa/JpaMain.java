package hellojpa;

import hellojpa.constants.RoleType;
import hellojpa.entity.Member;
import hellojpa.jpa.JpaCRUD;
import hellojpa.jpa.JpaDetachEntity;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member = new Member();
            member.setId(3L);
            member.setUsername("member3");
            member.setRoleType(RoleType.GUEST);
            em.persist(member);
            Member roleTypeMember = em.find(Member.class, member.getId());
            RoleType roleType = member.getRoleType();
            System.out.println("roleTypeMember = " + roleTypeMember.getRoleType());
//            JpaCRUD.selectMember(em, 160L);
        });
    }
}
