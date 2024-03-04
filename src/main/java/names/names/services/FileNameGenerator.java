package names.names.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@Qualifier("fileNameGenerator")

public class FileNameGenerator implements NameGenerator {
    private List<String> maleNames;
    private List<String> femaleNames;
    private Random random = new Random();

    public FileNameGenerator(@Value("${app.maleNamesFile}") String maleNamesFile,
            @Value("${app.femaleNamesFile}") String femaleNamesFile) {
        try {
            this.maleNames = Files.readAllLines(Paths.get(maleNamesFile));
            this.femaleNames = Files.readAllLines(Paths.get(femaleNamesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMaleName() {
        int index = random.nextInt(maleNames.size());
        return maleNames.get(index);

    }

    @Override
    public String getFemaleName() {
        int index = random.nextInt(femaleNames.size());
        return femaleNames.get(index);

    }

    @PostConstruct
    public void init() {
        System.out.println("FileNameGenerator has been initialized..");
    }
}
