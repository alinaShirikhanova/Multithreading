package lesson2;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class WorkerThreadTest {
    public static void main(String[] args) throws InterruptedException {
        WorkerThread thread = new WorkerThread();


        thread.start();
        Thread.sleep(1000);
        thread.stopWorking();


    }
}

class WorkerThread extends Thread {
    private volatile boolean working = true;

    @Override
    public void run() {
        while (working) {
            System.out.println(LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopWorking() {
        working = false;
        System.out.println("Stop working");

    }
}