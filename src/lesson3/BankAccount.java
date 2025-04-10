package lesson3;

class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        long before = System.currentTimeMillis();
        // Поток, который вносит депозит
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.deposit(10); // внесение 10 единиц 100 раз
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                }

            }
        });

        // Поток, который периодически выводит баланс
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

public class BankAccount {

    private int balance = 0;
    private int balance2 = 0;

    // Метод для внесения депозита синхронизируется на depositLock
    public synchronized void deposit(int amount) {
            balance += amount;
    }
    public synchronized void deposit2(int amount) {
        balance2 += amount;
    }

    // Метод для чтения баланса – без синхронизации (или с независимой, если нужно)
    public synchronized int getBalance() {
        return balance;
    }

    // Метод для печати баланса – для демонстрации можно оставить вне критической секции
    public synchronized void printBalance() {
        System.out.println("Balance: " + balance);
    }
}