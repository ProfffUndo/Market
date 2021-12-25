package com.submarine29.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "com_seq")
    private Long id;
    @Size(min=5, max=50)
    @Column(unique = true) //! не применяется почему-то, выполните 148 строку в data.sql
    private String name;
    @OneToMany
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
