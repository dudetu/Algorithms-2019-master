package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        /* Временные затраты - О(н)
           Аппаратные - О(1)
         */
        int absoluteNull = 273;
        int absoluteMax = 500;
        int n = (absoluteNull +  absoluteMax) * 10 + 1;
        int[] count = new int[n];
        try (BufferedReader buffer = new BufferedReader(new FileReader(inputName))) {
            String line = buffer.readLine();
            while (line != null) {
                double number = Double.parseDouble(line);
                if (number < -1 * absoluteNull || number > absoluteMax) throw new IllegalArgumentException();
                int inMassive = (int) (number * 10);
                count[inMassive + absoluteNull * 10]++;
                line = buffer.readLine();
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < count[i]; j++) {
                    int outer = i;
                    if (outer < absoluteNull * 10) {
                        outer = absoluteNull * 10 - i;
                        writer.write("-" + Integer.toString(outer / 10) + "." + (outer % 10));
                        writer.newLine();
                    } else {
                        writer.write(Integer.toString(outer / 10 - absoluteNull) + "." + outer % 10);
                        writer.newLine();
                    }

                }

            }
        }

    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        /* Временные затраты - О(н)
           Аппаратные - О(н)
         */
        int maxCount = 0;
        int maxCountNumber = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(inputName))) {
            Map<Integer, Integer> countNumber = new HashMap<>();
            String line = buffer.readLine();
            while (line != null) {
                int number = Integer.parseInt(line);
                list.add(number);
                Integer count = countNumber.get(number);
                if (count == null) count = 0;
                countNumber.put(number, count + 1);
                if (count > maxCount) {
                    maxCount = count;
                    maxCountNumber = number;
                } else if (count == maxCount) {
                    if (number < maxCountNumber) {
                        maxCountNumber = number;
                    }
                }
                line = buffer.readLine();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
            final int max = maxCountNumber;
            list.stream().filter(integer -> integer != max).forEach(c -> {
                try {
                    writer.write(Integer.toString(c));
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            for (int i = 0; i <= maxCount; i++) {
                writer.write(Integer.toString(maxCountNumber));
                writer.newLine();
            }
        }
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
