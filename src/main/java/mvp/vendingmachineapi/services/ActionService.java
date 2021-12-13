package mvp.vendingmachineapi.services;

import mvp.vendingmachineapi.dto.Buy;
import mvp.vendingmachineapi.dto.BuyResponse;
import mvp.vendingmachineapi.dto.Deposit;
import mvp.vendingmachineapi.dto.Reset;
import mvp.vendingmachineapi.exceptions.*;
import mvp.vendingmachineapi.models.Product;
import mvp.vendingmachineapi.models.User;
import mvp.vendingmachineapi.repositories.ProductRepository;
import mvp.vendingmachineapi.repositories.UserRepository;
import mvp.vendingmachineapi.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ActionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    private final List<Integer> acceptableAmounts = Arrays.asList(5, 10, 20, 50, 100);

    @PreAuthorize("BUYER")
    public Integer deposit(Deposit deposit) {
        Optional<User> loggedUser = userRepository.findOneByUsername(SecurityUtils.getCurrentUserUsername());

        if (loggedUser.isPresent()) {
            if (!acceptableAmounts.contains(deposit.getAmount())) {
                throw new UnacceptableAmount();
            } else {
                loggedUser.get().setDeposit(loggedUser.get().getDeposit() + deposit.getAmount());
                return userRepository.save(loggedUser.get()).getDeposit();
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    @PreAuthorize("BUYER")
    public BuyResponse buy(Buy buy) {
        Optional<User> optionalUser = userRepository.findOneByUsername(SecurityUtils.getCurrentUserUsername());
        Optional<Product> optionalProduct = productRepository.findById(buy.getProductId());

        if (optionalUser.isPresent()) {
            if (optionalProduct.isEmpty()) {
                throw new ProductNotFoundException();
            } else {
                User user = optionalUser.get();
                Product product1 = optionalProduct.get();
                if (product1.getAmountAvailable() < buy.getAmountOfProducts()) throw new InsufficientProducts();
                if (user.getDeposit() < (buy.getAmountOfProducts() * product1.getCost())) throw new InsufficientFunds();

                // TODO calculate change
                BuyResponse buyResponse = new BuyResponse();
                buyResponse.setProductId(product1.getId());
                buyResponse.setTotalSpent(buy.getAmountOfProducts() * product1.getCost());
                buyResponse.getChange().add(user.getDeposit() - buy.getAmountOfProducts() * product1.getCost());

                return buyResponse;
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    @PreAuthorize("BUYER")
    public Reset reset() {
        Optional<User> optionalUser = userRepository.findOneByUsername(SecurityUtils.getCurrentUserUsername());
        if (optionalUser.isEmpty()) throw new UserNotFoundException();

        Reset reset = new Reset();
        reset.getChange().add(optionalUser.get().getDeposit());

        optionalUser.get().setDeposit(0);
        userRepository.save(optionalUser.get());

        return reset;
    }
}
