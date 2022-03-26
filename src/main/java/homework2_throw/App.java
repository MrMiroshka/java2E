package homework2_throw;

import homework2_throw.enumclasses.testDoubleArray;
import homework2_throw.interfaces.StringDoubleArraySummable;
import homework2_throw.throwsclasses.MyArrayDataException;
import homework2_throw.throwsclasses.MyArraySizeException;

/**
 * Домашнее задание №2 курс Java 2
 * @author Miroshnichenko Igor
 * @version 2.2
 * created on 2022-03-25
 */
public class App {

    /**
     * Выводит в консоль имя тестируемого двумерного массива и выводит данный массив
     * @param array
     */
    private static void printHeader(testDoubleArray array) {
        System.out.println();
        System.out.println("**************************************************************");
        System.out.println("Передаем двумерный массив - "+array.name() +" :");
        if (array.getArray().length!=0) {
            for (String[] s : array.getArray()) {
                for (String str : s) {
                    System.out.print(" " + str + " ");
                }
                System.out.println();
            }
        }else{
            System.out.println("Передаем пустой двумерный массив");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //зададим длинну квадратной матрицы
        int count = 4;

        //Далее по разному решаю задачу с помощью builder, через анонимный класс,
        // через лямда выражение (обертка над анонимным классом)

        for (testDoubleArray arrayTest : testDoubleArray.values()) {

            printHeader(arrayTest);
            System.out.println("Решение с помощью паттерна - 'Builder':");

            try {
                System.out.println(new StringDoubleArraySumm.Builder()
                        .count(count).build().summ(arrayTest.getArray()));
            } catch (MyArraySizeException exp) {
                //можно было вывести все через sout, но тогда не было бы дополнительной информации
                exp.printStackTrace(System.out);
            } catch (MyArrayDataException exp) {
                exp.printStackTrace(System.out);
            }

            System.out.println();
            System.out.println("Решение через анонимный класс:");

            final StringDoubleArraySummable summAnonimClass = new StringDoubleArraySummable() {
                @Override
                public int summ(String[][] array) throws MyArraySizeException, MyArrayDataException {
                    boolean isFalseColumnLength = false;
                    for (String[] s : array) {
                        if (s.length != count) {
                            isFalseColumnLength = true;
                            break;
                        }
                    }

                    if (array.length != count || isFalseColumnLength) {
                        throw new MyArraySizeException("Размер массива отличается от размера " + count + "x" + count + "!");
                    }
                    int summ = 0;

                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < count; j++) {
                            try {
                                summ += Integer.parseInt(array[i][j]);
                            } catch (Exception exp) {
                                throw new MyArrayDataException("В ячейке лежит символ или текст вместо числа: в строке - " + i + " в столбце - " + j + " !");
                            }

                        }

                    }

                    return summ;
                }
            };
            try {
                System.out.println(summAnonimClass.summ(arrayTest.getArray()));
            } catch (MyArraySizeException exp) {
                exp.printStackTrace(System.out);
            } catch (MyArrayDataException exp) {
                exp.printStackTrace(System.out);
            }

            System.out.println();
            System.out.println("Решение через лямда выражение:");

            final StringDoubleArraySummable summLamda = array -> {
                boolean isFalseColumnLength = false;
                for (String[] s : array) {
                    if (s.length != count) {
                        isFalseColumnLength = true;
                        break;
                    }
                }

                if (array.length != count || isFalseColumnLength) {
                    throw new MyArraySizeException("Размер массива отличается от размера " + count + "x" + count + "!");
                }
                int summ = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < count; j++) {
                        try {
                            summ += Integer.parseInt(array[i][j]);
                        } catch (Exception exp) {
                            throw new MyArrayDataException("В ячейке лежит символ или текст вместо числа: в строке - " + i + " в столбце - " + j + " !");
                        }

                    }

                }

                return summ;
            };
            try {
                System.out.println(summLamda.summ(arrayTest.getArray()));
            } catch (MyArraySizeException exp) {
                exp.printStackTrace(System.out);
            } catch (MyArrayDataException exp) {
                exp.printStackTrace(System.out);
            }
        }
    }
}
