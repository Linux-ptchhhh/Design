import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;

class Dirc{
   String dname;
   Dirc(String dname){
   this.dname = dname;}
   
    void directoryCreate(){
   try{
      Path directoryPath = Paths.get(dname);
     Files.createDirectory(directoryPath);

   }catch(Exception e){e.printStackTrace();}
   }


   
}
class Dird{
   Path dname;
   Dird(Path dname) throws IOException{
      this.dname = dname;

      try (Stream<Path> paths = Files.walk(dname)) {
    paths.sorted(Comparator.reverseOrder()) 
         .forEach(path -> {
             try {
                 Files.delete(path);
                 System.out.println("Deleted: " + path);
             } catch (IOException e) {
                 System.err.println("Failed to delete " + path + ": " + e.getMessage());
             }
         });
}
 }

}

class Dirop{
    String dname;
    Dirop(String dname){
        this.dname = dname;
              try{
            File f = new File(dname);
            if(f.isDirectory()){
                if(f!=null){
                File[] files = f.listFiles();
                for(File file : files){
                    if(file.isFile()){
                        System.out.println(file);
                    }
                }}
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
class cred{
    public static void main(String[] args) {
       

    }
}

