package com.geekbrains.book.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilterParams {
    private Integer minPrice;
    private Integer maxPrice;
    private String titlePart;

}
