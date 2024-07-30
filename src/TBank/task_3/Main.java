package TBank.task_3;
/*
Виктория рассматривает занимательную квадратную двумерную матрицу, которая состоит только из натуральных чисел. Она то и дело суммирует числа в столбцах и строках. Виктория называет пару строки-столбца интересными, если сумма чисел в строке отличается от суммы чисел в столбце не более чем на величину числа на пересечении этих строки и столбца.
Найдите количество интересных пар строка-столбец во всей матрице.
Формат входных данных
В первой строке дано число 𝑛n (1≤𝑛≤1000)(1≤n≤1000) — размер квадратной матрицы. В последующих 𝑛n строках задано описание матрицы по 𝑛n целых неотрицательных чисел в каждой. Элементы матрицы 𝑎𝑖,𝑗ai,j лежат в диапазоне 0≤𝑎𝑖,𝑗≤1050≤ai,j≤105.
Формат выходных данных
Выведите количество искомых пар.
Примеры данных

Пример 1
Ввод:
2
1 9
0 5
Вывод: 1

Пример 2
Ввод:
4
1 1 4 3
1 2 9 2
1 4 2 1
5 2 1 3
Вывод: 10

Пример 3
Ввод:
4
0 0 0 0
0 1 1 0
0 1 1 0
1 1 1 0
Вывод: 7
 */
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int count = 0;
            int size = Integer.parseInt(scanner.nextLine());
            int[][] matrix = new int[size][size];
            long[] lineSum = new long[size];
            long[] columnSum = new long[size];
            for (int i = 0; i < size; i++) {
                int[] array = Arrays.stream(scanner.nextLine()
                                .split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                lineSum[i] = Arrays.stream(array)
                        .sum();
                matrix[i] = array;
            }
            for (int i = 0; i < size; i++) {
                columnSum[i] = 0;
                for (int j = 0; j < size; j++) {
                    columnSum[i] += matrix[j][i];
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (Math.abs(columnSum[i] - lineSum[j]) <= matrix[i][j]) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}
