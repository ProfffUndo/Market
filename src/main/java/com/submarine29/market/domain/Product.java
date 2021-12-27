package com.submarine29.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "com_seq")
    private Long id;
    @Size(min = 1, max = 100)
    private String name;
    @Column(length = 4000)
    @Size(min = 10, max = 4000)
    private String description;
    private double price; //Валидируется на хосте в upsert_product на 25 строке.
    private String imagePath;
    @ManyToOne
    @JsonIgnore
    private Category category; //TODO выводить имя
//    @OneToMany
//    @JoinColumn(name = "product_id")
//    @JsonIgnore
//    private List<OrderItem> orderItems;
    @Max(10000)
    @Min(0)
    private int amount;

    @Min(0)
    private int popularity;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image=" + imagePath +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(id, product.id)
                && Objects.equals(name, product.name) && Objects.equals(description, product.description)
                && Objects.equals(imagePath, product.imagePath) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, imagePath, category);
    }

    public void addPopularity(int popularity){
        this.popularity+=popularity;
    }
}
