package mvp.vendingmachineapi.services;

import mvp.vendingmachineapi.dto.DeleteMessageResponse;
import mvp.vendingmachineapi.dto.NewProduct;
import mvp.vendingmachineapi.exceptions.ProductNotFoundException;
import mvp.vendingmachineapi.models.Product;
import mvp.vendingmachineapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(NewProduct newProduct) {
        return null;
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public DeleteMessageResponse deleteProduct(Long id) {
        productRepository.deleteById(id);
        return new DeleteMessageResponse();
    }
}
