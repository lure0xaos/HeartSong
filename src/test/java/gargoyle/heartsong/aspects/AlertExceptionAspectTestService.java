package gargoyle.heartsong.aspects;

import gargoyle.heartsong.annotations.ExceptionAlert;
import org.springframework.stereotype.Service;

@Service
public class AlertExceptionAspectTestService {

    @ExceptionAlert
    public String successExceptionAlert(String any) {
        return "success" + any;
    }

    @ExceptionAlert
    public String failExceptionAlert(String any) {
        throw new RuntimeException("success" + any);
    }
}
