package objecthandle;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.messages.ListAllMyBucketsResult.Bucket;

public class minio{

    public MinioClient connection(String user, String password) throws Exception {
    try {
        Dotenv dotenv = Dotenv.configure().filename("config.env").load();

        String users = dotenv.get("ADMIN");
        String pro = dotenv.get("PASS");
        
        // 1. Check credentials first
        if (user.equals(users) && password.equals(pro)) {

            MinioClient minioClient = MinioClient.builder()
                        .endpoint("http://localhost:9004")
                        .credentials(users, pro)
                        .build();

            minioClient.listBuckets();

            return minioClient;
            
        } else {
            return null;
        }
        
    } catch (Exception e) {
        // Triggered ONLY if MinIO is offline, or config.env is missing
       e.printStackTrace();
        return null;
    }
}

  
}
