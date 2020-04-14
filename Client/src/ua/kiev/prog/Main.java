package ua.kiev.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();

            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            if (login.length() < 1 || password.length() < 1) {
                System.out.println("Введите верный пароль и имя пользователя");
            } else {
                Thread th = new Thread(new GetThread());
                th.setDaemon(true);
                th.start();
                User user = new User(login, password);
                int res1 = user.getConnection(Utils.getURL() + "/user");
                if (res1 != 202) {
                    System.out.println("HTTP error occured: " + res1);
                    return;
                } else {
                    System.out.println("Enter your message: ");
                    while (true) {
                        String text = scanner.nextLine();
                        if (text.isEmpty()) break;

                        Message m = new Message(login, text);
                        int res = m.send(Utils.getURL() + "/add");

                        if (res != 200) { // 200 OK
                            System.out.println("HTTP error occured: " + res);
                            return;
                        }
                    }
                }


            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
