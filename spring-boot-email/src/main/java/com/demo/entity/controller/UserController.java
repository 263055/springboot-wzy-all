package com.demo.entity.controller;

import com.demo.entity.Result;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guico
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService iUserService;

    private record LoginAndRegister(String email, String password, String code, String uuid) { }
    @PostMapping("/loginAndRegister")
    public Result loginAndRegister(@RequestBody LoginAndRegister request) {
        String email = request.email;
        String password = request.password;
        String code = request.code;
        String uuid = request.uuid;
        return iUserService.loginAndRegister(email, password, code, uuid);
    }

    @GetMapping("/activate")
    public Result activate(@RequestParam("token") String token) {
        return iUserService.activate(token);
    }

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result getCode() {
        return iUserService.getVerifyCode();
    }
}
