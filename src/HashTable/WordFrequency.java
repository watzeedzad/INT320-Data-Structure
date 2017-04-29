/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author jiraw
 */
public class WordFrequency {

    Map<String, List<Integer>> words = new TreeMap();

    public void processFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File " + fileName + "does not exit ..please try again");
            return;
        }

        try {
            Scanner sc = new Scanner(file);
            int countPos = 0;
            while (sc.hasNextLine()) {
                String line = sc.next();
                StringTokenizer stk = new StringTokenizer(line, ";.,\":()/\\<>?");
                while (stk.hasMoreTokens()) {
                    countPos++;
                    String word = stk.nextToken().toLowerCase();
                    //System.out.println(countPos + ": " + word);
                    if (words.get(word) == null) {
                        words.put(word, new LinkedList());
                    }
                    words.get(word).add(countPos);
                }
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File " + fileName + "does not exit ..please try again");
        }
    }

    public void print() {
        Set<Map.Entry<String, List<Integer>>> enties = words.entrySet();
        for (Map.Entry<String, List<Integer>> e : enties) {
            System.out.printf("%-15s : %3d->%s\n", e.getKey(), e.getValue().size(), e.getValue());
        }
    }

    public void clear() {
        words.clear();
    }

}
