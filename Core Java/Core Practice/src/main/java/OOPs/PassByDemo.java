package OOPs;

public class PassByDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Veeva");
        modify(sb);
        System.out.println(sb);
        //Veeva SDET
    }
    public static void modify(StringBuilder s) {
        s.append("SDET");
        s = new StringBuilder("QA");
        System.out.println(s);
        //QA
    }
}
