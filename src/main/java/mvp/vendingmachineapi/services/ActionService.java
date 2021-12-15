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

import java.util.ArrayList;
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

                BuyResponse buyResponse = new BuyResponse();
                buyResponse.setProductId(product1.getId());
                buyResponse.setTotalSpent(buy.getAmountOfProducts() * product1.getCost());
                buyResponse.setChange(calculateChange(buy.getAmountOfProducts() * product1.getCost(), user.getDeposit()));

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
        reset.setChange(calculateChange(0, optionalUser.get().getDeposit()));

        optionalUser.get().setDeposit(0);
        userRepository.save(optionalUser.get());

        return reset;
    }

    private List<Integer> calculateChange(int price, int deposit) {
        int change = deposit - price;

        List<Integer> result = new ArrayList<>();

        if (change == 0 || change == 5 || change == 10 || change == 25 || change == 50 || change == 100) {
            result.add(change);
            return result;
        } if (change >= 100) {
            change = collectChange(change, result, 100);
        } if (change >= 50) {
            change = collectChange(change, result, 50);
        } if (change >= 25) {
            change = collectChange(change, result, 25);
        } if (change >= 10) {
            change = collectChange(change, result, 10);
        } if (change >= 5) {
            collectChange(change, result, 5);
        }

        return result;
    }

    private int collectChange(int change, List<Integer> result, int coin) {
        for (int i = change; i >= coin; i -= coin) {
            result.add(coin);
            change -= coin;
        }
        return change;
    }

}
