package com.netsmartz.mis.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender mailSender;

    public HttpStatus sendEmail(String to, String subject, String message) {
        String from = "rdinesh709@gmail.com";

        SimpleMailMessage SimpleMailMessage = new SimpleMailMessage();


        SimpleMailMessage.setFrom(from);
        SimpleMailMessage.setTo(to);
        SimpleMailMessage.setSubject(subject);
        SimpleMailMessage.setText(message);

        try{
        mailSender.send(SimpleMailMessage);
          return HttpStatus.OK;
        }
        catch(MailSendException e)
        {
            throw new MailSendException(Constant.Mail_SENDING_FAILED);
        }

    }
}
