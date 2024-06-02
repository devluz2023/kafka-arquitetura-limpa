package br.com.faluz.cross;

import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;

@Component
public class ZonedDateFactory {
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
