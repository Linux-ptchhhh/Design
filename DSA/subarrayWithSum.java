import java.util.HashMap;
import java.util.Map;

public class subarrayWithSum {

    static HashMap<Integer,Integer> GivenSum(int[] arr,int target){

        int i,j,k;
        int sum = 0;

        HashMap<Integer,Integer> h = new HashMap<>();

        for(i = 0;i < arr.length;i++){

            for(j = i;j < arr.length;j++){

                sum = 0;

                for(k = i;k <= j;k++){
                    sum = sum + arr[k];
                }

                if(sum == target){
                    h.put(i,j); 
                }
            }
        }

        return h;
    }

    public static void main(String[] args) {

        int arr[] = {2,5,7,4,3};
        int target = 7;

        HashMap<Integer,Integer> a = GivenSum(arr,target);

        for(Map.Entry<Integer,Integer> entry : a.entrySet()){

            Integer key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println("Start index: " + key + 
                               " End index: " + value);
        }
    }
}