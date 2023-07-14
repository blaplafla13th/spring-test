package test.ngocpt.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.ngocpt.auth.dto.request.RegisterForm;
import test.ngocpt.auth.dto.response.BaseResponse;
import test.ngocpt.auth.service.AuthService;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
    @Autowired
    AuthService service;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public BaseResponse register(@RequestBody RegisterForm data){
       return service.register(data);
    }
}
