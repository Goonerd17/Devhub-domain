package goonerd.devhub.ports.out.mail;

public interface EmailSendPort {
    void sendEmail(String to, String subject, String htmlBody);
}