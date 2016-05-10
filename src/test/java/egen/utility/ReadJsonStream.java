package egen.utility;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import egen.entity.Metrics;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ReadJsonStream {

	private static int interval_in_ms = 5000;
	
	public static String base_value = null;

	public static Metrics readJson() throws Throwable {
		//String url = "http://localhost:8181";
		Metrics obj = null;		

		while(true) {

//			BufferedReader input = new BufferedReader
//					(new InputStreamReader(new URL(url).openStream(), "UTF-8"));

			//Assuming we are reading data from file every 5 seconds 
			FileReader file = new FileReader("src/test/file.txt");
			BufferedReader input = new BufferedReader(file);
			String line = null;
			while((line = input.readLine()) != null) {
				try {							
					obj = new ObjectMapper().readValue(line, Metrics.class);
					
					if(base_value == null) {
						base_value = obj.getValue();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					input.close();
				}
				
				Thread.sleep(interval_in_ms);
			}
		}
	}
}
