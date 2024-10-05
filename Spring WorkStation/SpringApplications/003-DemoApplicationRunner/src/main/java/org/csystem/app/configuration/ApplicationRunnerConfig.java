package org.csystem.app.configuration;

import com.karandev.util.console.Console;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationRunnerConfig {

    @Bean
    public ApplicationRunner run() {
        return args -> Console.writeLine("ApplicationRunnerConfig.run");
    }
 }
