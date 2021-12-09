package mvp.vendingmachineapi.mock;

import mvp.vendingmachineapi.dto.NewUser;
import mvp.vendingmachineapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

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
        HashSet<String> buyerRoles = new HashSet<>();
        buyerRoles.add("buyer");
        buyer.setRoles(buyerRoles);

        userService.createUser(buyer);

        NewUser seller = new NewUser();
        seller.setUsername("sellerr");
        seller.setPassword("sellerr");
        HashSet<String> sellerRoles = new HashSet<>();
        sellerRoles.add("seller");
        seller.setRoles(sellerRoles);

        userService.createUser(seller);
    }
}