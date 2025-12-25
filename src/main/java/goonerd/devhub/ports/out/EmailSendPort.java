package goonerd.devhub.ports.out;

public interface EmailSendPort {
    void sendEmail(String to, String subject, String htmlBody);
}