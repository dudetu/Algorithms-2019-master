package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        // Аппаратные затраты - О(n * m)
        // Временные О(n * m)
        int[][] dualMassive = new int[first.length() + 1][second.length() + 1];
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    dualMassive[i+1][j+1]=dualMassive[i][j] + 1;
                    if  (max < dualMassive[i+1][j+1]) {
                        max = dualMassive[i+1][j+1];
                        maxIndex = i;
                    }
                }
            }
        }
        return first.substring(maxIndex - max + 1, maxIndex + 1);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws IOException {
        //Аппаратные затраты - О(n * m)
        //Временные О(n * m * k), где k - количество слов - это только для трех вложенных for, однако
        //У нас еще есть рекурсия, зависящая от количества букв (в общем), так как всего 4 варианта существует, а
        //одно мы исрользуем на прошлом шаге (кроме первого случая), однако при следующих итерациях уже 2 варианта из 4 будут
        //использованы, в итоге, при устримлении количества букв в беск затротность нашей рекурентной функции 2 в степени числа букв
        // то в общем затраты будут - О(n * m * k * 2^(z))
        //где n и m - размер, k - слова и z - буквы
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int w;
        int h = 0;
        try (BufferedReader buffer = new BufferedReader(new FileReader(inputName))) {
            String line = buffer.readLine();
            while (line != null) {
                h++;
                list.add(line);
                line = buffer.readLine();
            }
        }
        w = list.get(0).length() / 2 + 1;
        char[][] symbols = new char[h][w];
        boolean[][] noWay = new boolean[h][w];
        for (int i = 0; i < list.size(); i++) {
            int count = 0;
            for (int j = 0; j < list.get(i).length(); j++) {
                char now = list.get(i).charAt(j);
                if (now != ' ') {
                    symbols[i][count] = now;
                    count++;
                }
            }
        }
        for (String string: words) {
            int symbolsEquals = 0;
            for (int i = 0; i < h; i++)
                for (int j = 0; j < w; j++) {
                    noWay = filler(noWay, h);
                    if (symbols[i][j] == string.charAt(0)) {
                        if (find(symbols, string, i, j, noWay, 1, h, w)
                                || string.length() == 1) set.add(string);
                    }
                }
        }
        return set;
    }


    static private boolean find(char[][] symbols, String string, int i, int j,
                                boolean[][] noWay, int numberOfSymbol, int h,int w) {
        noWay[i][j] = true;
        boolean go;
        numberOfSymbol++;
        if (numberOfSymbol  > string.length()) return true;
        if (i > 0 && !noWay[i-1][j] && symbols[i-1][j] == string.charAt(numberOfSymbol - 1)) {
            go = find(symbols, string, i - 1, j, noWay, numberOfSymbol, h, w);
            if (go) return true;
        }
        if (i < h - 1 && !noWay[i+1][j] && symbols[i+1][j] == string.charAt(numberOfSymbol - 1)) {
            go =find(symbols, string, i + 1, j, noWay, numberOfSymbol, h, w);
            if (go) return true;
        }
        if (j > 0 && !noWay[i][j-1] && symbols[i][j-1] == string.charAt(numberOfSymbol - 1)) {
            go = find(symbols, string, i, j-1, noWay, numberOfSymbol, h, w);
            if (go) return true;
        }
        if (j < w - 1 && !noWay[i][j+1] && symbols[i][j+1] == string.charAt(numberOfSymbol - 1)) {
            go = find(symbols, string, i, j + 1, noWay, numberOfSymbol, h, w);
            return go;
        }
        return false;
    }

    static private boolean[][] filler(boolean[][] massive, int h) {
        for (int i = 0; i < h; i++) {
            Arrays.fill(massive[i], false);
        }
        return massive;
    }
}