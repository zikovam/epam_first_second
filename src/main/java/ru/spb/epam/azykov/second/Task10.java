package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by zikovam on 09.05.16.
 */
public class Task10 implements ITestableTask10 {
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String, Integer> result = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            while (reader.ready()) {
                for (String str : reader.readLine().split(" ")) {
                    if(result.containsKey(str)) result.put(str, result.get(str) + 1);
                    else result.put(str, 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
