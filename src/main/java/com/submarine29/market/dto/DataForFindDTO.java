package com.submarine29.market.dto;

import com.submarine29.market.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataForFindDTO {
    private String name;
    private Double priceFrom;
    private Double priceTo;
    private Long categoryId;
    private String sort;
    private Category category;

    public String getPriceFrom() {
        if (priceFrom != null) {
            String priceFromString = priceFrom + "";
            priceFromString = priceFromString.substring(0, priceFromString.lastIndexOf("."));
            return priceFromString;
        }
        return "";
    }

    public String getPriceTo() {
        if (priceTo != null) {
            String priceToString = priceTo + "";
            priceToString = priceToString.substring(0, priceToString.lastIndexOf( "."));
            return priceToString;
        }
        return "";
    }

    public String getCategory() {
        if (category != null) {
            return category.getName();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getUrlForSort());
        if (sort != null && !sort.equals("")) {
            sb.append("sort=").append(sort);
        }
        return sb.toString();
    }

    public String getUrlForSort() {
        StringBuilder sb = new StringBuilder();
        if (name != null && !name.equals("")) {
            byte ptext[] = name.getBytes();
            sb.append("name=").append(new String(ptext, StandardCharsets.UTF_8)).append("&");
        }
        if (priceFrom != null) {
            sb.append("from=").append(priceFrom).append("&");
        }
        if (priceTo != null) {
            sb.append("to=").append(priceTo).append("&");
        }
        if (categoryId != null) {
            sb.append("category_id=").append(categoryId).append("&");
        }
        return sb.toString();
    }
}
