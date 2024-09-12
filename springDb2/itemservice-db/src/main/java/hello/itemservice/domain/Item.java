package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_name",length = 10)
    // 사실 이것도 안적어도됨 스프링부트에서 카멜하고 언더스코어 알아서 보고 매핑 처리해줌
    private String itemName;

    // 컬럼명이랑 테이블명 같으면 @Column 으로 해서 적용 따로 안해도됨
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
