package thread;

import java.util.concurrent.TimeUnit;

public class StopThread {
    //when variable is volatile it can be shared between threads
    //we shouldnt use it generally but in this kind of situatio you use it.
    //http://tutorials.jenkov.com/java-concurrency/volatile.html
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                }
                System.out.println(i);
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Done");
        stopRequested = true;
    }
}
