package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //Затраты памяти- O(n)
    //Временные - O(n * n)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.isEmpty()) return list;
        int number = 0;
        int maxOfAll = -1;
        List<Integer> answer = new ArrayList<>();
        int[] size = new int[list.size()];
        for (int i = size.length - 1; i >= 0; i--) {
            int max = -1;
            for (int j = i + 1; j <list.size(); j++) {
                if (list.get(i) < list.get(j) && size[j] > max) {
                    max = size[j];
                }
            }
            size[i] = max + 1;
            if (size[i]>=maxOfAll) {
                maxOfAll = size[i];
                number = i;
            }
        }
        while (maxOfAll > -1) {
            for (int i = number; i < list.size(); i++) {
                if (size[i] == maxOfAll && list.get(number) <= list.get(i)) {
                    answer.add(list.get(i));
                    maxOfAll--;
                    number = i;
                    break;
                }
            }
        }
        return answer;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    //Затраты памяти - O(n * m)
    //Временные - O(n * m)
    public static int shortestPathOnField(String inputName) throws IOException {
        List<String[]> field = new ArrayList<>();
        int[][] answer;
        try(
    BufferedReader buffer = new BufferedReader(new FileReader(inputName))) {
            String line = buffer.readLine();
            while (line != null) {
                String[] sepLine = line.split(" ");
                field.add(sepLine);
                line = buffer.readLine();
            }
            answer = new int[field.size()+1][field.get(0).length+1];
            for (int i = 0; i < answer.length; i++) {
                for (int j = 0; j < answer[0].length; j++) {
                    if (i == 0 && j == 0) {
                        answer[i][j] = 0;
                    } else if (i == 0 || j == 0) answer[i][j] = Integer.MAX_VALUE;
                    else { answer[i][j] = Integer.parseInt(field.get(i - 1)[j - 1]);
                    answer[i][j] += Math.min(Math.min(answer[i-1][j-1], answer[i-1][j]), answer[i][j-1]); }
                }
            }
        } return answer[answer.length - 1][answer[0].length - 1];
    }


    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
