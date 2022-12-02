package hellojpa;

import hellojpa.entity.embeddable.Address;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {

        });
    }
}
