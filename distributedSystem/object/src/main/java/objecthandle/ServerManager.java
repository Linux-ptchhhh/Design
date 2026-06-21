package objecthandle;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class ServerManager{

     void httpenable() throws Exception {

       service s = new service();

        HttpServer server =
            HttpServer.create(
                new InetSocketAddress(8080),
                0
            );

        server.createContext("/bucketNames", exchange -> {

              String json = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
            );

            ObjectMapper mapper = new ObjectMapper();

            Map<String, String> data =
                mapper.readValue(json, Map.class);

                 String bucketName =
                data.get("bucketname");

           try {
            s.verifybucket(bucketName);
        } catch (Exception e) {
            e.printStackTrace();
        }
            exchange.close();
        });

        // Endpoint 2
        server.createContext("/putFile", exchange -> {

             String json = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
            );

            ObjectMapper mapper = new ObjectMapper();

            Map<String, String> data =
                mapper.readValue(json, Map.class);

            String bname = data.get("bucketname");
            String fname = data.get("filename");
            String objectname = fname;

            try {
                s.putfile(fname,objectname,bname);
            } catch (Exception e) {
                e.printStackTrace();
            }

           
            exchange.close();
        });

        server.start();

    }
}