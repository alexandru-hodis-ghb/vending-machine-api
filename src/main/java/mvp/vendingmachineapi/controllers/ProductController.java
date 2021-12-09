package mvp.vendingmachineapi.controllers;

import mvp.vendingmachineapi.dto.DeleteMessageResponse;
import mvp.vendingmachineapi.dto.NewProduct;
import mvp.vendingmachineapi.models.Product;
import mvp.vendingmachineapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> createProduct(@RequestBody NewProduct newProduct) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(newProduct));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @DeleteMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<DeleteMessageResponse> deleteProduct(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

}
