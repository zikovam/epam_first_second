package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Описание:
 * Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.
 *
 *  Формат входных данных:
 * File input - файл с исходными данными.
 * File output - файл для выгрузки измененных данных.
 *
 * Формат выходных данных:
 * List<String> - список строк, считанных из файла (в прямом порядке).
 */

public class Task1 implements ITestableTask1{

    @Override
    public List<String> reverseFile(File input, File output) {

        List<String> lines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            for (int i = lines.size()-1; i >= 0; i--) {
                writer.write(lines.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}