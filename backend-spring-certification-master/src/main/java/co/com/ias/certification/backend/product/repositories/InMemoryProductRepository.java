package co.com.ias.certification.backend.product.repositories;

import co.com.ias.certification.backend.product.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> state = new HashMap<>();

    @Override
    public ProductOperation insertOne(ProductoperationRequest productoperationRequest) {
        Long id = state.size() + 1L;
        Product productCreated = Product.of(id,productoperationRequest.getName()
                ,productoperationRequest.getDescription()
                ,productoperationRequest.getBasePrice()
                ,productoperationRequest.getTaxRate()
                ,productoperationRequest.getProductStatus()
                ,productoperationRequest.getInventoryQueantity());
        state.put(id, productCreated);
        return ProductOperationSuccess.of(productCreated);
    }

    @Override
    public ProductOperation findById(ProductId productId) {
        return ProductOperationSuccess.of(state.get(productId.getValue()));
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public ProductOperation updateOne(ProductId productId, ProductoperationRequest productoperationRequest) {
        return null;
    }

    @Override
    public ProductOperation deleteOne(ProductId productId) {
        return null;
    }
}
