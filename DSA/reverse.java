class rev{
    String s1;
    char[] ch;
    rev(String s){
        this.s1 = s;
      ch = s1.toCharArray();
    }

    char[] rever(){
        int lengths = ch.length;
        char[] n = new char[lengths];
        int j = 0;
        int i = lengths-1;
        while(i>=0 && j<=lengths-1){
          n[j] = ch[i];
          i--;
          j++;
        }
        
        return n;

    }
}


public class reverse {
    public static void main(String[] args) {
        rev r = new rev("kumar");
        char[] re = r.rever();
        for(int i = 0;i<5;i++){
            System.out.println(re[i]);
        }
    }
    
}
