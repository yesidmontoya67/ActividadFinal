package co.com.ias.certification.backend.product.services;

import co.com.ias.certification.backend.product.domain.Product;
import co.com.ias.certification.backend.product.domain.ProductId;
import co.com.ias.certification.backend.product.domain.ProductOperation;
import co.com.ias.certification.backend.product.domain.ProductoperationRequest;
import co.com.ias.certification.backend.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService( ProductRepository repository) {
        this.repository = repository;
    }

    public ProductOperation createProduct(ProductoperationRequest productoperationRequest){
        return repository.insertOne(productoperationRequest);
    }

    public ProductOperation findProductById(ProductId productId) {
        return repository.findById(productId);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public ProductOperation updateProduct(ProductId productId, ProductoperationRequest productoperationRequest) {
        return repository.updateOne(productId,productoperationRequest);
    }

    public ProductOperation deleteProduct(ProductId productId) {
        return repository.deleteOne(productId);
    }
}
