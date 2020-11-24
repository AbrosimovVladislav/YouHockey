//package ru.yourhockey.service.account;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import ru.yourhockey.model.account.Account;
//import ru.yourhockey.repo.account.AccountRepo;
//import ru.yourhockey.web.UserAlreadyExistsException;
//
//@Service
//@RequiredArgsConstructor
//public class AccountService implements UserDetailsService {
//
//    private final AccountRepo accountRepo;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
//        return accountRepo.findByAccountName(accountName).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
//    }
//
//    public Account saveAccount(Account account) {
//        accountRepo.findByAccountName(account.getUsername()).ifPresent(e -> {
//            throw new UserAlreadyExistsException("Account already exists");
//        });
//
//        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
//        return accountRepo.save(account);
//    }
//
//}
