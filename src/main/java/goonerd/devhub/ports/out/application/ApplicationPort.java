package goonerd.devhub.ports.out.application;

import goonerd.devhub.domains.application.Application;

public interface ApplicationPort {
    Application save(Application application);
    boolean existsByProjectGuidAndApplicantGuid(String projectGuid, String applicantGuid);
    Application findByApplicationGuid(String applicantGuid);
}