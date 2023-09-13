package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;
import com.demo.entity.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2023-06-12
 */
public interface UserService extends IService<User> {
    Result loginAndRegister(String email, String password, String code, String uuid);

    Result activate(String token);

    Result getVerifyCode();

    Result resetPasswordByEmail(String token);
}
