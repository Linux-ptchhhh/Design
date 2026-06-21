package objecthandle;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import java.util.*;

public class Handle {

    public void bucketCheck(MinioClient minioClient,String bucketName) {
        Scanner s = new Scanner(System.in);

        try {

            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );

            System.out.println("Existing Buckets:");

            var bucketList = minioClient.listBuckets();

            for (var bucket : bucketList) {
                System.out.println("Bucket Name: " + bucket.name());
            }

            if (exists) {
                System.out.println("Bucket already exists.");
            } else {

                System.out.println("Bucket not found. Creating bucket.../y/N");
                String con = s.nextLine();

                if(con.toLowerCase().equals("y")){

                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build()
                );

                System.out.println("Bucket created successfully.");
            }else{
                System.out.println("bucket not created....");
            }
            }


        } catch (Exception e) {

            System.out.println("Bucket operation failed.");
            e.printStackTrace();
        }
    }
//put your file into the minio server bucket;;
    public void putobject(

            String localFilePath,
            String objectName,
            String bucketName,
            MinioClient minioClient) {

        try {

            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)//bucket name in server
                            .object(objectName)//name to be saved in cloud center
                            .filename(localFilePath)//your local mechine file path extracted from python script
                            .build()
            );
           

        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}