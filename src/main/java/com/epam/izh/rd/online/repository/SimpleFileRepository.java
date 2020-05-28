package com.epam.izh.rd.online.repository;

import javafx.scene.shape.Path;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long count = 0L;
        String absolutePath = "src/main/resources/" + path;
        File file = new File(absolutePath);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    count += this.countFilesInDirectory(path + "/" + f.getName());
                } else {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long count = 0L;
        String absolutePath = "src/main/resources/" + path;
        if (new File(absolutePath).isDirectory()) {
            count++;
        }
        File[] dirs = new File(absolutePath).listFiles();
        if (dirs != null) {
            for (File f : dirs) {
                if (f.isDirectory()) {
                    count += this.countDirsInDirectory(path + "/" + f.getName());
                }
            }
        }
        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File fromDirectory = new File(from);
        File toDirectory = new File(to);
        fromDirectory.getAbsoluteFile(); // не забыть, возможно можно пользоваться clone()
        return;
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File dir = new File("src/main/resources/" + path);
        if (!dir.exists()) {
            if (dir.mkdir()); {
                File file = new File(dir + "/" + name);
                try {
                    if (file.createNewFile()) {
                        return true;
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        return true;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        File file = new File("src/main/resources/" + fileName);
        StringBuilder stringBuilder = new StringBuilder("");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                stringBuilder.append(str);
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
