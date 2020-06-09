package co.com.ias.certification.backend.order.controllers;

import co.com.ias.certification.backend.configuration.security.Identity;
import co.com.ias.certification.backend.order.domain.*;
import co.com.ias.certification.backend.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class OrderContoroller {

    @Autowired
    private HttpServletRequest request;

    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderOperation> createOrder(@RequestBody OrderRequest orderBody){
        OrderOperation orderOperation= orderService.createOrder(orderBody);
        if (orderOperation.isValid()){
            return ResponseEntity.ok(OrderOperationSuccess.of(orderOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OrderOperationFailure.of(orderOperation.failure()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id){
        OrderOperation orderOperation= orderService.findOrderById(OrderId.of(id));
        if (orderOperation.isValid()){
            return ResponseEntity.ok(orderOperation.value());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(null);
    }

    @GetMapping("/")
    public List<Order> findAllOrders(){
        return orderService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderOperation> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest){
        OrderOperation orderOperation= orderService.updateOrder(OrderId.of(id),orderRequest);
        if (orderOperation.isValid()){
            return ResponseEntity.ok(OrderOperationSuccess.of(orderOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OrderOperationFailure.of(orderOperation.failure()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderOperation> deleteOrder(@PathVariable Long id){
        OrderOperation orderOperation= orderService.deleteOrder(OrderId.of(id));
        if (orderOperation.isValid()){
            return ResponseEntity.ok(OrderOperationSuccess.of(orderOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OrderOperationFailure.of(orderOperation.failure()));
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("identity", new Identity(getKeycloakSecurityContext()));

    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }


}
