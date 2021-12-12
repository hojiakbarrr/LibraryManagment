package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book implements Serializable {

    private int id = Librar.genUniqueId();
    private String title;
    private String author;
    private boolean inStock;
    private String user;
////////////////////////////////////////////////////////////////////////конструктор
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.inStock = true;
        this.id = Librar.genUniqueId();
    }
//////////////////////////////////////////////////////////////////////////поиск книги
    public static void searchBook() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите ключевую фразу для поиска...");
                String search = scanner.nextLine();

                if (search.equals("1")) {
                    MainLibrary.mainMenu();
                }

                List<Book> books = new ArrayList<>();
                for (Book bo : MainLibrary.librar.getBookList()) {
                    if (bo.getAuthor().toLowerCase().matches("(.*)" + search.toLowerCase() + "(.*)") || bo.getTitle().toLowerCase().matches("(.*)" + search.toLowerCase() + "(.*)")) {
                        books.add(bo);
                    }
                }
                if (books.size() > 0) {
                    getAllBooks(books);
                    break;
                } else {
                    System.out.println("Такой книги нет...");
                    System.out.println("Вернуться в главное меню --> нажмите 1");
                    System.out.println();
                }


            } catch (Exception e) {
                System.err.println("Неверный формат ввода");
            }

        }
        while (true) {
            System.out.println();
            System.out.println("_____Вернуться в главное меню нажмите - 1 _____");
            System.out.println("_____Желаете завершить процесс нажмите - 0 _____");

            try {
                int x = Integer.parseInt(scanner.nextLine());
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
    //////////////////////////////////////////////////////////////////////////все книги
    public static void getAllBooks(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        for (Book b : bookList) {
            b.getInfo();
        }
        while (true) {
            System.out.println();
            System.out.println("_____Вернуться в главное меню нажмите - 1 _____");
            System.out.println("_____Желаете завершить процесс нажмите - 0 _____");

            try {
                int x = Integer.parseInt(scanner.nextLine());
                switch (x) {
                    case 1:
                        MainLibrary.mainMenu();
                        break;
                    case 0:
                        MainLibrary.Vyhod();
                        break;
                }
            } catch (Exception exception) {
                System.err.println("Неверный формат ввода2");
            }
        }

    }
    //////////////////////////////////////////////////////////////////////////получить информацию
    void getInfo() {
        System.out.println("ID: " + getId()
                + " | Название: " + getTitle()
                + " | Автор: " + getAuthor()
                + " | Статус: " + (isInStock() ? "Доступно" : "Взято"));
    }
//////////////////////////////////////////////////////////////////////////все взятые книги
    public static void getAllTakenBooks() {
        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>();
        for (Book bb : MainLibrary.librar.getBookList()) {
            if (!bb.isInStock()) {
                books.add(bb);
                bb.getInfo();
            }
        }

        if (books.size() == 0) {
            System.out.println("нет взятых книг");
        }


        while (true) {
            System.out.println();
            System.out.println("_____Вернуться в главное меню нажмите - 1 _____");
            System.out.println("_____Желаете завершить процесс нажмите - 0 _____");

            try {
                int x = Integer.parseInt(scanner.nextLine());
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
    //////////////////////////////////////////////////////////////////////////проверка
    static String chekString(String name) {


        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '1'
                    || name.charAt(i) == '2'
                    || name.charAt(i) == '3'
                    || name.charAt(i) == '4'
                    || name.charAt(i) == '5'
                    || name.charAt(i) == '6'
                    || name.charAt(i) == '7' || name.charAt(i) == '8' || name.charAt(i) == '9' || name.charAt(i) == ' ') {
                System.err.println("____Вы вводите цифру в поле имя____");
                return null;
            }
        }
        for (int i = 0; i < name.length(); i++) {
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return name;
    }
    //////////////////////////////////////////////////////////////////////////добавить новую книгу
    public static void addNewBook(List<Book> bookList) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название книги");
        String title = scanner.nextLine();

        String author = "";
        while (author == null || author.isEmpty()) {
            System.out.println("Введите автора книги");
            author = chekString(scanner.nextLine());
        }

        Book book = new Book(title, author);
        MainLibrary.librar.getBookList().add(book);
        System.out.println("Добавлена новая книга");
        while (true) {
            System.out.println();
            System.out.println("2. Добавить еще одну книгу");
            System.out.println("1. Вернуться в главное меню ");
            System.out.println("0. Желаете завершить процесс ");

            try {
                int x = Integer.parseInt(scanner.nextLine());
                switch (x) {
                    case 1:
                        MainLibrary.mainMenu();
                        break;
                    case 0:
                        MainLibrary.Vyhod();
                        break;
                    case 2:
                        addNewBook(bookList);
                }
            } catch (Exception exception) {
                System.err.println("Неверный формат ввода");
            }
        }
    }
//////////////////////////////////////////////////////////////////////////геттеры и сеттеры
    public void setInStock(boolean inStock) {
        this.inStock = inStock;

    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isInStock() {
        return inStock;
    }

}
