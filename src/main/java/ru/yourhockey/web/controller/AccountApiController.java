package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.account.Account;
import ru.yourhockey.service.account.AccountService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountApiController implements AccountApi {

    private final AccountService accountService;

    @PostMapping("/registration")
    public ResponseEntity<Account> addAccount(@Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build(); // перепилить в норм вид)
        }
        return ResponseEntity.ok(accountService.saveAccount(account));
    }
}
