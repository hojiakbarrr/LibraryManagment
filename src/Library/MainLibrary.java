package Library;

import Library.Book;

import java.io.*;
import java.util.*;

import static java.lang.Math.random;

public class MainLibrary {

    static Librar librar;
    static Scanner scanner;
    public static Reader loggedReader;
    static String fr;



    public static void main(String[] args) {



        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry_Potter", "Joanne Kathleen Rowling"));
        books.add(new Book("Shaitanat", "Tohit Malik"));
        books.add(new Book("Chelovek_nevidimka", "Herbert Wales"));


        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Tom", "t", "t"));
        readers.add(new Reader("Jerry", "jerry", "jerry1234"));
        readers.add(new Reader("Hardy", "hardy", "hardy1234"));


        librar = new Librar(readers, books);
        try {
            
            ObjectInputStream ios = new ObjectInputStream(new FileInputStream("database"));
            librar = (Librar) ios.readObject();
            ios.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("База данных повреждена или не найдена");
        }
        scanner = new Scanner(System.in);


        mainMenu();


    }

    public static void mainMenu() {
        System.out.println("Добро пожаловать в систему управления библиотекой");
        System.out.println("***********************************");


        while (true) {

            System.out.println("1. Поиск книг");
            System.out.println("2. Просмотр всех книг");
            System.out.println("3. Просмотр всех читателей");
            System.out.println("4. Просмотр всех взятых книг");
            System.out.println("5. Добавить книгу");
            System.out.println(loggedReader != null ? "6. Взять книгу" : "6. Войти");
            System.out.println(loggedReader != null ? "7. Вернуть книгу" : "7. Зарегистрироваться");
            System.out.println("8. Выход");


            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 9 && choice > 0) {
                    switch (choice) {
                        case 1:
                            Book.searchBook();
                            break;
                        case 2:
                            Book.getAllBooks(librar.getBookList());
                            break;
                        case 3:
                            Reader.getAllReaders();
                            break;
                        case 4:
                            Book.getAllTakenBooks();
                            break;
                        case 5:
                            Book.addNewBook(librar.getBookList());
                            break;
                        case 6:
                            if (loggedReader != null) {
                                Reader.takeBook();
                            } else login();
                            break;
                        case 7:
                            if (loggedReader != null) {
                                Reader.returnBook();
                            } else Reader.createAccount(librar.getReaderList());
                            break;
                        case 8:
                            Vyhod();
                    }
                } else {
                    System.err.println("Введите правильное число!");
                    System.out.println();
                }
            } catch (Exception e) {
                System.err.println("Неверный формат ввода3");
                System.out.println();

            }
        }
    }

    public static void Vyhod() {
        System.out.println();
        System.out.println("-----> Изменения сохранены <-----");
        System.out.println("...............Bye.............");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database"));

            oos.writeObject(librar);
            oos.close();


        } catch (IOException e) {
            System.out.println("Данные не сохранились !!!");
        }

        System.exit(0);
    }

    private static void login() {

        int count = 0;
        boolean temp = true;

        System.out.println("----------------------------Войти в систему управления Библиотекой-------------------------------------------" + "\n");

        while (temp) {
            count++;

            System.out.println("-------Ваш логин------");
            String login = scanner.nextLine();

            System.out.println("-------Введите ваш пароль-------");
            String pfrol = scanner.nextLine();
            for (int i = 0; i < librar.getReaderList().size(); i++) {
                if (librar.getReaderList().get(i).getLogin().equals(login) && librar.getReaderList().get(i).getPassword().equals(pfrol)) {
                    loggedReader = librar.getReaderList().get(i);
                    fr = librar.getReaderList().get(i).getName();
                    temp = false;
//                    Reader.cloenLogin = login;
//                    Reader.cloenPassward = pfrol;
                }
                if (count >= 3) {
                    System.out.println("----Обратись к администраору----"
                            + "\n" + "----попыток войти в систему :" + count);
                    temp = false;

                    while (true) {
                        System.out.println();
                        System.out.println("1. Вернуться в главное меню ");
                        System.out.println("0. Желаете завершить процесс ");

                        try {
                            int x = Integer.parseInt(scanner.nextLine());
                            switch (x) {
                                case 1:
                                    mainMenu();
                                    break;
                                case 0:
                                    Vyhod();
                                    break;
                            }
                        } catch (Exception exception) {
                            System.err.println("Неверный формат ввода4");
                        }
                    }
                }
            }
            if (temp) {
                int x = 3 - count;
                System.out.println("------------Ваши данные не правильные, повторите ввод----------------"
                        + "\n" + "---------------попыток осталось --> " + x + " <----------------");
            }
        }
    }
}