package com.ashman.ProductService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductRequest {

    private String name;
    private long price;
    private long quantity;
}
