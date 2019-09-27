package gargoyle.heartsong.model.alerts;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Scope(value = SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AlertSet implements Iterable<Alert> {
    private final Set<Alert> set = new TreeSet<>();

    public void alert(Alert alert) {
        AlertType type = alert.getType();
        if (null != type && AlertType.NONE != type) set.add(alert);
    }

    public Set<Alert> getAlerts() {
        return Collections.unmodifiableSet(new TreeSet<>(set));
    }

    public Set<Alert> getAlertsOnce() {
        Set<Alert> copy = Collections.unmodifiableSet(new TreeSet<>(set));
        set.clear();
        return copy;
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public Iterator<Alert> iterator() {
        return set.iterator();
    }

    public void clear() {
        set.clear();
    }
}
