//package goonerd.devhub.common.event;
//
//import goonerd.devhub.common.mail.EmailService;
//import org.springframework.context.event.EventListener;
//
//import java.util.Map;
//
//public class GenericEventListener {
//
//    private final EmailService emailService;
//
//    public GenericEventListener(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @EventListener
//    public void handleGenericEvent(GenericEvent<?> event) {
//        if ("PROJECT_COMPLETED".equals(event.getType())) {
//            Map<String, String> data = (Map<String, String>) event.getPayload();
//            String email = data.get("userEmail");
//            String projectName = data.get("projectName");
//
//            String htmlContent = "<h1>프로젝트 완료 알림</h1>"
//                    + "<p>프로젝트 '" + projectName + "'가 완료되었습니다.</p>";
//
//            emailService.sendEmail(email, "프로젝트 완료 알림", htmlContent);
//        }
//    }
//}