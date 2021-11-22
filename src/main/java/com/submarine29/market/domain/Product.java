package com.submarine29.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(length = 4000)
    private String description;
    private double price;
    private byte[] image; //как хранить фото?
    @ManyToOne
    @JsonIgnore
    private Category category; //TODO выводить имя
    @OneToMany
    @JoinColumn(name = "id")
    @JsonIgnore
    private List<OrderItem> orderItems;

    //  private int amount; //количество товара, пока вопрос высчитывать как-то?
    //характеристики? цвет/мощность...


}
