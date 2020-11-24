//package ru.yourhockey.model.account;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.util.Collection;
//
//import static ru.yourhockey.model.account.Account.ACCOUNT_TABLE;
//
//@Entity
//@Table(name = ACCOUNT_TABLE)
//@Getter
//@Setter
//public class Account implements UserDetails {
//    public static final String ACCOUNT_TABLE = "account";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Size(min = 2, message = "Не меньше 5 знаков")
//    private String accountName;
//    @Size(min = 2, message = "Не меньше 5 знаков")
//    private String password;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return accountName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
