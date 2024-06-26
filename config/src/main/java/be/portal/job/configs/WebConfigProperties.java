package be.portal.job.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "angular.cors")
@Getter @Setter
public class WebConfigProperties {

    private List<String> allowedOrigins;
}
