package com.brokenFirmChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Logger;

@SpringBootApplication
public class Application implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			String url = "http://localhost:8585/Brokage-Firm-Challenge/swagger-ui/index.html";
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(new URI(url));
					LOGGER.info("Swagger UI opened successfully in the browser.");
				} else {
					LOGGER.warning("BROWSE action is not supported on this system.");
				}
			} else {
				LOGGER.warning("Desktop is not supported on this system.");
			}
		} catch (Exception e) {
			LOGGER.severe("Failed to open Swagger UI: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
