public class kpag {
    public static void main(String[] args) throws Exception{
        String s1 = args[0];
        String s2 = args[1];
        String s3 = args[2];
        create c = new create();
        switch (s1) {
            case "-cr":
              
              c.make(s2);
                break;
            case "-rn":
               create v = new create();
               v.rename(s2, s3);
               break;
            case "-cp":
               c.copy(s1,s2);
            case "-rd":
               c.read(s1);
            default:
                break;
        }

    }
}
