package com.submarine29.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "com_seq")
    private Long id;
    private LocalDateTime orderDate;
    private String comment;
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @ManyToOne
    private User user;

    private String paymentId;
}
