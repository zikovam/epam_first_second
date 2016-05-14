package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zikovam on 08.05.16.
 */
public class Task6 implements ITestableTask6 {
    @Override
    public HashMap<Integer, Integer> addPolynomials (HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap <Integer, Integer> result = new HashMap<>();
        result.putAll(first);
        for (Map.Entry<Integer, Integer> entry :
            second.entrySet()){
            if (result.containsKey(entry.getKey())){
                result.put(entry.getKey(),entry.getValue()+first.get(entry.getKey()));
            }
            else{
                result.put(entry.getKey(),entry.getValue());
            }
        }

        return result;
    }
}
