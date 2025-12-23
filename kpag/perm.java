import java.io.IOException;
import java.io.File;
import java.io.File.*;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

 interface permission {
    void setPerm(String s,String path)throws IOException;
    void check(String s);
}

 class filePerm implements permission {

     public void setPerm(String s,String path)throws IOException{
         Set<PosixFilePermission> perm = PosixFilePermissions.fromString(s);
         Path p = Paths.get(path);
         Files.setPosixFilePermissions(p, perm);
         System.out.println("permission has been set");
        }

        public void check(String s){
            Path p = Paths.get(s);
            if(Files.isExecutable(p)){
                System.out.println("file is executable");
            }
            if(Files.isReadable(p)){
                System.out.println("file is readable");

            }
            if(Files.isWritable(p)){
                System.out.println("file is writable");
            }
             
             
        }

    }
    class determine {
         public boolean ret(String fp,int a){
            Path p = Paths.get(fp);
            if(a == 1){
                return Files.isReadable(p);
            }
            if(a == 2){
                return Files.isWritable(p);
            }
             return false;
         }

    }
    class perm{
        public static void main(String[] args) {
            permission p = new filePerm();
            p.check(args[0]);
        }
    }
