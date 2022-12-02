package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static hellojpa.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create((em, emf) -> {
            //criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            //루트 클래스 (조회를 시작할 클래스)
            Root<Member> m = query.from(Member.class);

            //쿼리 생성
            CriteriaQuery<Member> cq = query.select(m);

            //동적 쿼리
            String username = "kim";
            if (username != null) {
                cq = cq.where(cb.equal(m.get("name"), username));
            }

            List<Member> members = em.createQuery(cq).getResultList();
            for (Member member : members) {
                System.out.println("member = " + member);
            }
        });
    }
}
