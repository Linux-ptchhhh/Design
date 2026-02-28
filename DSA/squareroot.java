public class squareroot {
    
    public static void main(String[] args) {
        
        int square = 81;

        int initial = 0;
        int last = square;
        boolean flag = false;

         
        while(initial<=last){
                int mid = (initial + last) /2;
            if((mid * mid) == square){
                System.out.println("square  root " + mid);
               break;
            }
            else if(mid *mid > square){
                last = mid;
               }
        else{
            initial = mid;
            if((initial +1) == last){
                flag = true;
                break;
            }
        }
    }
    if(flag){
        System.out.println("square root relies on " + initial + "to" + last);
    }

}
}