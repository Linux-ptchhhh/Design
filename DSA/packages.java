public class packages {

    static int[] findMaxandSum(int[] arr){
        int sum = 0;
        int max = arr[0];
        for(int check : arr ){
            sum = sum+check;
            if(check > max){
                max = check;
            }
        }
        return new int[] {max,sum};


    }

    static boolean isallocate(int arr[],int studen ,int mid){
           int stu = 1;
           int pload = 0;
           for(int checks:arr){
               if(checks+pload>=mid){
                stu++;
                pload = 0;
                pload = checks;
               }
               pload = pload+checks;

           }
           return stu<=studen;



    }
    public static void main(String[] args) {
        int pages[] = {3,5,7,2,6,8};
        int student = 7;
        int[] r= findMaxandSum(pages);
        int low = r[0];
        int high = r[1];

        while(low<=high){
            int mid= (low+high)/2;
            if(isallocate(pages,student,mid)){
                System.out.println("we can allocate such pages to the given student" + "capacity"+ mid);
                break;
            }
            else{
                low = mid+1;
            }

        }

    }
}

