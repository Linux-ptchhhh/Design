package distributed;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.messages.ListAllMyBucketsResult.Bucket;

public class minioclient {

    public MinioClient connection(String user, String password) throws Exception {
    try {
        Dotenv dotenv = Dotenv.configure().filename("config.env").load();

        String users = dotenv.get("ADMIN");
        String pro = dotenv.get("PASS");
        
        // 1. Check credentials first
        if (user.equals(users) && password.equals(pro)) {

            MinioClient minioClient = MinioClient.builder()
                        .endpoint("http://192.168.46.34:9004")
                        .credentials(users, pro)
                        .build();

            System.out.println("Testing active connection to MinIO on VLAN...");
            minioClient.listBuckets();

            System.out.println("Connection and Authentication SUCCESSFUL!");
            return minioClient;
            
        } else {
            System.out.println("Authentication FAILED: Invalid Username or Password.");
            return null;
        }
        
    } catch (Exception e) {
        // Triggered ONLY if MinIO is offline, or config.env is missing
        System.out.println("Network Error: CONNECTION REFUSED or MinIO is offline.");
        return null;
    }
}

  
}
