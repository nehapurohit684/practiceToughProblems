package thread;

public class Lazy {

    //for scenario when we keep thread initialization in static block creates a dead lock
    // because when we keep run and join in static block then main method thread wait for "t" to finish
    // but "t" is waiting for static initializer to finish so it creates a cyclic dependency.

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
