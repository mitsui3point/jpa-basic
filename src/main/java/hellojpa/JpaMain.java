package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Order;
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

            Order orderA = new Order();
            orderA.setMember(memberA);
            orderA.setProduct(productA);
            em.persist(orderA);

            Order orderB = new Order();
            orderB.setMember(memberB);
            orderB.setProduct(productB);
            em.persist(orderB);

            em.flush();
            em.clear();
        });
    }
}
