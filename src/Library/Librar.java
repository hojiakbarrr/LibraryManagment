package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Librar implements Serializable {
    private List<Reader> readerList;
    private List<Book> bookList;
    static List<Integer> ids = new ArrayList<>();

    //////////////////////////////////////////////////////////////////////////конструктор
    public Librar(List<Reader> readerList, List<Book> bookList) {
        this.readerList = readerList;
        this.bookList = bookList;
    }
//////////////////////////////////////////////////////////////////////////геттеры и сеттеры
    public List<Reader> getReaderList() {
        return readerList;
    }
    public List<Book> getBookList() {
        return bookList;
    }
//////////////////////////////////////////////////////////////////////////проверка уни кода
    public static int genUniqueId() {
        int id = 0;
        while (true) {
            id = new  Random().nextInt(30);
            if (checkForDuplicates(id)) {
                ids.add(id);
                break;
            }
        }
        return id;
    }
    ///////////////////////////////////////////////////////////проверка кода
    private static boolean checkForDuplicates(int id) {
        for (int i : ids) {
            return (i != id) ? true : false;
        }
        return true;

    }
}
