package org.csystem.app.component;

import com.karandev.util.spring.datetime.BeanName;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeDisplay {
    @Autowired
    @Qualifier(BeanName.CURRENT_TIME)
    private LocalTime time;

    @Autowired
    @Qualifier(BeanName.TIME_FORMATTER_TR)
    private DateTimeFormatter formatter;

    @PostConstruct
    public void display() {
        System.out.println("Time: " + formatter.format(time));
    }
}
