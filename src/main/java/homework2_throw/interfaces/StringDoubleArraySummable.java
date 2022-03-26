package homework2_throw.interfaces;

import homework2_throw.throwsclasses.MyArrayDataException;
import homework2_throw.throwsclasses.MyArraySizeException;

public interface StringDoubleArraySummable {
    int summ(String[][] array) throws MyArraySizeException, MyArrayDataException;
}
