package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask12;

import java.util.Collections;
import java.util.List;

/**
 * Created by zikovam on 09.05.16.
 */
public class Task12 implements ITestableTask12 {
    @Override
    public List<Integer> transform (List<Integer> integers, int value) {
        Collections.sort(integers);
        return integers;
    }
}
