package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zikovam on 09.05.16.
 */
public class Task11 implements ITestableTask11 {
    @Override
    public String emulate (ArrayList<String> peoples) {
        String temp;

        while (peoples.size() != 1) {

            if (peoples.size() % 2 != 0) {
                temp = peoples.get(peoples.size() - 1);
                for (int i = 0; i < peoples.size(); i++) {
                    peoples.remove(i);
                }
                peoples.add(0, temp);
            } else {
                for (int i = 0; i < peoples.size() - 1; i++) {
                    peoples.remove(i);
                }
            }
            //System.out.println(peoples);
        }

        return peoples.get(0);
    }


    @Override
    public String emulate (LinkedList<String> peoples) {
        String temp;

        while (peoples.size() != 1) {
            Iterator i = peoples.iterator();
            //i.next();

            if (peoples.size() % 2 != 0) {
                temp = peoples.getLast();
                while (i.hasNext()){
                    i.next();
                    i.remove();
                    if (i.hasNext())
                        i.next();
                }
                peoples.addFirst(temp);
            } else {
                while (i.hasNext()){
                    i.next();
                    i.remove();
                    if (i.hasNext())
                        i.next();
                }
            }
            //System.out.println(peoples);
        }

        return peoples.get(0);
    }
}

