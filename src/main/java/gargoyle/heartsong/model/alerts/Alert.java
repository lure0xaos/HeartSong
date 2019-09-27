package gargoyle.heartsong.model.alerts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(of = "date")
@AllArgsConstructor
@NoArgsConstructor
public class Alert implements Comparable<Alert> {
    @Builder.Default
    private final LocalDateTime date = LocalDateTime.now();
    private AlertType type;
    private String message;
    private String description;
    private boolean dismissible;
    private boolean raw;

    @Override
    public int compareTo(Alert other) {
        return date.compareTo(other.date);
    }
}
