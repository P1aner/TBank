package TBank.task_5;
/*
В предыдущей задаче была рассмотрена система исполнения процессов «T-Saurus». В этой задаче вам предстоит реализовать core-функционал этой системы. Вместо минимального времени, за которое могут быть выполнены все процессы, вам необходимо перечислить их порядок, при котором достигается это время.
Для этого вам необходимо разбить все процессы на непересекающиеся множества процессов (пронумеруем их от 11 до 𝑘k) так, чтобы сначала исполнитель «T-Saurus» параллельно выполнил все процессы первого множества, затем второго множества и т.д., и исполнение процессов удовлетворяло условию из предыдущей задачи. Т. е. в 𝑖i-ом множестве процессов должны присутствовать только те процессы, для которых все необходимые для их исполнения процессы включены в множествах с меньшими номерами 𝑗j: 1≤𝑗<𝑖1≤j<i. Напоминаем, что функционал системы «T-Saurus» состоит в том, что при последовательном исполнении предыдущих множеств процессов, все процессы в очередном множестве смогут исполниться.
Входные данные совпадают с входными данными из предыдущей задачи. Обратите внимание на то, как должно выводиться каждое множество — все процессы в рамках множества должны быть отсортированы.
Формат входных данных
В первой строке дано число 𝑛n (1≤𝑛≤105)(1≤n≤105) — количество процессов. Далее дано 𝑛n строк. В 𝑖i-й строке первым числом идёт 𝑎𝑖ai — количество необходимых 𝑖i-му процессу процессов. Далее идет 𝑎𝑖ai чисел через пробел — их номера.
Формат выходных данных
В единственной строке выведите число 𝑘k — количество множеств, на которое необходимо разбить все процессы. В следующих 𝑘k строках выведите описание этих множеств: первым числом укажите размер множества 𝑘𝑖ki, а далее через пробел 𝑘𝑖ki чисел в порядке возрастания — номера процессов очередного множества.
Примеры данных
Пример 1
Ввод:
5
3 2 3 5
1 4
0
0
1 3
Вывод:
3
2 3 4
2 2 5
1 1

Пример 2
Ввод:
6
1 2
1 3
1 4
1 5
1 6
0
Вывод:
6
1 6
1 5
1 4
1 3
1 2
1 1

Пример 3
Ввод:
6
5 2 3 4 5 6
0
0
0
0
0
Вывод:
2
5 2 3 4 5 6
1 1

Пример 4
Ввод:
3
0
0
0
Вывод:
1
3 1 2 3
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int threadsCount = Integer.parseInt(scanner.nextLine());
            int[][] matrix = new int[threadsCount][threadsCount];
            for (int i = 0; i < threadsCount; i++) {
                int[] threadsDependency = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                for (int j = 1; j < threadsDependency[0] + 1; j++) {
                    matrix[i][threadsDependency[j] - 1] = 1;
                }
            }
            int seconds = 0;
            boolean[] threadsCompleted = new boolean[threadsCount];
            HashMap<Integer, List<Integer>> objectObjectHashMap = new HashMap<>();
            while (hasNextThread(threadsCompleted)) {
                List<Integer> toChangeColumn = new ArrayList<>();
                for (int i = 0; i < threadsCount; i++) {
                    if (!threadsCompleted[i] && Arrays.stream(matrix[i]).sum() == 0) {
                        if (objectObjectHashMap.get(seconds) != null) {
                            objectObjectHashMap.get(seconds).add(i);
                        } else {
                            List<Integer> value = new ArrayList<>();
                            value.add(i);
                            objectObjectHashMap.put(seconds, value);
                        }
                        toChangeColumn.add(i);
                        threadsCompleted[i] = true;
                    }
                }
                for (Integer integer : toChangeColumn) {
                    for (int j = 0; j < threadsCount; j++) {
                        matrix[j][integer] = 0;
                    }
                }
                seconds++;
            }
            System.out.println(objectObjectHashMap.size());
            for (int i = 0; i < objectObjectHashMap.size(); i++) {
                List<Integer> list = objectObjectHashMap.get(i).stream().sorted().toList();
                StringBuilder s = new StringBuilder(String.valueOf(list.size()));
                for (Integer integer : list) {
                    s.append(" ").append(integer + 1);
                }
                System.out.println(s);
            }
        }
    }

    private static boolean hasNextThread(boolean[] threadsCompleted) {
        for (boolean b : threadsCompleted) {
            if (!b) return true;
        }
        return false;
    }
}
