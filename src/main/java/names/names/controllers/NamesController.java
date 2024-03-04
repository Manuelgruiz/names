package names.names.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import names.names.services.NameGenerator;

@RestController
@RequestMapping("/api/names")
public class NamesController {

    private final NameGenerator fileNameGenerator;
    private final NameGenerator serviceNameGenerator;

    public NamesController(@Qualifier("fileNameGenerator") NameGenerator fileNameGenerator,
            @Qualifier("serviceNameGenerator") NameGenerator serviceNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
        this.serviceNameGenerator = serviceNameGenerator;
    }

    @GetMapping("/male")
    public String getMaleName(@RequestParam(required = false) String generationMode) {
        if ("SERVICE".equals(generationMode)) {
            return serviceNameGenerator.getMaleName();
        } else if ("FILE".equals(generationMode)) {
            return fileNameGenerator.getMaleName();

        }
        return getRandomName(fileNameGenerator.getMaleName(), serviceNameGenerator.getMaleName());

    }

    @GetMapping("/female")
    public String getFemaleName(@RequestParam(required = false) String generationMode) {
        if ("SERVICE".equals(generationMode)) {
            return serviceNameGenerator.getFemaleName();
        } else if ("FILE".equals(generationMode)) {
            return fileNameGenerator.getFemaleName();

        }
        return getRandomName(fileNameGenerator.getFemaleName(), serviceNameGenerator.getFemaleName());

    }

    private String getRandomName(String name1, String name2) {
        double random = Math.random();
        if (random < 0.5) {
            return name1;
        } else {
            return name2;
        }
    }
}