package test.ngocpt.auth.service;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import test.ngocpt.auth.dto.request.RegisterForm;
import test.ngocpt.auth.dto.response.BaseResponse;
import test.ngocpt.auth.dto.response.MailResponse;
import test.ngocpt.auth.entity.Role;
import test.ngocpt.auth.entity.User;
import test.ngocpt.auth.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    UserRepository repository;
    @Autowired
    MailService mailService;
    public BaseResponse register(RegisterForm data){
        if (repository.existsUserByEmail(data.getEmail()) || repository.existsUserByUsername(data.getUsername()))
            throw new IllegalArgumentException("Account Existed");
        User user = new User();
        BeanUtils.copyProperties(data,user,"password");
        user.setPassword(hashPass(data.getPassword()));
        user.setRole(Role.UNAUTHORIZED.getValue());
        repository.save(user);
        HashMap<String,String> body = new HashMap<>();
        body.put("username", user.getUsername());
        body.put("uuid",user.getId().toString());
        mailService.sendEmail(new MailResponse()
            .withRecipient(user.getEmail())
            .withSubject("[Blaplafla] Activation Account")
            .withContent(body,"active.html")
        );
        return new BaseResponse(HttpStatus.OK.value(), "Created account successfully");
    }

    @Value(value = "${spring.password.salt}")
    private String salt;
    @SneakyThrows
    private String hashPass(String password) {
        password = salt+password+salt;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] encodedhash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        encodedhash = sha256.digest(encodedhash);
        return new String(encodedhash);
    }
}
