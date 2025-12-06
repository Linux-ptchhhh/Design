import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
 class create{
   Scanner scanner = new Scanner(System.in); 
   void make(String filepath){

      try{

        File f = new File(filepath);
        if(f.exists()){
          System.out.println("The file already exist in the directory");}

        boolean verify = f.createNewFile();

        if(verify){
        System.out.println("your file has been created successfully");}
      

      }
      catch(Exception e ){
        e.printStackTrace();
      }
   }

     void rename(String f1,String f2){
      try{
           File f = new File(f1);
           File fa = new File(f2);
           if(f.exists()){
              f.renameTo(fa);
                System.out.println("File/Dir name has been changed");
              }
            }catch(Exception e ){e.printStackTrace();}
 }
      void copy(String f1,String f2){
        Path p1 = Paths.get(f1);
        Path p2 = Paths.get(f2);
         try {
          Files.copy(p1,p2,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      void read(String filepath) throws Exception{
      FileInputStream f = new FileInputStream(filepath);
      FileChannel fc = f.getChannel();
      try{
          ByteBuffer buffer = ByteBuffer.allocate(4096); 
              int bytesRead = fc.read(buffer);
              buffer.flip();
              byte[] arr = new byte[buffer.remaining()];
        buffer.get(arr);
        String text = new String(arr, StandardCharsets.UTF_8);
        System.out.println(text);
      }catch(Exception e){e.printStackTrace();}
      finally{
        fc.close();
        f.close();
      }

 }


 }
 
 class fcre{
  public static void main(String[] args) throws Exception {
    create c = new create();
    c.read(args[0]);
  }
 }