import java.io.*;
import java.nio.file.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

class del{
    String paths;
    del(String paths){
        this.paths = paths;
        File fip = new File(paths);
        try{
        boolean verify1 = fip.delete();
        if(verify1){
            System.out.println("file has been deleted successfully");
        }else{
            System.out.println("file not exists!!");
        }
    }catch(Exception e){ e.printStackTrace();}

}
}
class Channel{
    File fp;
    String fp2;
    Channel(String fp2,File fp){
        this.fp2 = fp2;
        this.fp = fp;
     }
    void filewrite(){
    try{
        FileOutputStream fos = new FileOutputStream(fp);
        FileChannel fileChannel = fos.getChannel();
        long pos = fileChannel.position();
        Path p = Paths.get(fp2);
        byte[] bread = Files.readAllBytes(p);
        ByteBuffer b = ByteBuffer.wrap(bread);
        fileChannel.write(b,pos);
        System.out.println("Content has been written");
      }catch(Exception e){e.printStackTrace();}
      }
}



class fwri{
    public static void main(String[] args) {
      
    }
}