package objecthandle;
import io.github.cdimascio.dotenv.Dotenv;
import io.minio.MinioClient;
import java.util.Scanner;

public class App 
{

   static Handle bucket= new Handle();
    public static void main( String[] args ) throws Exception
    {
        ServerManager server = new ServerManager();
        server.httpenable();
    }
}
