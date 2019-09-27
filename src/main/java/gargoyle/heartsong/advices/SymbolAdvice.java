package gargoyle.heartsong.advices;

import gargoyle.heartsong.config.SymbolsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class SymbolAdvice {
    private final SymbolsConfig config;

    @ModelAttribute("applicationBuild")
    public String getApplicationBuild() {
        return config.getApplicationBuild();
    }

    @ModelAttribute("applicationCharset")
    public String getApplicationCharset() {
        return config.getApplicationCharset();
    }

    @ModelAttribute("applicationDescription")
    public String getApplicationDescription() {
        return config.getApplicationDescription();
    }

    @ModelAttribute("applicationInceptionYear")
    public String getApplicationInceptionYear() {
        return config.getApplicationInceptionYear();
    }

    @ModelAttribute("applicationName")
    public String getApplicationName() {
        return config.getApplicationName();
    }

    @ModelAttribute("applicationOrganizationName")
    public String getApplicationOrganizationName() {
        return config.getApplicationOrganizationName();
    }

    @ModelAttribute("applicationOrganizationUrl")
    public String getApplicationOrganizationUrl() {
        return config.getApplicationOrganizationUrl();
    }
}
