package names.names.services;

import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
@Qualifier("serviceNameGenerator")
public class ServiceNameGenerator implements NameGenerator {
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getMaleName() {
        String url = "https://randomuser.me/api/?gender=male&inc=name&nat=us";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        String json = (String) response.getBody()
                .get("results").toString();
        String name = json.split("first=")[1].split(",")[0];
        return name;
    }

    @Override
    public String getFemaleName() {
        String url = "https://randomuser.me/api/?gender=female&inc=name&nat=us";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        String json = (String) response.getBody()
                .get("results").toString();
        String name = json.split("first=")[1].split(",")[0];
        return name;
    }

    @PostConstruct
    public void init() {
        System.out.println("ServiceNameGenerator has been initialized.");
    }
}
