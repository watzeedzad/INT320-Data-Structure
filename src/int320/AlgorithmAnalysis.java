
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Khaitong
 */
public class AlgorithmAnalysis {

    private static int DATA_SIZE = 100000;
    private static int ITEM_PER_LINE = 60;
    private static int MAX_VALUE = 999;
    private static int[] x, y, z;

    static {
        x = new int[DATA_SIZE];
        y = new int[DATA_SIZE];
        z = new int[DATA_SIZE];
        for (int i = 0; i < DATA_SIZE; i++) {
            int data = (int) (Math.random() * MAX_VALUE);
            x[i] = data;
            y[i] = data;
            z[i] = data;
        }
    }

    public static void main(String[] args) {
        //printData(x);
        //printData(y);

        long begin;
        long end;
        begin = System.currentTimeMillis();
        sortA(x);
        end = System.currentTimeMillis();
        long t1 = end - begin;

        begin = System.currentTimeMillis();
        sortB(y);
        end = System.currentTimeMillis();
        long t2 = end - begin;
        
        begin = System.currentTimeMillis();
        Arrays.parallelSort(z);
        end = System.currentTimeMillis();
        long t3 = end - begin;
        
        System.out.println("After sorting :::\n");
        System.out.println("Running time of Sort A " + t1);
        System.out.println("Running time of Sort B " + t2);
        System.out.println("Running time of parallelSort " + t3);
        //printData(x);
        //printData(y);
    }

    public static void printData(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%5d%s", data[i], (i + 1) % ITEM_PER_LINE == 0 ? "\n" : "");
        }
        System.out.println("\n--------------------------------\n");
    }

    public static void sortA(int[] data) {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                }
            }
        }
    }

    public static void sortB(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int x = data[i];
            int j = i;
            while (j > 0 && x < data[j - 1]) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = x;
        }

    }

    private static void swap(int[] data, int j, int i) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
