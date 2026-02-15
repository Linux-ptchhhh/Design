import java.util.*;
class operations{
    double[][] vector = {{0 ,0}};
    int speed;

    int i = 0;
    Scanner s = new Scanner(System.in);

    void calculatePosition(){
        System.out.println("Enter your initial position in 2D represent");
        for(int i = 0;i<1;i++){
            for(int j = 0;j<2;j++){
               vector[i][j] = s.nextDouble();
            }
        }
    }

    double[][] velocity(double vx,double vy){
           double[][] velo = {{vx,vy}};
        return velo;
    }
   void track(double[][] velo)throws Exception{
    long sec = System.nanoTime();

     while(true){
        long current = System.nanoTime();
        double t = (current - sec)/1_000_000_000.0;


        for(int i = 0;i<1;i++){
            for(int j =0;j<2;j++){

                vector[i][j] = vector[i][j] + velo[i][j] * t;
              
            }
        }
         System.out.println("object at " + t + " " + "second");
        for(int i = 0;i<1;i++){
            for(int j =0;j<2;j++){
               
                System.out.print(vector[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }
        Thread.sleep(1000);

   }

}
}


public class vector {
    public static void main(String[] args) {
        double vx,vy;
        Scanner n = new Scanner(System.in);
        operations op = new operations();
        op.calculatePosition();
        System.out.println("enter velocity point in 2D representation");
        vx = n.nextDouble();
        vy = n.nextDouble();
        System.out.println("Assigning velocity to the object");
        double[][] o = op.velocity(vx,vy);
        try {
            op.track(o);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
