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
     *      - Parent 와 Child 등록수정 라이프사이클이 거의 유사할때.(그냥 같을때)
     *  - Parent 가 Child 의 단일 소유자 일 때 사용할 것.
     *      - Parent 하나 '만' Child 를 관리하는 경우: 사용가능
     *      - Parent 뿐 아닌 다른 부모 엔티티가 Child 를 관리하는 경우: 사용하면 안됨
     *      - 예시: 게시판과 게시판 하나에 종속된 첨부파일 엔티티
     *              여러개의 게시판에 종속된 첨부파일 엔티티는 사용하면 안됨.
     *  (위 두가지 조건이 만족할때만 사용을 고려할 것, 실무에서 꽤 쓰이지만 조심해서 사용해야 한다.)
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
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
