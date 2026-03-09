public class subarraysum {

    static void array(int[] arr){
        int k=0;
          for(int i = 0;i<arr.length;i++){
                for(int j = i;j<arr.length;j++){
                        int sum = 0;
                    for(k = i;k<=j;k++){
                    sum = sum + arr[k];
                  System.out.print(arr[k] + " ");
                    }
                      System.out.println(" = " + sum);
                }


          }
    }
    public static void main(String[] args) {
        int arr[] = {2,3,5,6,-8};
        array(arr);

    }
    
}
