package goonerd.devhub.ports.out.application;

import goonerd.devhub.domain.application.Application;

public interface ApplicationRepository {
    Application save(Application application);
    boolean existsByProjectGuidAndApplicantGuid(String projectGuid, String applicantGuid);
    Application findByApplicationGuid(String applicantGuid);
}