package org.maklashev.goldengroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableDiscoveryClient
public class GoldengroupApplication {

	public static void main(String[] args) {

			ConfigurableApplicationContext context = SpringApplication.run(GoldengroupApplication.class, args);

	}



}
