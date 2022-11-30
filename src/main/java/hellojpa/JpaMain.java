package hellojpa;

import hellojpa.entity.Member;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());//Proxy
            Member findMember = em.getReference(Member.class, member.getId());//Proxy
            System.out.println("findMember1 = " + findMember.getClass());

//            em.detach(findMember);
            em.clear();

            findMember.getName();//영속성 컨텍스트에서 detach 된 준영속 객체는 영속성 컨텍스트의 도움을 받지 못한다.(org.hibernate.LazyInitializationException: could not initialize proxy)
        });
    }
}
