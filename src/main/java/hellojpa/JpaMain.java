package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Product;

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

            Product productA = new Product();
            productA.setName("productA");
            em.persist(productA);

            Product productB = new Product();
            productB.setName("productB");
            em.persist(productB);

            memberA.getProducts().add(productA);
            memberA.getProducts().add(productB);
            memberB.getProducts().add(productA);
//            memberB.getProducts().add(productB);
//            productA.getMembers().add(memberA);
//            productA.getMembers().add(memberB);
//            productB.getMembers().add(memberA);
//            productB.getMembers().add(memberB);

            em.flush();
            em.clear();



        });
    }
}
