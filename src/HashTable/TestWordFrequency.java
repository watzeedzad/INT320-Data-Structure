/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashTable;

/**
 *
 * @author jiraw
 */
public class TestWordFrequency {
    public static void main(String[] args) {
        WordFrequency wf = new WordFrequency();
        wf.processFile("data.txt");
        wf.print();
    }
}
