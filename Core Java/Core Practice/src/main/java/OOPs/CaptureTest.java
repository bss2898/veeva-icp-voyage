public class CaptureTest {
    public static void main(String args[]) {
        final int count = 10;
        Runnable r = new Runnable() {
            public void run() {
                System.out.println(count);
            }
        };
        //count++; // Line 9
        r.run();
    }


}


