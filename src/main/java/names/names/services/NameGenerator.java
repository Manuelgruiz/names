package names.names.services;

import org.springframework.stereotype.Service;

@Service
public interface NameGenerator {
    public String getMaleName();

    public String getFemaleName();

}