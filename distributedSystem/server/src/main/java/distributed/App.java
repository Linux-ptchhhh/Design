package distributed;

import io.minio.MinioClient;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        minioclient min= new minioclient();

        schedular sc = new schedular();

       MinioClient conn =  min.connection("poolarea","poolarea");

       if(conn!=null){

        System.out.println("connection established successfully");

        kafkaproducer kaf = new kafkaproducer();
        

        sc.runschedule(kaf.produceService());

       }

    }
}
