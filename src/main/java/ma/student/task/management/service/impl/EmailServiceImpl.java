package ma.student.task.management.service.impl;

import lombok.RequiredArgsConstructor;
import ma.student.task.management.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String sentFromEmail;

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Task");
        message.setText(text);
        message.setFrom(sentFromEmail);

        mailSender.send(message);
    }
}
