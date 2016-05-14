package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask3;

import java.util.*;

/**
 * Created by zikovam on 08.05.16.
 */
public class Task3 implements ITestableTask3 {
    @Override
    public List<String> sortPoems (Set<IPoem> poems, String author) {

        LinkedList<String> lines = new LinkedList<>();
        for (IPoem poem :
                poems) {
            if (poem.getAuthor() == author)
                lines.addAll(poem.getLines());
        }
        Collections.sort(lines);
        return lines;
    }
}

