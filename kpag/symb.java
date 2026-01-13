import java.io.IOException;
import java.nio.file.*;
interface link{
    void linking(String a, String b);
    void check(String s);
}

class unsupport extends Exception{
    unsupport(String m){
        super(m);
    }

}

class notlink extends Exception{
     notlink(String m){
        super(m);
    }
}
class notf extends Exception{
    notf(String m){
        super(m);
    }
}



class Link implements link{
   
    public void linking(String p1,String p2){
         Path p = Paths.get(p1);
         Path pp = Paths.get(p2);
         //creating symbolic link
         if(Files.isSymbolicLink(pp)){
            System.out.println("file already in link");
         }
         else{

            try{
                Files.createSymbolicLink(p,pp); // original , duplicate;
                if(!Files.isSymbolicLink(p) == false){
                     throw new unsupport ("file link not extablished or file doesn't exist");
                }
            }catch(unsupport e){
                System.out.println(e.getMessage());
            }
            catch(IOException e){e.printStackTrace();}
         }

     }
    
    public void check(String s){   // this check symbolic link files and display it..
        Path p = Paths.get(s);
        try{
            DirectoryStream<Path> stream = Files.newDirectoryStream(p);
            for(Path f : stream){
                if(Files.isSymbolicLink(f)){
                    Path file = Files.readSymbolicLink(f);
                    System.out.println(f.getFileName() +"->" + file);

                }
                else{
                    throw new notlink("The file"+ f.getFileName() + "not in any link");
                }
            }

        }
        catch(notlink e){
           e.getMessage();
        }catch(IOException e){e.printStackTrace();}
     }

    public boolean Check(String fp){
        Path p = Paths.get(fp);
           if(Files.isSymbolicLink(p)){
            System.out.println("Given file /Dir has symbolic link");
           }
           return true;
     }

    void Delete(String path) throws IOException{ //path for the link ,not the original file ::
        Path p = Paths.get(path);
        if(Files.isSymbolicLink(p)){
            Files.delete(p);
        }
     }

    void getPath(String path){ // find orginal file path as well as the link part;
        try{
            Path p = Paths.get(path);
            if(Files.exists(p)){
            Path p1 = Files.readSymbolicLink(p);
            Path g = p1.getParent();
            System.out.println(g);
            }
            else{
                throw new notf("file not found");
            }
        }catch(notf e){e.getMessage();}
         catch(IOException e){e.getMessage();}
         }

    void BrokenSpe(String path){ //one for givene link file not for all system files; this method only handle the specific task
           Path p = Paths.get(path);
           try{
            if(Files.isSymbolicLink(p)){
                Path p1 = Files.readSymbolicLink(p);
                Path re = p.getParent().resolve(p1);
                if(!Files.exists(re)){
                      System.out.println("Broken symblink");
                      System.out.println("Maybe target/link file is missed");
                }
                else{
                    System.out.println("No broken occured");
                }
            }
            else{
                System.out.println("File is not in link");
            }
           }catch(Exception e){e.printStackTrace();}

         }

    void changeTar(String otarget,String ntarget){ // (target file and new link file) for changing the link of the file;;
        // new target and old link..
          try{
             boolean b = Check(otarget);
             if(b){
           Path p = Paths.get(otarget);
           Path read = Files.readSymbolicLink(p);
           String s = read.toString();
           Files.delete(read);
           linking(ntarget,s);
        }
           }catch(Exception e){e.printStackTrace();}
        }

        void changeLink(String olink,String nlink){
            try{
                boolean b = Check(olink);
                if(b){
                    Path p = Paths.get(olink);
                    Path read = Files.readSymbolicLink(p); //old link's Target file stored here
                    String s = read.toString();
                    Files.delete(read);
                    linking(s,nlink);
                }else{
                          System.out.println("Not a link");                    
                }
            }catch(Exception e){e.printStackTrace();}
        }
    }
