public class subarray {
    public static void main(String[] args) {

        int sum = 0;
        int start = 0;
        int end = 0;

        int arr[] = {2,3,4,5,6,8,4,3,-10,-5};

        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){

                int total = 0;  

                for(int k = i; k <= j; k++){
                    total = total + arr[k];
                }

                if(total > sum){
                    sum = total;
                    start = i;
                    end = j;
                }
            }
        }

        System.out.println("Total Sum: " + sum);

        for(int a = start; a <= end; a++){
            System.out.println(arr[a]);
        }
    }
}