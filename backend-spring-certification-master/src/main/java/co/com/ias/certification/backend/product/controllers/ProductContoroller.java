package co.com.ias.certification.backend.product.controllers;

import co.com.ias.certification.backend.configuration.security.Identity;
import co.com.ias.certification.backend.product.domain.*;
import co.com.ias.certification.backend.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ProductContoroller {

    @Autowired
    private HttpServletRequest request;

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductOperation> createProduct(@RequestBody ProductoperationRequest productBody){
        ProductOperation productOperation= productService.createProduct(productBody);
        if (productOperation.isValid()){
            return ResponseEntity.ok(ProductOperationSuccess.of(productOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProductOperationFailure.of(productOperation.failure()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        ProductOperation productOperation= productService.findProductById(ProductId.of(id));
        if (productOperation.isValid()){
            return ResponseEntity.ok(productOperation.value());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(null);
    }

    @GetMapping("/")
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOperation> updateProducte(@PathVariable Long id, @RequestBody ProductoperationRequest productoperationRequest){
        ProductOperation productOperation= productService.updateProduct(ProductId.of(id),productoperationRequest);
        if (productOperation.isValid()){
            return ResponseEntity.ok(ProductOperationSuccess.of(productOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProductOperationFailure.of(productOperation.failure()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductOperation> deleteProduct(@PathVariable Long id){
        ProductOperation productOperation= productService.deleteProduct(ProductId.of(id));
        if (productOperation.isValid()){
            return ResponseEntity.ok(ProductOperationSuccess.of(productOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProductOperationFailure.of(productOperation.failure()));
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("identity", new Identity(getKeycloakSecurityContext()));

    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }


}
