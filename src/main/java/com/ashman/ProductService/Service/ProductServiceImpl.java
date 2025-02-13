package com.ashman.ProductService.Service;

import com.ashman.ProductService.Entity.Product;
import com.ashman.ProductService.Exception.ProductServiceCustomException;
import com.ashman.ProductService.Model.ProductRequest;
import com.ashman.ProductService.Model.ProductResponse;
import com.ashman.ProductService.Repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {

        log.info("Adding Product..");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        product = productRepository.save(product);

        log.info("Product created");

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {

        log.info("Get the Product for ProductId: {}", productId);
        Product product =
                productRepository.findById(productId)
                        .orElseThrow(()-> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);


        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduced Quantity {} for id: {}", quantity, productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"));
        if (product.getQuantity() < quantity){
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("Product Quantity updated Successfully");
    }
}
