package test.ngocpt.auth.service;

import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import test.ngocpt.auth.dto.response.MailResponse;


@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @SneakyThrows
    public void sendEmail(MailResponse mail){
        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject(mail.getSubject());
        message.setContent(mail.getContent(), "text/html; charset=utf-8");
        message.setRecipients(MimeMessage.RecipientType.TO, mail.getRecipient());
        mailSender.send(message);
    }
}
