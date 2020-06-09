package co.com.ias.certification.backend.product.repositories;

import co.com.ias.certification.backend.product.domain.Product;
import co.com.ias.certification.backend.product.domain.ProductId;
import co.com.ias.certification.backend.product.domain.ProductOperation;
import co.com.ias.certification.backend.product.domain.ProductoperationRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    ProductOperation insertOne(ProductoperationRequest productoperationRequest);
    ProductOperation findById (ProductId productId);
    List<Product> findAll();
    ProductOperation updateOne(ProductId productId, ProductoperationRequest productoperationRequest);
    ProductOperation deleteOne(ProductId productId);
}
