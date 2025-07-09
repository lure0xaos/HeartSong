package gargoyle.heartsong;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HeartSongApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HeartSongApplication.class)
                .profiles("development")
                .run(args);
    }

}
