package code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class IncubatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncubatorApplication.class, args);
    }

}
