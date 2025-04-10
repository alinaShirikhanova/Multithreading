package lesson3;

class Main2 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount2 account = new BankAccount2();
        long before = System.currentTimeMillis();
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.deposit(10);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                }

            }
        });

        Thread printThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.deposit2(10);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                }
            }
        });

        depositThread.start();
        printThread.start();

        depositThread.join();
        printThread.join();
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
}

public class BankAccount2 {
    private int balance = 0;
    private int balance2 = 0;
    // Отдельный объект-монитор для критической секции изменения баланса
    private final Object depositLock = new Object();
    private final Object printLock = new Object();

    // Метод для внесения депозита синхронизируется на depositLock
    public void deposit(int amount) {
        synchronized (depositLock) {
            balance += amount;
        }
    }
    public void deposit2(int amount) {
        synchronized (printLock) {
            balance2 += amount;
        }
    }

    // Метод для чтения баланса – без синхронизации (или с независимой, если нужно)
    public int getBalance() {

        synchronized (printLock) {
            return balance;
        }
    }

    // Метод для печати баланса – для демонстрации можно оставить вне критической секции
    public void printBalance() {
        synchronized (printLock) {
            System.out.println("Balance: " + balance);
        }
    }
}
