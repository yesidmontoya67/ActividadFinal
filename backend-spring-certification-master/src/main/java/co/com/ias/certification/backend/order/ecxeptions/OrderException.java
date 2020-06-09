package co.com.ias.certification.backend.order.ecxeptions;

public abstract class OrderException extends RuntimeException{
    public OrderException(String message) {
        super(message);
    }
}
