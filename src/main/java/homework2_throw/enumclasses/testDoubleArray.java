package homework2_throw.enumclasses;

/**
 * Перечисления содержит двумерные массивы для тестирования
 */
public enum testDoubleArray {
    TEST_ARRAY_TRUE( getFinalDataArrayTrue(new String[4][4])),
    TEST_ARRAY_DATA_BAD(generateDataArrayBad(new String[4][4])),
    TEST_ARRAY_SIZE_BAD_1(generateDataArrayBad(new String[5][4])),
    TEST_ARRAY_SIZE_BAD_2 (generateDataArrayBad(new String[4][2])),
    TEST_ARRAY_DATA_NULL(new String[0][0]);

    private String[][] array;

    public String[][] getArray() {
        return array;
    }

    testDoubleArray(String[][] array) {
        this.array = array;
    }

    /**
     * возвращает "правильно" заполненны двумерный массив.
     *
     * @param array строковый двумерный массив
     * @return заполненный массив "правильными" данными
     */
    private static String[][] getFinalDataArrayTrue(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Integer.toString(i + j);
            }
        }
        return array;
    }

    /**
     * возвращает "не правильно" заполненны двумерный массив.
     *
     * @param array строковый двумерный массив
     * @return заполненный массив "не правильными" данными
     */
    private static String[][] generateDataArrayBad(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = "s";
            }
        }
        return array;
    }
}
