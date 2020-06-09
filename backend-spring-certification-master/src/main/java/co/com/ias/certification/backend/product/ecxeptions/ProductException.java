package co.com.ias.certification.backend.product.ecxeptions;

public abstract class ProductException extends RuntimeException{
    public ProductException(String message) {
        super(message);
    }
}
