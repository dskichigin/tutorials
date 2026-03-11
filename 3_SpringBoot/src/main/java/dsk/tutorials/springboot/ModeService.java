package dsk.tutorials.springboot;

import org.springframework.stereotype.Service;

@Service
public class ModeService implements MyService {
    private final ApplicationSettings appSettings;
    private final DbSettings dbSettings;

    public ModeService(ApplicationSettings appSettings, DbSettings dbSettings) {
        this.appSettings = appSettings;
        this.dbSettings = dbSettings;
    }

    @Override
    public String getName() {
        return "myService";
    }
}
