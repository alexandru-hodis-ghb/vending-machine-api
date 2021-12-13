package mvp.vendingmachineapi.controllers;

import mvp.vendingmachineapi.dto.Buy;
import mvp.vendingmachineapi.dto.BuyResponse;
import mvp.vendingmachineapi.dto.Deposit;
import mvp.vendingmachineapi.dto.Reset;
import mvp.vendingmachineapi.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/actions")
public class ActionsController {

    @Autowired
    private ActionService actionService;

    @PostMapping("/deposit")
    public ResponseEntity<Integer> deposit(@RequestBody Deposit deposit) {
        return ResponseEntity.ok(actionService.deposit(deposit));
    }

    @PostMapping("/buy")
    public ResponseEntity<BuyResponse> buy(@RequestBody Buy buy) {
        return ResponseEntity.ok(actionService.buy(buy));
    }

    @GetMapping("/reset")
    public ResponseEntity<Reset> reset() {
        return ResponseEntity.ok(actionService.reset());
    }


}
