package com.epam.izh.rd.online.service;

import com.sun.corba.se.impl.encoding.BufferQueue;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        File file = new File("C:/EPAM/java-data-handling-template/src/main/resources/sensitive_data.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.readLine().replaceAll("(\\d{4}) \\d{4} \\d{4} (\\d{4})", "$1 **** **** $2");
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        return null;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        File file = new File("C:/EPAM/java-data-handling-template/src/main/resources/sensitive_data.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
