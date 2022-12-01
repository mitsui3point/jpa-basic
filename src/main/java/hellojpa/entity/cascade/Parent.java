package hellojpa.entity.cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /**
     * cascade 사용시 주의사항
     *  - 라이프 사이클이 동일할때 사용할 것.
     *      - Parent 와 Child 등록수정 라이프사이클이 거의 유사할때.
     *  - Parent 가 Child 의 단일 소유자 일 때 사용할 것.
     *      - Parent 하나 '만' Child 를 관리하는 경우: 사용가능
     *      - Parent 뿐 아닌 다른 부모 엔티티가 Child 를 관리하는 경우: 사용하면 안됨
     *      - 예시: 게시판과 게시판 하나에 종속된 첨부파일 엔티티
     *              여러개의 게시판에 종속된 첨부파일 엔티티는 사용하면 안됨.
     *  (위 두가지 조건이 만족할때만 사용을 고려할 것, 실무에서 꽤 쓰이지만 조심해서 사용해야 한다.)
     *
     * 고아(orphan) 객체 사용시 주의사항
     *  - 참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아 객체로 보고 삭제하는 기능
     *  - 참조하는 곳이 하나일 때 사용해야함!
     *  - 예시: 게시판과 게시판 하나에 종속된 첨부파일 엔티티
     *          여러개의 게시판에 종속된 첨부파일 엔티티는 사용하면 안됨.
     *  - 특정 엔티티가 개인 소유할 때 사용
     *  - @OneToOne, @OneToMany만 가능
     *  - 참고: 개념적으로 부모를 제거하면 자식은 고아가 된다.
     *          따라서 고아 객체 제거 기능을 활성화 하면,
     *          부모를 제거할 때 자식도 함께 제거된다.
     *          이것은 CascadeType.REMOVE처럼 동작한다.
     *
     * 영속성 전이 + 고아 객체, 생명주기
     *  - CascadeType.ALL + orphanRemovel=true
     *  - 스스로 생명주기를 관리하는 엔티티는 em.persist()로 영속화, em.remove()로 제거
     *  - 두 옵션을 모두 활성화 하면 부모 엔티티를 통해서 자식의 생명주기를 관리할 수 있음
     *  - 도메인 주도 설계(DDD)의 Aggregate Root개념을 구현할 때 유용
     *      - Aggregate Root
     *          : DDD Aggregate root 구현 개념
     *          : Repository 는 Aggregate root 만 contact 하고, 나머지는 Repository를 만들지 않는 것이 더 낫다.
     *          : 참고; https://eocoding.tistory.com/36
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildList() {
        return childList;
    }

    //== 연관관계 편의 메서드 ==//

    public void addChild(Child child) {
        this.childList.add(child);
        child.setParent(this);
    }
}
