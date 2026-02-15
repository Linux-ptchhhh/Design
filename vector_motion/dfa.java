import java.util.*;

class DFA{
    String text;
    String pattern;
     public char[] ch;
     
//trans table for only string pattern to this 'ab' character;;
     public int trans[][] = {{1,0},{1,2},{1,0}};
    DFA(String t, String p){
        this.text = t;
        this.pattern = p;

    }
   
    char[] ArrayChange(String name){
           ch = name.toCharArray();
           return ch;
    }

    void findPattern(){
     int pl = pattern.length();
     int tl = text.length();
        int i=0;
        int state=0;
        char[] ch1 = ArrayChange(text);
        while(i<tl){
         int input = ch1[i] - 'a';
         state = trans[state][input];
         

           if(state == 2){
            System.out.println("pattern found at index " + ((i-pl) + 1) );
            continue;
           }
           i++;

        }

    }

}

public class dfa {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); 
        System.out.println("enter the string containe only ab letters");
        String S = s.nextLine();
        String pat = "ab";
        DFA dfa = new DFA(S,pat);
        dfa.findPattern();         
    }
    
}
