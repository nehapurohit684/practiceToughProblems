package thread;

public class FoorBar {

    public synchronized static void main(String[] args) {

        Thread t = new Thread() {
            public void run() {
                bar();
            }
        };
       // t.start();//t.start makes sure a new tread is created.
        System.out.println(t.getName());
        t.run();//t.run makes sure run will be called as normal method
        System.out.println(t.getName());
        System.out.println("foo");
    }

    private static void bar() {
        System.out.println("bar");
    }
}
