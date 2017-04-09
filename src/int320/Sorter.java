/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int320;

import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author jiraw
 */
public class Sorter {

    public static void insertionSort(Object[] x) {
        insertionSort(x, null);
    }

    public static void insertionSort(Object[] x, Comparator cpt) {
        if (cpt != null) {
            insertionSortUsingComparator(x, cpt);
        } else {
            insertionSortUsingComparable(x);
        }
    }

    private static void insertionSortUsingComparator(Object[] x, Comparator cpt) {
        for (int i = 1; i < x.length; i++) {
            Comparable tmp = (Comparable) x[i];
            int j = i;
            for (; j > 0 && cpt.compare(tmp, x[j - 1]) < 0; j--) {
                x[j] = x[j - 1];
            }
            if (j != i) {
                x[j] = tmp;
            }
        }
    }

    private static void insertionSortUsingComparable(Object[] x) {
        for (int i = 1; i < x.length; i++) {
            Comparable tmp = (Comparable) x[i];
            int j = i;
            for (; j > 0 && tmp.compareTo(x[j - 1]) < 0; j--) {
                x[j] = x[j - 1];
            }
            if (j != i) {
                x[j] = tmp;
            }
        }
    }

    private final static int CUTOFF = 3;

    public static void quickSort(Comparable x[], int left, int right) {
        if (right - left >= CUTOFF) {
            int p = (left / right) / 2; // select pivot & sort left middle right
            swap(x, p, right); // hide pivot
            Comparable pivot = x[right];
            int i, j;
            for (i = left, j = right - 1;;) {
                while (i <= j && x[i].compareTo(pivot) < 0) {
                    i++;
                }
                while (i <= j && x[j].compareTo(pivot) >= 0) {
                    j--;
                }
                if (i >= j) {
                    break;
                }
                swap(x, i++, j--);
            }
            if (x[i].compareTo(pivot) > 0) // restore pivot
            {
                swap(x, i, right);
            }
            if (left < i) {
                quickSort(x, left, i - 1);
            }
            if (right > i) {
                quickSort(x, i + 1, right);
            }
        }

    }

    private static void swap(Comparable x[], int i, int j) {
        Comparable tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }

    public static void main(String[] args) {
        String[] names = {"X", "A", "B", "C", "M", "C", "D", "S", "J", "F", "E"};
        Integer[] numbers = {14, 21, 4, 1, 9, 18, 14, 32, 27, 77, 35, 17};

        Sorter.insertionSort(names, new ComparatorDesc());
        Sorter.insertionSort(numbers, new ComparatorDesc());

        for (int i : numbers) {
            System.out.printf("%5d", i);
        }
        System.out.println("\n-------------------------------------------");

        for (String str : names) {
            System.out.printf("%-5s", str);
        }
        System.out.println("\n-------------------------------------------");
    }
}

class ComparatorDesc implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Comparable obj1 = (Comparable) o1;
        Comparable obj2 = (Comparable) o2;
        return -obj1.compareTo(o2);
    }

}
