import Series.Seriesable;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Seriesable[] db = null; // сборник серий (сборник сборников)

        Scanner scan = new Scanner(System.in);
        String m;
        do {

            SeriesMenu.print("меню\n" +
                    "\n" +
                    " 1 - создать базу\n" +
                    " 2 - ввести значение элемента\n" +
                    " 3 - вывести базу\n" +
                    " 4 - найти пару в массиве\n"+
                    " 5 - разделить массив\n"+
                    " 8 - создать и заполнить базу\n" +
                    " 9 - создать и заполнить базу с аналогичными результатами\n"+
                    " 0 - выйти\n");

            m = scan.nextLine();

            switch (m) {
                case "1":
                    System.out.print("введите размер базы: ");
                    db = SeriesMenu.printGetSeriesableArr();
                    break;

                case "2":
                    SeriesMenu.printDbAsTitlesOfEls(db);
                    System.out.println();

                    SeriesMenu.printSetElOfDb(db);
                    break;

                case "3":
                    SeriesMenu.printDb(db);
                    break;

                case "4":
                    SeriesMenu.printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(db);
                    break;

                case "5":
                    SeriesMenu.printSplitDbIntoTwoArticlesAndBooksArrs(db);
                    break;

                case "8":
                    db = TestingSeries.createAndFillInDbWithFiveElsAutomatically();
                    SeriesMenu.print("база создана и заполнена!");
                    break;

                case "9":
                    db = TestingSeries.createAndFillInDbWithFiveElsAutomatically();
                    TestingSeries.setTwoSeriesableWithSameSumOfPagesWithoutStart(db);
                    SeriesMenu.print("база создана и заполнена!");
                    break;

                default:
                    break;
            }
        } while (!m.equals("0"));
    SeriesMenu.print("выход");
    }
}
