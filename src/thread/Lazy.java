package thread;

public class Lazy {


    static Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("Worker thread starts");
            init = true;
        }
    });
    public static boolean init = false;

    static {
        t.start();
    }

    public static void main(String[] args) {
        //we cant keep t.join in static as it will create deadlock
        try {
            t.join();
            System.out.println("Worker thread done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(init);
    }
}
