package com.ashman.ProductService.Service;

import com.ashman.ProductService.Model.ProductRequest;
import com.ashman.ProductService.Model.ProductResponse;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
