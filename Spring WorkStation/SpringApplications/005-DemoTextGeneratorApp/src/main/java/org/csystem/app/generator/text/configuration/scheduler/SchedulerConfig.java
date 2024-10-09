package org.csystem.app.generator.text.configuration.scheduler;

import org.csystem.util.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class SchedulerConfig {
    @Bean
    public Scheduler createScheduler(@Value("${generator.period:2000}") Long period, @Value("${generator.timeunit:SECOND}") String timeUnit) {
        return new Scheduler(period, TimeUnit.valueOf(timeUnit));
    }
}
