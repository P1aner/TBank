package TBank.task_4;
/*
Система исполнения «T-Saurus» разработана для параллельного выполнения процессов. Инженеры самой высокой квалификации разработали её таким образом, что одновременно может исполняться бесконечное количество процессов, т. е. вместимость исполнителя не ограничена. Причем, после запуска процесса он успешно завершается ровно через одну секунду.
Несмотря на невероятную эффективность параллелизации, некоторые процессы не могут быть начаты, пока другие не завершат своё исполнение (так часто бывает в реальных системах, когда один процесс использует результаты работы другого и соответственно не может быть запущен с ним параллельно). При этом один процесс может ожидать завершения нескольких процессов.
Все процессы в системе пронумерованы от 11 до 𝑛n. Для каждого процесса известно, результаты работы каких процессов ему потребуются для исполнения. Ваша задача состоит в том, чтобы определить, за какое минимальное количество секунд могут быть исполнены все процессы.
Гарантируется, что отсутствуют циклические зависимости, и процессы завершатся за конечное время. Также гарантируется, что процесс с номером 11 всегда будет завершаться последним.
Формат входных данных
В первой строке дано число 𝑛n (1≤𝑛≤105)(1≤n≤105) — количество процессов. Далее дано 𝑛n строк. В 𝑖i-й строке первым числом идёт 𝑎𝑖ai — количество необходимых 𝑖i-му процессу процессов для старта. Далее идет 𝑎𝑖ai чисел через пробел — их номера.
Формат выходных данных
В единственной строке выведите количество секунд — минимальное время, за которое все процессы смогут завершиться.
Примеры данных

Пример 1
Ввод:
5
3 2 3 5
1 4
0
0
1 3

Вывод: 3

Пример 2
Ввод:
6
1 2
1 3
1 4
1 5
1 6
0

Вывод: 6

Пример 3
Ввод:
6
5 2 3 4 5 6
0
0
0
0
0

Вывод: 2

Пример 4
Ввод:
3
0
0
0

Вывод: 1
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
        }
    }

    private static boolean hasNextThread(boolean[] threadsCompleted) {
        for (boolean b : threadsCompleted) {
            if (!b) return true;
        }
        return false;
    }
}
