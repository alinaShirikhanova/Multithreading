package lesson2;

import java.util.Scanner;

public class Lesson2{
    public static void main(String[] args) throws InterruptedException {
        // Создание и запуск потока Manfred
        Manfred manfredThread = new Manfred();
        manfredThread.start();

        // Ожидание ввода пользователя для остановки потока
        Scanner scanner = new Scanner(System.in);
        System.out.println("Нажмите Enter для остановки потока...");
        scanner.nextLine();

        // Остановка потока с помощью метода shutdown
        manfredThread.shutdown();

        // Ждем завершения потока
        try {
            manfredThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Главный поток завершает работу");
        scanner.close();
    }
}

// Класс, расширяющий Thread
 class Manfred extends Thread {
    // Объявляем переменную для управления работой потока как volatile
    private  boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello from Manfred thread");
//            try {
//                System.out.println("in the ");
//               Thread.sleep(100); // Приостанавливаем выполнение на 100 мс
//            } catch (InterruptedException e) {
//                // Обработка прерывания потока (если необходимо)
//                Thread.currentThread().interrupt();
//                System.out.println("Thread was interrupted");
//            }
        }
        System.out.println("Manfred thread stopped");
    }

    // Метод для остановки потока
    public void shutdown() {
        System.out.println("shutdown");
        running = false;
    }
}
