package gargoyle.heartsong.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:symbols.properties")
@Getter
public class SymbolsConfig {

    @Value("${application.build}")
    private String applicationBuild;
    @Value("${application.charset}")
    private String applicationCharset;
    @Value("${application.description}")
    private String applicationDescription;
    @Value("${application.inceptionYear}")
    private String applicationInceptionYear;
    @Value("${application.name}")
    private String applicationName;
    @Value("${application.organization.name}")
    private String applicationOrganizationName;
    @Value("${application.organization.url}")
    private String applicationOrganizationUrl;

}
