package lesson1;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Поток " + Thread.currentThread().getName() + " из группы " +
                Thread.currentThread().getThreadGroup().getName() + " выполняется.");
    }
}

public class Lesson1 {
    public static void main(String[] args) {
//        ThreadGroup group = new ThreadGroup("Workers");
//        Thread t = new Thread(group, new MyRunnable(), "Worker-Thread");
//        t.start();
        CounterThread thread1 = new CounterThread("Thread 1");
        CounterThread thread2 = new CounterThread("Thread 2");

        thread1.start();
        thread2.start();

        System.out.println("Main thread is done creating threads.");
    }
}
class CounterThread extends Thread {
    private String threadName;

    public CounterThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(threadName + ": " + i);
            // Для наглядности можно добавить небольшую задержку:
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
