public class minincrement {
    public static void main(String[] args) {
          int arr[] = {3,4,2,12};
          int k = 3;
          int count = 0;
          int max = 0;;
          boolean flag = false;
        for(int i = 0;i<arr.length-1;i++){
            
            if(max < arr[i+1]){
                max = arr[i+1];
            }
        }
        int i = 0;
        
        while(i<arr.length){
           while(arr[i] < max){
              arr[i] = arr[i] + k;
              count++;
           }

              if(arr[i] >max){
                flag = true;
                System.out.println("array value -1 ");
                break;
              }
           
           i++;
        }
        if(flag == false){
              System.out.println("cost = " + count);
        }

    }
}



    