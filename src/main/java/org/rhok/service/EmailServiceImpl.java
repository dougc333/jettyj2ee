package org.rhok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author 287 Development, 287dev.com
 */
@Service
public class EmailServiceImpl implements EmailService
{
    private JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendEmail(final String email, final String text)
    {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(email);
                message.setFrom("aaron@aaronschram.com");
                message.setText(text, true);
           }
        };
        try
        {
            this.mailSender.send(preparator);
        }
        catch(MailException me)
        {
            me.printStackTrace();
        }
    }
}
