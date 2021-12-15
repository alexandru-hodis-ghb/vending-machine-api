package mvp.vendingmachineapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActionIntegrationTests {

    @Test
    void buyWithSufficientFunds() {

    }

    @Test
    void buyWithInsufficientFunds() {

    }

    @Test
    void buyWithInsufficientProducts() {

    }

    @Test
    void buyWithWrongProduct() {

    }

    @Test
    void depositWithAcceptableAmount() {

    }

    @Test
    void depositWithUnacceptableAmount() {

    }

    @Test
    void calculateChangeShouldSplitAccordingly() {
        Assertions.assertEquals(5, calculateChange(5, 10).get(0), "10 - 5 should be 5");
        Assertions.assertEquals(0, calculateChange(5, 5).get(0), "5 - 5 should be 0");
        Assertions.assertEquals(Arrays.asList(10, 5).toString(), calculateChange(10, 25).toString(), "25 - 10 should be 15");
        Assertions.assertEquals(0, calculateChange(200, 200).get(0), "200 - 200 should be 0");
        Assertions.assertEquals(50, calculateChange(150, 200).get(0), "200 - 150 should be 50");
        Assertions.assertEquals(Arrays.asList(25, 10, 5).toString(), calculateChange(160, 200).toString(), "200 - 160 should be 40");
        Assertions.assertEquals(Arrays.asList(100, 50, 25, 5).toString(), calculateChange(20, 200).toString(), "200 - 20 should be 180");
    }

    public List<Integer> calculateChange(int price, int deposit) {
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
