package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask9;

import java.io.*;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zikovam on 09.05.16.
 */
public class Task9 implements ITestableTask9 {

    @Override
    public HashSet<String> getUniqueWords (File input) {

        HashSet<String> result = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {

            while (reader.ready()) {
                for (String str :
                        reader.readLine().split(" ")) {

                    if (!result.contains(str.toLowerCase())){
                        result.add(str);
                        //System.out.println(str);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
