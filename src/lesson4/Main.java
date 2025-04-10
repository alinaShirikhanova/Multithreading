package lesson4;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Тест с синхронизацией на объекте (this):");
        testSyncOnThis();

        System.out.println("\nТест с синхронизацией на стороннем объекте (lock):");
        testSyncOnLock();
    }

    // Тест для BankAccount, где методы синхронизированы на объекте (this)
    public static void testSyncOnThis() throws InterruptedException {
        BankAccountThis account = new BankAccountThis();
        long start = System.currentTimeMillis();

        // Поток, который вносит депозит
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(10);
                sleep(1);
            }
        });

        // Поток, который периодически выводит баланс
        Thread printThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.printBalance();
                sleep(1);
            }
        });

        depositThread.start();
        printThread.start();

        depositThread.join();
        printThread.join();

        long end = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (end - start) + " мс");
    }

    // Тест для BankAccount, где используется внешний объект-монитор
    public static void testSyncOnLock() throws InterruptedException {
        BankAccountLock account = new BankAccountLock();
        long start = System.currentTimeMillis();

        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(10);
                sleep(1);
            }
        });

        Thread printThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.printBalance();
                sleep(1);
            }
        });

        depositThread.start();
        printThread.start();

        depositThread.join();
        printThread.join();

        long end = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (end - start) + " мс");
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) { /* игнорируем */ }
    }
}

// Класс BankAccount с синхронизацией на объекте (this)
class BankAccountThis {
    private int balance = 0;

    // Метод синхронизирован на объекте, т.е. используется монитор this
    public synchronized void deposit(int amount) {
        balance += amount;
    }

    // Тоже синхронизирован, чтобы гарантировать взаимное исключение при чтении
    public synchronized void printBalance() {
        System.out.println("Баланс: " + balance);
    }
}

// Класс BankAccount с синхронизацией на стороннем объекте (lock)
class BankAccountLock {
    private int balance = 0;
    // Внешний монитор для синхронизации
    private final Object lock = new Object();

    public void deposit(int amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    public void printBalance() {
        synchronized (lock) {
            System.out.println("Баланс: " + balance);
        }
    }
}
