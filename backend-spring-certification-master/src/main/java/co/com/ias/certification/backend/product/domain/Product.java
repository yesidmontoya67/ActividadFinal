package co.com.ias.certification.backend.product.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Product {
    Long id;
    Name name;
    Description description;
    BasePrice basePrice;
    TaxRate taxRate;
    ProductStatus productStatus;
    InventoryQueantity inventoryQueantity;


    
}
