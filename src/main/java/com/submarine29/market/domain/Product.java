package com.submarine29.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;
    private byte[] image; //как хранить фото?
    @ManyToOne
    private Category category;
    @OneToMany
    @JoinColumn(name = "id")
    private List<OrderItem> orderItems;

    //  private int amount; //количество товара, пока вопрос высчитывать как-то?
    //характеристики? цвет/мощность...


}
