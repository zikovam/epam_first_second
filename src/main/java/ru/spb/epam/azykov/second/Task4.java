package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zikovam on 08.05.16.
 */
public class Task4 implements ITestableTask4 {

    @Override
    public Set<Integer> intersection (Set<Integer> first, Set<Integer> second) {
        if (first.isEmpty() || second.isEmpty())
            return new HashSet<>();
        else {
            Set<Integer> answer = new HashSet<>();
            answer.addAll(first);
            answer.retainAll(second);
            return answer;
        }
    }

    @Override
    public Set<Integer> union (Set<Integer> first, Set<Integer> second) {
        if (first.isEmpty()) return second;
        if (second.isEmpty()) return first;
        else {
            Set<Integer> answer = new HashSet<>();
            answer.addAll(first);
            answer.addAll(second);
            return answer;
        }
    }
}
