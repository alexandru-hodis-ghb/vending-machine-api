package mvp.vendingmachineapi.mock;

import mvp.vendingmachineapi.dto.NewUser;
import mvp.vendingmachineapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Mocker {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void addMocks() {
        addMockUsers();
    }

    private void addMockUsers() {
        NewUser buyer = new NewUser();
        buyer.setUsername("buyerr");
        buyer.setPassword("buyerr");
        userService.createUser(buyer);

        NewUser seller = new NewUser();
        seller.setUsername("sellerr");
        seller.setPassword("sellerr");
        userService.createUser(seller);
    }
}