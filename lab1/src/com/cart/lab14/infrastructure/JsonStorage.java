package com.cart.lab14.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Проста інфраструктура зберігання даних у текстовий файл
 * у спрощеному JSON-подібному форматі (без зовнішніх бібліотек).
 * Кожен рядок файлу — один запис, поля розділені символом "|".
 */
public class JsonStorage {

    /**
     * Читає всі рядки файлу. Якщо файл не існує — повертає порожній список.
     */
    public List<String> readLines(String fileName) {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося прочитати файл: " + fileName, e);
        }
    }

    /**
     * Перезаписує файл новим списком рядків.
     */
    public void writeLines(String fileName, List<String> lines) {
        try {
            Files.write(Paths.get(fileName), lines);
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося записати файл: " + fileName, e);
        }
    }

    /**
     * Дописує один рядок у кінець файлу.
     */
    public void appendLine(String fileName, String line) {
        List<String> lines = readLines(fileName);
        lines.add(line);
        writeLines(fileName, lines);
    }
}