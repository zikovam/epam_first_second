package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask2;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Описание:
 * Сформировать множество файлов и каталогов, входящих в корневой каталог и его подкаталоги.
 *
 * Формат входных данных:
 * File directory - корневой каталог.
 *
 * Формат выходных данных:
 * Set<File> - множество обнаруженных в каталоге элементов.
 */

public class Task2 implements ITestableTask2 {
    @Override
    public Set<File> getFiles (File directory) {
        Set<File> files = new TreeSet<>();
        if (directory.exists() && (directory.listFiles() != null)) {
            lookThrowDirectory(directory, files);
        }
       return files;
    }

    private void lookThrowDirectory (File directory, Set<File> files){
        for (File file :
                directory.listFiles()) {

            files.add(file);
            //System.out.println(file);
            if (file.isDirectory())
                lookThrowDirectory(file, files);
        }
    }
}
