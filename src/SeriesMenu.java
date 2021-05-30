import Series.ArticlesCollection;
import Series.BooksCollection;
import Series.Seriesable;
import SeriesExceptions.IllegalIndexException;

import static Series.Series.*;

import java.util.Scanner;

public class SeriesMenu {
    public static void print(String message) {
        System.out.println(message);
    }
    public static int printGetInt() {
        int num;

        Scanner scan = new Scanner(System.in);
        String str;
        do {
            System.out.print("введите число ... ");
            str = scan.nextLine();

            try {
                num = Integer.parseInt(str);
                break;
            } catch (NumberFormatException exc) {
                print("ошибка: введённая строка не является числом");
            }
        } while (true);

        return num;
    }

    public static int printGetIndex(int maxIndex) {
        int index;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите индекс ... ");
            str = scan.nextLine();
            System.out.println();

            try {
                index = Integer.parseInt(str);
                if (index < 0 || index > maxIndex) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalIndexException exc) {
                print("ошибка: неверный индекс");
            } catch (Exception exc) {
                print("ошибка: введённая строка не является числом");
            }
        } while (true);

        return index;
    }

    public static Seriesable[] printGetSeriesableArr() {
        int len;

        do {
            len = printGetInt();
            Seriesable[] seriesableArr = new Seriesable[len];
            print("массив размером в " + len + " элементов успешно создан");
            return seriesableArr;

        } while (true);
    }

    public static void printDbAsTitlesOfEls(Seriesable[] db) {
        System.out.print("база данных: ");
        if (db == null) {
            System.out.println("не задана");
        } else {

            for (int i = 0; i < db.length; i++) {
                System.out.print("[" + i + "] ");
                if (db[i] == null) {
                    System.out.println("элемент не задан");
                } else {
                    System.out.println('«' + db[i].getTitle() + '»');
                }
            }
        }
    }

    public static void printSetElOfDb(Seriesable[] db) {
        if (db == null) {
            print("операция невозможна: база данных не задана");
        } else {
            System.out.println("задайте индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля):");
            int index = printGetIndex(db.length - 1);

            Scanner scan = new Scanner(System.in);
            String str;

            System.out.print("задание элемента под индексом " + index + '\n');
            do {
                System.out.print("Выберите тип\n" +
                        "1 -- ArticleCollections\n" +
                        "2 -- BookCollection\n");
                str = scan.nextLine();
                System.out.println();

                if (str.equals("1")) {
                    db[index] = printGetArticlesSeries();
                    break;
                } else if (str.equals("2")) {
                    db[index] = printGetBooksSeries();
                    break;
                } else {
                    print("ошибка: неверный пункт меню");
                }
            } while (true);
        }
    }

    private static ArticlesCollection printGetArticlesSeries() {
        System.out.print("введите название сборника ................................. ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfArticles = printGetNumOfElsInSeries();
        int numOfAbstractPages = printGetNumOfStartPages();
        ArticlesCollection as = new ArticlesCollection(title, numOfAbstractPages, numOfArticles);
        print("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями статей и их количеством страниц\n");
        printSetElsOfSeries(as);

        return as;
    }

    private static BooksCollection printGetBooksSeries() {
        System.out.print("введите название сборника ........................... ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfBooks = printGetNumOfElsInSeries();
        int numOfPrefacePages = printGetNumOfStartPages();
        BooksCollection bs = new BooksCollection(title, numOfPrefacePages, numOfBooks);
        print("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями книг и их количеством страниц\n");
        printSetElsOfSeries(bs);

        return bs;
    }

    private static int printGetNumOfElsInSeries() {
        int num;

        do {
            System.out.print("задание количества элементов в серии: ... ");
            num = printGetInt();

            return num;
        } while (true);
    }

    private static int printGetNumOfStartPages() {
        int num;

        do {
            System.out.print("задание количества страниц в предисловии/аннотации каждого элемента серии: ");
            num = printGetInt();
            return num;
        } while (true);
    }

    private static void printSetElsOfSeries(Seriesable s) {
        if (s == null) {
            print("операция невозможна: серия не задана");
        } else {
            for (int i = 0; i < s.getCountOfElements(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n');
                try {
                    if (!printSetElOfSeries(s, i)) {
                        i--;
                    }
                } catch (Exception exc) {
                    print(exc.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }

    private static boolean printSetElOfSeries(Seriesable s, int index) throws Exception {
        if (s == null) {
            throw new UnsupportedOperationException("операция невозможна: серия не задана");
        } else {
            try {
                System.out.print("название ............................... ");
                Scanner scan = new Scanner(System.in);
                String title = scan.nextLine();
                s.setElement(index, title);

                System.out.print("количество страниц ... ");
                int numOfPages = printGetNum();
                s.setNumOfPagesOfEl(index, numOfPages);

                return true;
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exc) {
                print(exc.getMessage());
                return false;
            } catch (Exception exc) {
                throw new Exception(exc.getMessage());
            }
        }
    }

    private static int printGetNum() {
        int num = 0;
        do {
            num = printGetInt();
            if (num != 0){
                return num;
            }
        } while (true);
    }

    public static void printDb(Seriesable[] db) {
        System.out.print("база данных: ");
        if (db == null) {
            System.out.println("не задана");
        } else {
            System.out.println();

            for (int i = 0; i < db.length; i++) { // по элементам БД
                System.out.print("[" + i + "] ");
                printSeries(db[i]);
            }
        }
    }

    private static void printSeries(Seriesable s) {
        if (s == null) {
            print("серия не задана");
        } else {
            System.out.println('«' + s.getTitle() + '»');
            System.out.println(s);
        }
    }

    private static void printElsOfSeries(Seriesable s) {
        if (s == null) {
            System.out.println("серия не задана");
        } else {
            for (int i = 0; i < s.getCountOfElements(); i++) {
                System.out.print(i + ") ");

                if (s.getElement(i) == null) {
                    print("элемент не задан");
                } else {
                    System.out.println(s.getElement(i) +
                            " (кол-во страниц -- " + s.getCountPages(i) + ')');
                }
            }
        }
    }

    public static void printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] s) {
        Seriesable[] arr;

        try {
            arr = getArrWithTwoElsWithSameSumOfPagesWithoutStart(s);
            print("база успешно разделена");
            System.out.println();

            printDb(arr);
        } catch (Exception exc) {
            print(exc.getMessage());
        }
    }

    public static void printSplitDbIntoTwoArticlesAndBooksArrs(Seriesable[] s) {
        if (s == null) {
            print("база не создана");
        } else {
            try {
                ArticlesCollection[] as = getArticlesSeriesArrFromSeriesableArr(s);
                BooksCollection[] bs = getBooksSeriesArrFromSeriesableArr(s);
                print("база данных разбита на два массива, в которых хранятся однотипные элементы");
                System.out.println();

                printDb(as);
                printDb(bs);
            } catch (Exception exc) {
                print(exc.getMessage());
            }
        }
    }
}
