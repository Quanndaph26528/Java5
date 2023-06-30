package com.demo.service;

import com.demo.model.Account;
import com.demo.repository.AccountRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public boolean login(String username, String password) {
        Account account = accountRepository.findById(username).orElse(null);
        return account != null && account.getClass().equals(password);
    }


    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account findById(String id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElse(null);
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void delete(String id) {
        accountRepository.deleteById(id);
    }
    public boolean checkCredentials(String username, String password) {
        Account account = accountRepository.findByUsername(username);

        // Kiểm tra xem người dùng có tồn tại hay không
        if (account == null) {
            return false;
        }

        // Kiểm tra mật khẩu
        return account.getPassword().equals(password);
    }
    public boolean isAdmin(String username) {
        Account account = accountRepository.findByUsername(username);
        return account != null && account.isAdmin();
    }

    public boolean registerUser(User user) {
        // Kiểm tra xem tên người dùng đã tồn tại chưa
        if (accountRepository.findByUsername(user.getUsername()) != null) {
            return false; // Tên người dùng đã tồn tại, đăng ký không thành công
        }

        // Tạo một đối tượng Account mới từ thông tin người dùng
        Account account = new Account();
        account.setUsername(user.getUsername());
        account.setPassword(user.getPassword());

        // Lưu đối tượng Account vào cơ sở dữ liệu
        accountRepository.save(account);

        return true; // Đăng ký thành công
    }
}

