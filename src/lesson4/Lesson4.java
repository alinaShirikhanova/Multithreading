package lesson4;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lesson4 {
    public static void main(String[] args) throws InterruptedException {
        new Worker().main();
    }
}

class Worker {
    Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();


    public synchronized void addToList1() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));


    }

    public synchronized void addToList2() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));

    }

    public void work() {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    public void main() throws InterruptedException {
        long before = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> work());
        Thread thread2 = new Thread(() -> work());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        long after = System.currentTimeMillis();
        System.out.println(after - before);
        System.out.println(list1.size());
        System.out.println(list2.size());

    }
}

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class Lesson4 {
//    public static void main(String[] args) throws InterruptedException {
//        new Worker().main();
//    }
//}
//
//class Worker {
//    Random random = new Random();
//    private List<Integer> list1 = new ArrayList<>();
//    private List<Integer> list2 = new ArrayList<>();
//    private Object lock1 = new Object();
//    private Object lock2 = new Object();
//
//    public void addToList1() {
//        synchronized (lock1) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            list1.add(random.nextInt(100));
//
//        }
//    }
//
//    public void addToList2() {
//        synchronized (lock2) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            list2.add(random.nextInt(100));
//        }
//    }
//
//    public void work() {
//        for (int i = 0; i < 1000; i++) {
//            addToList1();
//            addToList2();
//        }
//    }
//
//    public void main() throws InterruptedException {
//        long before = System.currentTimeMillis();
//        Thread thread1 = new Thread(() -> work());
//        Thread thread2 = new Thread(() -> work());
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
//
//        long after = System.currentTimeMillis();
//        System.out.println(after - before);
//        System.out.println(list1.size());
//        System.out.println(list2.size());
//
//    }
//}
