package Series;

import SeriesExceptions.IllegalIndexException;

import java.util.Arrays;
import java.util.Objects;

public class BooksCollection implements Seriesable {
    private String title = "";
    private int numOfPrefacePages = 5;
    private int countBooks = 25;
    private String[] books;
    private int[] numsOfPages;

    public BooksCollection() {
        title = "";
        numOfPrefacePages = 5;
        books = new String[countBooks];
        numsOfPages = new int[books.length];
    }

    public BooksCollection(String title, int numOfPrefacePages, int numOfBooks) {
        this.title = title;
        this.numOfPrefacePages = numOfPrefacePages;
        books = new String[numOfBooks];
        numsOfPages = new int[books.length];
    }

    public String getTitle() {
        return title;
    }

    public int getCountStartPages() {
        return numOfPrefacePages;
    }

    public int getCountOfElements() {
        return books.length;
    }

    public String getElement(int index) {
        if (index < 0 || index >= books.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        return books[index];
    }

    public int getCountPages(int index) {
        if (index < 0 || index >= numsOfPages.length) {
            throw new IllegalIndexException("неверный индекс");
        }
        return numsOfPages[index];
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountStartPages(int num) {
        numOfPrefacePages = num;
    }

    public void setElement(int index, String title) {
        if (index < 0 || index >= books.length) {
            throw new IllegalIndexException("неверный индекс");
        }
        books[index] = title;
    }

    public void setNumOfPagesOfEl(int index, int num) {
        if (index < 0 || index >= books.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        numsOfPages[index] = num;
    }
    // функциональный метод
    public int getSumOfPagesWithoutIntro() {
        int sum = 0;
        for (int num : numsOfPages) {
            sum += num;
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        outputStr.append("Название сборника:").append(title).append('\n');
        outputStr.append("Кол-во страниц в предисловии:").append(numOfPrefacePages).append('\n');
        outputStr.append("Суммарное кол-во страниц (без предисловий):").append(getSumOfPagesWithoutIntro()).append('\n');
        outputStr.append("Кол-во статей в сборнике").append(books.length).append('\n');
        outputStr.append("Тип объекта").append(getClass()).append('\n').append("\n");
        return outputStr.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksCollection booksCollection = (BooksCollection) o;
        return numOfPrefacePages == booksCollection.numOfPrefacePages && countBooks == booksCollection.countBooks && title.equals(booksCollection.title) && Arrays.equals(books, booksCollection.books) && Arrays.equals(numsOfPages, booksCollection.numsOfPages);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, numOfPrefacePages, countBooks);
        result = 31 * result + Arrays.hashCode(books);
        result = 31 * result + Arrays.hashCode(numsOfPages);
        return result;
    }
}
