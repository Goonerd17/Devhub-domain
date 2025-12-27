package goonerd.devhub.common.utils;

import goonerd.devhub.ports.out.common.IdentifierGeneratorPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidIdentifierGeneratorUtil implements IdentifierGeneratorPort {

    @Override
    public String generate() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "");
    }
}
