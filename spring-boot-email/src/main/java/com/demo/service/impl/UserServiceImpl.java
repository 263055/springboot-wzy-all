package com.demo.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.User;
import com.demo.entity.Result;
import com.demo.mapper.UserMapper;
import com.demo.service.UserService;
import com.demo.util.*;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.thymeleaf.context.Context;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2023-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Value("${deploy.host}")
    private String host;
    @Value("${deploy.pathForRegister}")
    private String registerPath;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Resource
    private RedisCache redisCache;

    @Resource
    private JWTGenerator jwtGenerator;

    @Resource
    private EmailUtil emailUtil;

    @Override
    public Result loginAndRegister(String email, String password, String code, String uuid) {
        // 验证码校验
        validateCaptcha(code, uuid);
        boolean exist = query().eq("email", email).exists();
        // 如果存在，登录；如果不存在，注册
        if (exist) {
            return login(email, password);
        } else {
            return register(email, password);
        }
    }

    @Override
    public Result getVerifyCode() {
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr, code;
        BufferedImage image;

        // 生成验证码
        String capText = captchaProducerMath.createText();
        capStr = capText.substring(0, capText.lastIndexOf("@"));
        code = capText.substring(capText.lastIndexOf("@") + 1);
        image = captchaProducerMath.createImage(capStr);

        redisCache.setVerifyCode(verifyKey, code);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return Result.fail(e.getMessage());
        }
        HashMap<String, String> res = new HashMap<>();
        res.put("uuid", uuid);
        res.put("img", Base64.encode(os.toByteArray()));
        return Result.success(res, "success");
    }

    @Override
    public Result resetPasswordByEmail(String token) {
        // 根据email从redis中获取信息
        String id = jwtGenerator.getIdFromToken(token);
        if(id == null) return Result.fail("重置失败，token已过期");
        User user = redisCache.getRegisterInfo(id);
        if (user == null) return Result.fail("重置失败，请稍后尝试或联系管理管重置密码");
        this.updateById(user);
        return Result.success("密码重置成功");
    }

    @Override
    public Result activate(String token) {
        // 根据email从redis中获取信息
        String id = jwtGenerator.getIdFromToken(token);
        User user = redisCache.getRegisterInfo(id);
        if (user == null) {
            return Result.fail("认证失败，邮件已过期，请再次尝试");
        }
        if (!jwtGenerator.verifyToken(token, user.getId())) {
            return Result.fail("认证失败，邮件已过期，请再次尝试");
        }
        boolean save = save(user);
        if(!save) Result.fail("激活失败，账号已存在");
        // 返回结果
        return Result.success("账号激活成功");
    }

    private String generateEmail(String token, String host, String url) {
        return """
                请点击下面的链接激活账号，如果链接无法点击，请复制到浏览器地址栏打开。
                https://%s%s?token=%s&type=0
                """.formatted(host, url, token);
    }

    private Result login(String email, String password) {
        // 检测用户名密码是否正确
        User user = query().eq("email", email).eq("password", password).one();
        if (user == null) {
            return Result.fail("用户名或密码错误");
        }
        // 生成token
        String token = jwtGenerator.generateToken(user.getId());
        // 返回token
        return Result.success(token, "登录成功");
    }

    private Result register(String email, String password) {
        User user = new User();
        if (query().eq("email", email).exists()) {
            return Result.fail("注册失败，邮箱已被注册");
        }
        String uuid = IdUtil.simpleUUID();
        user.setId(uuid);
        user.setEmail(email);
        user.setPassword(password);
        // 生成token
        String token = jwtGenerator.generateToken(uuid);
        // 将用户信息存入redis
        redisCache.setRegisterInfo(uuid, user);
        // 发送邮件
        try {
            Context context = new Context();
            context.setVariable("email", email);
            context.setVariable("url", generateEmail(token, host, registerPath));
            emailUtil.send(email, "重建账号", context, "registerTemplate");
        } catch (Exception e) { // AddressException
            return Result.fail("注册失败，邮箱地址不正确");
        }
        return Result.success("注册成功，请前往邮箱激活账号");
    }

    public void validateCaptcha(String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getVerifyCode(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new RuntimeException("验证码过期");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new RuntimeException("验证码错误");
        }
    }
}
