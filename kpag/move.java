import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FileMove implements Callable<String>{
       Path path1,path2;
       FileMove(Path path1,Path path2){
       this.path1 = path1;
       this.path2 = path2;

        }
   public String call() throws Exception{
        Files.move(path1,path2,StandardCopyOption.REPLACE_EXISTING);

          return "copied" + path1 + "to" + path2;
  
           }
}
class SendCopy{
  
  public void Cp(String src,String dest){
    try{
    List<Future<String>> results = new ArrayList<>();
   ExecutorService pool = Executors.newFixedThreadPool(10);
 
        Path src1 = Paths.get(src);
        Path dest1 = Paths.get(dest);
     DirectoryStream<Path> stream = Files.newDirectoryStream(src1);
     
      for (Path file : stream) {

            Path destFile = dest1.resolve(file.getFileName());

            Future<String> future = pool.submit(new FileMove(file, destFile));
            results.add(future);
        }
         pool.shutdown();
    }catch(Exception e){e.printStackTrace();}
}
  public void Dirlist(String dir){ // list the directory files
         Path p = Paths.get(dir);
         try{
         Files.walk(p) 
             .forEach(path ->{
              System.out.println(p.relativize(path));
             });
            }catch(Exception e){e.printStackTrace();}

}
   
}
class move{
  public static void main(String[] args) {
    SendCopy sc = new SendCopy();
    sc.Cp(args[0],args[1]);
    
  }
}


 