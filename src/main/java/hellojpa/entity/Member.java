package hellojpa.entity;

import hellojpa.constants.RoleType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//테이블 컬럼 unique 제약조건 @Column unique = true 대신 보편적으로 사용한다.
//@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_name", columnNames = "name")})
public class Member {
    @Id
    private Long id;

    @Column(
            name = "name",
//            insertable = false,   //컬럼등록제한
//            updatable = false,    //컬럼수정제한
//            length = 200,         //컬럼길이제한
//            unique = true,        //컬럼 unique 제약조건;UK_ektea7vp6e3low620iewuxhlq; 이름 제어가 불가능해서 잘 안쓴다.
            columnDefinition = "varchar(100) default 'EMPTY'", //제약조건 DDL 직접 입력
            nullable = false
    )
    private String username;

    //    @Column(precision = 20, scale = 2)//20자리 소숫점을 포함 전체 자릿수, 소수점 2자리
//    private BigDecimal age;
    private int age;

    @Enumerated//EnumType.ORDINAL 절대쓰지않는 옵션
//    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;

    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient
    private String temp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
