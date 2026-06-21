package distributed;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class mGET {
    
  double getvaluesMetrices() throws IOException,InterruptedException{

         HttpClient client = HttpClient.newHttpClient();

           HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(

                    "http://192.168.46.34:9004/minio/metrics/v3/system/drive"))

                .GET()
                .build();

                HttpResponse<String> response = 
                client.send(request,
                        HttpResponse.BodyHandlers.ofString());

          String responseBody = response.body();

  String[] lines = responseBody.split("\n");

    for(String line : lines) {

    if(line.startsWith("minio_system_drive_used_bytes")) {

        String value = line.substring(line.lastIndexOf(" ") + 1);

        double usedBytes = Double.parseDouble(value);

        double usedGB = usedBytes / (1024.0 * 1024 * 1024);
        return usedGB;        
    }
}
    return 0;
 }

}
