package com.submarine29.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

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
    @ColumnDefault("0") //Пришлось добавить, так как не хотел делать alter table для вставки доп столбца
    private int categoryId;

    //  private int amount; //количество товара, пока вопрос высчитывать как-то?
    //характеристики? цвет/мощность...


}
