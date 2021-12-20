package com.submarine29.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataForFindDTO {
    private String name;
    private Double priceFrom;
    private Double priceTo;
    private Long categoryId;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null && !name.equals("")) {
            sb.append("name=").append(name).append("&");
        }
        if (priceFrom != null) {
            sb.append("from=").append(priceFrom).append("&");
        }
        if (priceTo != null) {
            sb.append("to=").append(priceTo).append("&");
        }
        if (categoryId != null) {
            sb.append("category_id=").append(categoryId);
        }
        if (sb.toString().endsWith("&")) {
            sb.delete(sb.length(), sb.length());
        }
        return sb.toString();
    }
}
