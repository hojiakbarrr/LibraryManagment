package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Serializable {

    private int id = Librar.genUniqueId();
    private String name;
    public String login;
    public String password;
    public List<Book> issuedBooks;
////////////////////////////////////////////////////////////////////////конструктор
    public Reader(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.issuedBooks = new ArrayList<>();
    }
/////////////////////////////////////////////////////////////////////////клон пароля и логина
    static String cloenLogin;
    static String cloenPassward;

    public String getCloenPassward() {
        return cloenPassward;
    }

    public void setCloenPassward(String cloenPassward) {
        this.cloenPassward = cloenPassward;
    }
/////////////////////////////////////////////////////////////////////все пользователи
    public static void getAllReaders() {
        for (Reader r : MainLibrary.librar.getReaderList()) {
            r.getInfo();
        }
        while (true) {
            System.out.println();
            System.out.println("_____Вернуться в главное меню нажмите - 1 _____");
            System.out.println("_____Желаете завершить процесс нажмите - 0 _____");
            try {
                int x = Integer.parseInt(MainLibrary.scanner.nextLine());
                switch (x) {
                    case 1:
                        MainLibrary.mainMenu();
                        break;
                    case 0:
                        MainLibrary.Vyhod();
                        break;
                }
            } catch (Exception exception) {
                System.err.println("Неверный формат ввода");
            }
        }
    }
//////////////////////////////////////////////////////взять книгу
    public static void takeBook() {
        for (Book b : MainLibrary.librar.getBookList()) {
            System.out.println("Название книги : " + b.getTitle() + (b.isInStock() ? " | Cтатус: доступно" : " | Cтатус: взято"));
        }
        boolean temp = true;

        while (temp) {
            System.out.println("---> Выбрать книгу");
            String search = MainLibrary.scanner.nextLine();
            if (search.equals("1")) {
                MainLibrary.mainMenu();
                break;
            }

            for (Book bi : MainLibrary.librar.getBookList()) {
                if (search.toLowerCase().contains(bi.getTitle().toLowerCase()) && bi.isInStock()) {
                    MainLibrary.loggedReader.getIssuedBooks().add(bi);
                    System.out.println(MainLibrary.loggedReader.name + ", ты взял книги(у) -=-> ");
                    for (int i = 0; i < MainLibrary.loggedReader.getIssuedBooks().size(); i++) {
                        System.out.println("=--> " + MainLibrary.loggedReader.getIssuedBooks().get(i).getTitle() +" <--=" );
                    }
                    bi.setInStock(false);
                    temp = false;
                    break;

                } else if (search.toLowerCase().contains(bi.getTitle().toLowerCase()) && !bi.isInStock()) {
                    System.out.println("Свободные книги : ");
                    for (Book gg : MainLibrary.librar.getBookList()) {
                        if (gg.isInStock()) {
                            System.out.print(" | " + gg.getTitle() + " | ");
                        }

                    }
                    System.out.println();
                }
            }
            if (temp) {
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-");
                System.out.println("1. Вернуться в главное меню ");
                System.out.println("-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-**-*-*-*-*");
                System.out.println("Такой книги нет в библиотеке или уже в статусе - !НеДоступно!");
                System.out.println("-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-**-*-*-*-*");

            }
        }
        while (true) {
            System.out.println();
            System.out.println("2. Взять еще одну книгу");
            System.out.println("1. Вернуться в главное меню ");
            System.out.println("0. Желаете завершить процесс ");

            try {
                int x = Integer.parseInt(MainLibrary.scanner.nextLine());

                switch (x) {
                    case 1:
                        MainLibrary.mainMenu();
                        break;
                    case 0:
                        MainLibrary.Vyhod();
                        break;
                    case 2:
                        takeBook();
                        break;
                }
            } catch (Exception exception) {
                System.err.println("Неверный формат ввода");
            }
        }
    }
//////////////////////////////////////////////////////вернуть книгу
    public static void returnBook() {
        boolean temp = true;


        if (MainLibrary.loggedReader.issuedBooks.size() == 0) {
            System.out.println("Ты еще не брал ни одну книгу к себе");
            While();
        } else {
            System.out.println("У тебя в наличии есть эти книги : ");
            for (Book bb : MainLibrary.loggedReader.getIssuedBooks()) {
                if (!bb.isInStock()) {
                    ArrayList<Book> books = new ArrayList<>();
                    books.add(bb);

                    for (int i = 0; i < MainLibrary.loggedReader.getIssuedBooks().size(); i++) {
                        System.out.println("=-=-> "+ MainLibrary.loggedReader.getIssuedBooks().get(i).getTitle());
                    }

                    System.out.println();
                    while (temp) {

                        System.out.println("Введи название книги которую хочешь вернуть");

                        String poisk = MainLibrary.scanner.nextLine();

                        if (poisk.equals("1")) {
                            MainLibrary.mainMenu();
                            break;
                        }
                        for (Book iu : MainLibrary.loggedReader.getIssuedBooks()) {
                            if (poisk.toLowerCase().equals(iu.getTitle().toLowerCase())) {
                                MainLibrary.loggedReader.getIssuedBooks().remove(iu);
                                for (Book fer : MainLibrary.librar.getBookList()) {
                                    if (poisk.toLowerCase().equals(fer.getTitle().toLowerCase()))
                                        fer.setInStock(true);

                                }
                                System.out.println("Ты вернул книгу");
                                temp = false;
                                While();
                            }

                        }
                        if (temp) {
//                            System.out.println("=-=-=-=-!!!-=-=-=-=");
                            System.out.println("_=_!!!_=_-_Ни эту книгу ты брал_-_=_!!!_=_");
                            System.out.println("1. Вернуться в главное меню ");
                        }
                    }
                }
            }
        }
    }
///////////////////////////////////////////////////////выход в главное меню
    public static void While() {
        while (true) {
            System.out.println();
            System.out.println("2. Вернуть еще одну книгу");
            System.out.println("1. Вернуться в главное меню ");
            System.out.println("0. Желаете завершить процесс ");

            try {

                int x = Integer.parseInt(MainLibrary.scanner.nextLine());
                switch (x) {
                    case 1:
                        MainLibrary.mainMenu();
                        break;
                    case 0:
                        MainLibrary.Vyhod();
                        break;
                    case 2:
                        returnBook();
                        break;
                }
            } catch (Exception exception) {
                System.err.println("Неверный формат ввода");
            }
        }
    }
/////////////////////////////////////////////////////////////////////////создание нового аккаунта
    public static void createAccount(List<Reader> readers) {

        String name = "";
        while (name == null || name.isEmpty()) {
            System.out.println("----------------------------------Введите имя нового читателя----------------------------------");
            name = Book.chekString(MainLibrary.scanner.nextLine());
        }

        String loginn;
        while (true) {
            System.out.println("----------------------------------Придумайте логин----------------------------------");
            loginn = MainLibrary.scanner.nextLine();
            if (!loginn.isEmpty()) {
                break;
            }
        }
        String password;
        while (true) {
            System.out.println("----------------------------------Придумайте пароль----------------------------------");
            password = MainLibrary.scanner.nextLine();
            if (!password.isEmpty()) {
                break;
            }
        }

        Reader reader = new Reader(name, loginn, password);
        MainLibrary.librar.getReaderList().add(reader);

        System.out.println("Добавлен новый читатель");
        while (true) {
            System.out.println();
            System.out.println("2. Добавить еще одного читателя");
            System.out.println("1. Вернуться в главное меню ");
            System.out.println("0. Желаете завершить процесс ");

            try {
                int x = Integer.parseInt(MainLibrary.scanner.nextLine());
                switch (x) {
                    case 1:
                        MainLibrary.mainMenu();
                        break;
                    case 0:
                        MainLibrary.Vyhod();
                        break;
                    case 2:
                        createAccount(readers);
                }
            } catch (Exception exception) {
                System.err.println("Неверный формат ввода1");
            }
        }

    }
//////////////////////////////////////////////////////////////////////////геттеры и сеттеры
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }
    void getInfo() {

        System.out.println("ID: " + getId() + " | Имя: " + getName()
                + " | Количество взятых книг: " + getIssuedBooks().size());


    }

}
