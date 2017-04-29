/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author jiraw
 */
public class TestHashTable {
    public static void main(String[] args) {
        Map<Student, String> m = new TreeMap();
        m.put(new Student(10001, "SAOe",3.02), "A");
        m.put(new Student(10002, "SAOt",3.22), "B");
        m.put(new Student(10003, "SAOo",3.12), "C");
        m.put(new Student(10004, "SAOc",3.72), "D");
        m.put(new Student(10005, "SAOb",3.92), "E");
        m.put(new Student(10006, "SAOa",3.0), "F");
        m.put(new Student(10007, "SAOw",2.3), "G");
        m.put(new Student(10008, "SAOp",1.5), "H");
        m.put(new Student(10009, "SAOl",6.948), "I");
        
        Student x = new Student(10001, "SAOe",3.02);
        System.out.println(x.hashCode());
        
        System.out.println(m.get(x));
    }
}
