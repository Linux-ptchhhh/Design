public class greater {
    public static void main(String[] args) {
        int ar[] = {2,5,7,8,9,19};
        int first = 0;
        int last = ar.length-1;
        int target = 10;
        int smallest =0;
        while(first<=last){
            int mid = (first+last)/2;
            if(ar[mid]<target){
                   smallest = mid;
                   first = mid+1;
            }
            else{
                last = mid-1;
            }
        }
        System.out.println("smallest "+ar[smallest+1]);
    }
}
