package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zikovam on 08.05.16.
 */
public class Task7 implements ITestableTask7 {
    @Override
    public List<Integer> multiplyPolynomials (List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>(first.size()+second.size());

        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                if (result.size() <= i+j)
                    result.add(i+j, first.get(i)*second.get(j));
                else
                    result.set(i+j, first.get(i)*second.get(j) + result.get(i+j));
            }
        }

        int iter = result.size()-1;
        while((result.get(iter)==0)&&(result.size()>1)){
            result.remove(iter);
            iter--;
        }

        return result;
    }
}
