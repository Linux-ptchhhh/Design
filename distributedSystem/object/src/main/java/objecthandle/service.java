package objecthandle;

import io.minio.MinioClient;

public class service {
    void verifybucket(String bucketName)throws Exception{
        Handle h = new Handle();
        
        h.bucketCheck(makeconnection(),bucketName);

    }

    MinioClient makeconnection()throws Exception{
        minio m = new minio();
        MinioClient conn = m.connection("poolarea","poolarea");
        return conn;
    }

    void putfile(String filename,String objectname,String bucketname)throws Exception{
        Handle h = new Handle();
        h.putobject(filename,objectname,bucketname,makeconnection());
    }
}
