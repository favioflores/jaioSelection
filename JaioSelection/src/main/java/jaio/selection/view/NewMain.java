/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaio.selection.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Flores Sanchez
 */
public class NewMain {

    public static void main(String[] args) {

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();

        map.put("a", 11);
        map.put("B", 12);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 6);
        map.put("g", 7);
        map.put("h", 8);
        map.put("i", 9);
        map.put("j", 3);
        map.put("k", 2);
        map.put("l", 1);

        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(entries, new CustomizedHashMap());

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
            System.out.print(sortedMap.put(entry.getKey(),
                    entry.getValue()) + "  ");
        }
    }
}

class CustomizedHashMap implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
        // TODO Auto-generated method stub
        return -o2.getValue().compareTo(o1.getValue());
    }

}
