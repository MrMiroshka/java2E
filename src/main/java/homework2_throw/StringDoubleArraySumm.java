package homework2_throw;

import homework2_throw.interfaces.StringDoubleArraySummable;
import homework2_throw.throwsclasses.MyArrayDataException;
import homework2_throw.throwsclasses.MyArraySizeException;


public class StringDoubleArraySumm implements StringDoubleArraySummable {
    final private int count;

    private String getMessageErrorSize() {
        return "Размер массива отличается от размера " + count + "x" + count + "!";
    }

    private String getMessageErrorData(int y, int x) {
        return "В ячейке лежит символ или текст вместо числа: в строке - " + y + " в столбце - " + x + " !";
    }

    private boolean isFalseColumnLength(String[][] array) {
        for (String[] s : array) {
            if (s.length != 4) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int summ(String[][] array) throws MyArraySizeException,MyArrayDataException {
        if (array.length != count || isFalseColumnLength(array)) {
            throw new MyArraySizeException(getMessageErrorSize());
        }
        int summ = 0;

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                try {
                    summ += Integer.parseInt(array[i][j]);
                }
                catch (NumberFormatException exp){
                    throw new MyArrayDataException(getMessageErrorData(i,j));
                }

            }

        }

        return summ;
    }

    private StringDoubleArraySumm(Builder builder) {
        this.count = builder.count;
    }

    public static class Builder {
        private int count;

        public StringDoubleArraySumm.Builder count(int count) {
            this.count = count;
            return this;
        }

        public StringDoubleArraySumm build() {
            return new StringDoubleArraySumm(this);
        }

    }
}
