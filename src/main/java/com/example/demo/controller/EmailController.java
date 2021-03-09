package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Nicole
 */
@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private TemplateEngine templateEngine;

    @RequestMapping("sendSimpleEmail")
    public String sendSimpleEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件发送地址
            message.setFrom(from);
            message.setCc(from);
            //邮件接收地址
            message.setTo("296378869@qq.com");
            //邮件标题
            message.setSubject("一封简单的邮件");
            //邮件内容
            message.setText("使用spring Boot发送简单邮件。");
            jms.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 发送HTML格式的邮件
     *
     * @return String
     */
    @RequestMapping("sendHtmlEmail")
    public String sendHtmlEmail() {
        MimeMessage message = null;

        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            log.info("+++++++from:{}", from);
            //邮件发送方
            helper.setFrom(from);
            helper.setCc(from);
            //邮件接收地址
            helper.setTo("296378869@qq.com");
            helper.setSubject("一封HTML格式的邮件");
            //带HTML格式的内容
            StringBuffer sb = new StringBuffer("<p style='color:#6db33f'>使用Spring Boot发送HTML格式邮件。</p>");
            //helper.setText(sb.toString(), true);中的true表示发送HTML格式邮件
            helper.setText(sb.toString(), true);
            jms.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @return String
     */
    @RequestMapping("sendAttachmentsMail")
    public String sendAttachmentsMail() {
        MimeMessage message = null;

        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setCc(from);
            helper.setTo("296378869@qq.com");
            helper.setSubject("一封带附件的邮件");
            helper.setText("详情参见附件内容！");
            //传入附件
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/file/项目文档.docx"));
            helper.addAttachment("项目文档.docx", file);
            jms.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    /**
     * 发送带静态资源的邮件
     *
     * @return String
     */
    @RequestMapping("sendInlineMail")
    public String sendInlineMail() {
        MimeMessage message = null;

        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setCc(from);
            helper.setTo("296378869@qq.com");
            helper.setSubject("一封带静态资源的邮件");
            helper.setText("<html><body>博客图：<img src='cid:img'/></body></html>", true);

            helper.setText("详情参见附件内容！");
            //传入附件
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/img/img.png"));
            helper.addInline("img", file);
            jms.send(message);
            return "发送成功";

        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    /**
     * 使用模板发送邮件
     *
     * @return String
     */
    @RequestMapping("sendTemplateEmail")
    public String sendTemplateEmail(String code) {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setCc(from);
            helper.setTo("296378869@qq.com");
            helper.setSubject("邮件摸板测试");

            //处理邮件模板
            Context context = new Context();
            context.setVariable("code", code);
            String template = templateEngine.process("emailTemplate", context);
            helper.setText(template, true);
            jms.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
