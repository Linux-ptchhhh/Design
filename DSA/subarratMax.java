public class subarratMax {

    static int[] Maxsubarray(int[] arr){
           int i,j,k;
           int sum = 0;
           int max ;
           int f = 0,l = 0;

           for(i = 0;i<arr.length;i++){


              for(j = i;j<arr.length;j++){
                 
                max = 0;

                for(k = i;k<=j;k++){

                   max = max+arr[k];

                   
                }
                 
                if(max > sum){
                    sum = max;
                    f = i;
                    l = j;
                }
              }
           }

           return new int[]{f,l,sum};

    }
    public static void main(String[] args) {
        int arr[] = {2,-1,-1,-9,7,4};   
        int [] res = Maxsubarray(arr);
        for(int i = res[0];i<=res[1];i++){
            System.out.println(arr[i]);
        }
        System.out.println("Maximum sum of subarrray " + res[2]);
    }
}
