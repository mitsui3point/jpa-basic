package hellojpa.entity.cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Child {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

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

    public Parent getParent() {
        return parent;
    }

    //== 연관관계 편의 메서드 ==//
    public void setParent(Parent parent) {
        this.parent = parent;
    }

}
