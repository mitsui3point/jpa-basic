package hellojpa;

import hellojpa.entity.cascade.Child;
import hellojpa.entity.cascade.Parent;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            Child child1 = new Child();
            child1.setName("child1");

            Child child2 = new Child();
            child2.setName("child2");

            Parent parent = new Parent();
            parent.setName("parent1");
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            System.out.println("========================");
            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);
            System.out.println("========================");
        });
    }
}
