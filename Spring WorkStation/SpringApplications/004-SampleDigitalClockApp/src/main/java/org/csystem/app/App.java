package org.csystem.app;

import com.karandev.util.spring.datetime.BasePackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.csystem", BasePackage.BASE_PACKAGE})
public class App {
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
