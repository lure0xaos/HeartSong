package gargoyle.heartsong.aspects;

import gargoyle.heartsong.model.alerts.Alert;
import gargoyle.heartsong.services.Alerts;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class AlertExceptionAspectTest {

    @Autowired
    private AlertExceptionAspectTestService service;
    @Autowired
    private Alerts alerts;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ConversionService conversionService;

    @Test
    public void testSuccess() {
        alerts.clear();
        service.successExceptionAlert("any");
        Set<Alert> alertsOnce = alerts.getAlertsOnce();
        assertFalse("", alertsOnce.isEmpty());
        log.info(alertsOnce.toString());

    }

    @Test
    public void testFail() {
        alerts.clear();
        service.failExceptionAlert("any");
        Set<Alert> alertsOnce = alerts.getAlertsOnce();
        assertFalse("", alertsOnce.isEmpty());
        log.info(alertsOnce.toString());
    }
}
