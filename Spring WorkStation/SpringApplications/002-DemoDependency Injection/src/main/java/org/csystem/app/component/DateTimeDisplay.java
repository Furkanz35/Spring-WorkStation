package org.csystem.app.component;

import com.karandev.util.spring.datetime.BeanName;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeDisplay {
    private final LocalDateTime now;
    private final DateTimeFormatter formatter;

    public DateTimeDisplay(int s) {
        formatter = null;
        now = null;
    }
    @Autowired
    public DateTimeDisplay(@Qualifier(BeanName.CURRENT_DATE_TIME) LocalDateTime now, @Qualifier(BeanName.DATE_TIME_FORMATTER_TR) DateTimeFormatter formatter) { //ctor injection
        this.now = now;
        this.formatter = formatter;
    }
    @PostConstruct
    public void display() {
            System.out.println("Now: " + formatter.format(now));
    }
}
