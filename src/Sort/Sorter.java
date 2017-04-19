package Sort;

import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author int320
 */
public class Sorter {

    public static void insertionSort(Object[] x) {
        insertionSort(x, null);
    }

    public static void insertionSort(Object[] x, Comparator cpt) { //ส่งcomparatorมา
        if (cpt != null) {
            insertionSortUsingComparator(x, cpt);
        } else {
            insertionSortUsingComparable(x);
        }
    }

    private static void insertionSortUsingComparator(Object[] x, Comparator cpt) {
        for (int i = 1; i < x.length; i++) {
            int j = i;
            Comparable tmp = (Comparable) x[i];
            for (; j > 0 && cpt.compare(tmp, x[j - 1]) < 0; j--) { //using comparator
                x[j] = x[j - 1];
            }

            if (j != i) {
                x[j] = tmp;
            }
        }
    }

    private static void insertionSortUsingComparable(Object[] x) {
        for (int i = 1; i < x.length; i++) {
            Comparable tmp = (Comparable) x[i]; // casting because x is Object
            int j = i;

            for (; j > 0 && tmp.compareTo(x[j - 1]) < 0; j--) {
                x[j] = x[j - 1];
            }

            if (j != i) {
                x[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        String[] names = {"X", "A", "B", "C", "M", "C", "D", "S", "J", "F", "E"};
        Integer[] numbers = {14, 21, 4, 1, 9, 18, 14, 32, 27, 77, 35, 17,};

//        Sorter.insertionSort(names, new ComparatorDesc());
//        Sorter.insertionSort(numbers, new ComparatorDesc());
        Sorter.quickSort(names);
        Sorter.quickSort(numbers);

        int count = 1;
        for (Integer i : numbers) {
            System.out.printf("%5d",  i);
            count++;
        }
        count = 1;
        System.out.println("\n------------------------------------------------\n");

        for (String str : names) {
            System.out.printf("%-5s", str);
            count++;
        }
    }

    // QuickSort *-**************************************************
    public  static void quickSort(Comparable x[]){
        quickSort(x,0,x.length-1);
//        insertionSort(x);
    }
    
    private static void swap(Comparable x[ ], int i, int j) {
        Comparable tmp = x [ i ] ;
        x [ i ] = x [ j ] ;
        x [ j ] = tmp ;
    }
    
    private final static int CUTOFF=2;
    
    private static void quickSort(Comparable x[], int left, int right) {
        if (right - left >= CUTOFF) {
//            int p = (left+right) / 2; //ใช้ค่าตรงกลาง
            int p = selectPivot(x, left, right);    // select pivot & sort left middle right
//             ใช้ select pivot ลำดับดูดีขึ้น ดูใกล้ๆเรียงมากกว่าเดิม
            swap(x, p, right); // hide pivot
            Comparable pivot = x[right];
            int i, j;
            for (i = left, j = right - 1; ;) {
                while (i < j && x[i].compareTo(pivot) < 0) {
                    i++;
                }
                while (i <= j && x[j].compareTo(pivot) >= 0) {
                    j--;
                }
                if (i >= j) {
                    break;
                }
                swap(x, i, j);
                i++; j++;
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
    
    private static int selectPivot (Comparable x [ ], int left, int right) {
        int middle = (left+right)/2;
        // sort left middle right
        if (x[middle].compareTo( x[left] ) < 0)
        swap( x, left, middle ) ;
        if (x[right].compareTo( x[left] ) < 0 )
        swap( x, left, right ) ;
        if ( x[right].compareTo( x[middle] ) < 0 )
        swap ( x, middle, right ) ;
        return middle;
    }

}

class ComparatorDesc implements Comparator { //ต้องไม่เป็น inner class

    @Override
    public int compare(Object o1, Object o2) { // obj ที่จะเอามาใช้มี Comparable อยู่แล้ว
        Comparable obj1 = (Comparable) o1;
        Comparable obj2 = (Comparable) o2;
        //             7          5
        return -obj1.compareTo(obj2); //ถ้าเป็น - เจอ - จะได้บวก
        //ถ้าเป็น 1 เจอ - จะได้ -1 ซึ่งในinsertionจะสลับก็ต่อเมื่อ <0
    }

}
