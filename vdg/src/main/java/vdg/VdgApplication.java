package vdg;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import vdg.controller.UbicacionController;
import vdg.controller.UbicacionRutinaController;
import vdg.model.controladorRutina.HistorialUbicacionFecha;

@SpringBootApplication
@Component
public class VdgApplication {

	public static void main(String[] args) {
		SpringApplication.run(VdgApplication.class, args);
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void start() throws IOException {
		String url = "http://localhost:9090/RestriccionPerimetral/getInit";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			System.out.println("Conectado OK");
		} else {
			System.out.println("GET request not worked");
		}
	}

}
