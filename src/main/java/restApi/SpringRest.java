package restApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;

@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@SpringBootApplication
public class SpringRest {
    public static void main(String[] args) {
        SpringApplication.run(SpringRest.class);
    }

    @Scheduled(fixedRate = 30000)
    @GetMapping(value="/get10min")
    public void greeting() {
        System.out.println("Hello!!!");
    }
}
