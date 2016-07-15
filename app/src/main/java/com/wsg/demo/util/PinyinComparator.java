package com.wsg.demo.util;

import com.wsg.demo.data.Person;

import java.util.Comparator;


/**
 * @author xiaanming
 */
public class PinyinComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getFirstLetter().equals("@") || o2.getFirstLetter().equals("#")) {
            return -1;
        } else if (o1.getFirstLetter().equals("#")
                || o2.getFirstLetter().equals("@")) {
            return 1;
        } else {
            return o1.getFirstLetter().compareTo(o2.getFirstLetter());
        }
    }

}
