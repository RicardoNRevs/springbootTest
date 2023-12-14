package com.ricardo.quickstart;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//Anotacion que indica que es una aplicación spring. Esta es el main
@SpringBootApplication
//Enables the logging feature from spring-boot
@Log
public class QuickstartApplication implements CommandLineRunner {

	private final DataSource dataSource;

	public QuickstartApplication(final DataSource dataSource){ this.dataSource = dataSource; }

	public static void main(String[] args) {
		SpringApplication.run(QuickstartApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			log.info("Datasource" + dataSource.toString());
			final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
			restTemplate.execute("select 1");
		}catch (Exception e){
			log.info("Error"+ e);
		}

	}
}
