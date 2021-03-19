package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    /*
     * 다대다 연관관계: 중간 테이블로 연결
     * joinColumns: 중간 테이블에 있는 자신의 아이디
     * inverseJoinColumns: 중간 테이블에 있는 연관관계 매핑 아이디
     */
    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    /*
     * self 로 양방향 매핑
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    /*
     * self 로 양방향 매핑
     */
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
