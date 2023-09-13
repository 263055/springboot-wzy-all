package com.demo.util;

import cn.hutool.extra.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 邮件服务
 *
 * @author ding
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailUtil {
    private final TemplateEngine templateEngine;

    /**
     * 发送邮件-读取自定义模板
     *
     * @param to      目标邮箱
     * @param subject 标题
     * @param context 内容
     */
    public void send(String to, String subject, Context context, String templateUrl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        context.setVariable("createTime", sdf.format(new Date()));
        String template = templateEngine.process(templateUrl, context);
        MailUtil.send(to, subject, template, true);
    }

    /**
     * 发送邮件
     *
     * @param to      目标邮箱
     * @param subject 标题
     * @param content 内容
     */
    public void send(String to, String subject, String content) {
        MailUtil.send(to, subject, content, false);
    }

    /**
     * 发送邮件(带附件)
     *
     * @param to      目标邮箱
     * @param subject 标题
     * @param content 内容
     * @param files   附件（可选）
     */
    public void send(String to, String subject, String content, File... files) {
        MailUtil.send(to, subject, content, false, files);
    }

    /**
     * 发送邮件-读取自定义模板（带附件）
     *
     * @param to      目标邮箱
     * @param subject 标题
     * @param context 内容
     * @param files   附件
     */
    public void send(String to, String subject, Context context, File... files) {
        String template = templateEngine.process("emailTemplate", context);
        MailUtil.send(to, subject, template, true, files);
    }
}
