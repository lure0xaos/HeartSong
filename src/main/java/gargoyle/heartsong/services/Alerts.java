package gargoyle.heartsong.services;

import gargoyle.heartsong.model.alerts.Alert;
import gargoyle.heartsong.model.alerts.AlertSet;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

@Service
public class Alerts {
    private final AlertSet alerts = new AlertSet();

    public void alert(Alert alert) {
        alerts.alert(alert);
    }

    public Set<Alert> getAlerts() {
        return alerts.getAlerts();
    }

    public Set<Alert> getAlertsOnce() {
        return alerts.getAlertsOnce();
    }

    public int size() {
        return alerts.size();
    }

    public boolean isEmpty() {
        return alerts.isEmpty();
    }

    public Iterator<Alert> iterator() {
        return alerts.iterator();
    }

    public void clear() {
        alerts.clear();
    }

}
