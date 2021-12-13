package mvp.vendingmachineapi.services;

import mvp.vendingmachineapi.dto.DeleteMessageResponse;
import mvp.vendingmachineapi.dto.NewProduct;
import mvp.vendingmachineapi.exceptions.ProductNotFoundException;
import mvp.vendingmachineapi.exceptions.UnauthorizedException;
import mvp.vendingmachineapi.exceptions.UserNotFoundException;
import mvp.vendingmachineapi.models.Product;
import mvp.vendingmachineapi.models.User;
import mvp.vendingmachineapi.repositories.ProductRepository;
import mvp.vendingmachineapi.repositories.UserRepository;
import mvp.vendingmachineapi.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("SELLER")
    public Product createProduct(NewProduct newProduct) {
        Product product = new Product();
        product.setAmountAvailable(newProduct.getAmountAvailable());
        product.setCost(newProduct.getCost());
        product.setProductName(newProduct.getProductName());
        product.setSellerId(newProduct.getSellerId());

        return productRepository.save(product);
    }

    @PreAuthorize("SELLER")
    public Product updateProduct(Product product) {
        Optional<User> loggedUser = userRepository.findOneByUsername(SecurityUtils.getCurrentUserUsername());

        if (loggedUser.isPresent()) {
            if (!product.getSellerId().equals(loggedUser.get().getId())) {
                throw new UnauthorizedException();
            } else {
                return productRepository.save(product);
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @PreAuthorize("SELLER")
    public DeleteMessageResponse deleteProduct(Long id) {
        Optional<User> loggedUser = userRepository.findOneByUsername(SecurityUtils.getCurrentUserUsername());
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            if (loggedUser.isPresent()) {
                if (!product.get().getSellerId().equals(loggedUser.get().getId())) {
                    throw new UnauthorizedException();
                } else {
                    productRepository.deleteById(id);
                    return new DeleteMessageResponse();
                }
            } else {
                throw new UserNotFoundException();
            }
        } else {
            throw new ProductNotFoundException();
        }
    }
}
