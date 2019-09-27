package gargoyle.heartsong.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
public class Initializer {
    private final DataSource dataSource;

    //    @PostConstruct
    public void initialize() {
        HSTestUtil.prepareData(dataSource);
    }
}
