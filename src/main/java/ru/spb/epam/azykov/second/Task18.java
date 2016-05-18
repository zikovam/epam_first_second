package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask18;

/**
 * Created by zikovam on 13.05.16.
 */
public class Task18 implements ITestableTask18 {

    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix (IRectangularIntegerMatrix matrix) {


        if (matrix.getWidth() == 1 && matrix.getHeight() == 1) {
            IRectangularIntegerMatrix result = new IntMatrix(new int[][]{{matrix.getValue(0, 0)}});
            return result;
        }

        // параметры максимальной подматрицы
        int resBeginColumn = 0; //j1
        int resBeginStroke = 0; //i1
        int resEndColumn = 0;   //j2
        int resEndStroke = 0;   //i2
        int resMeaning = 0;
        for (int i = 0; i < matrix.getHeight(); i++) {

            int beginColumn = 0;

            for (int j = 1; j < matrix.getWidth(); j++) {

                if ((matrix.getValue(j, i) != matrix.getValue(j - 1, i)) ||
                        ((j == matrix.getWidth() - 1) && (matrix.getValue(j, i) == matrix.getValue(j - 1, i)))) {

                    int endColumn = j - 1;
                    if ((j == matrix.getWidth() - 1) && (matrix.getValue(j, i) == matrix.getValue(j - 1, i))) {
                        endColumn = j;
                    }

                    int endStroke = lookThrowColumns(i, beginColumn, endColumn, matrix.getValue(j - 1, i), matrix);
                    //проверяем кол-во элементов в текущей подматрице и нашей "максимальной"
                    int numElements = (j - beginColumn + 1) * (endStroke - i + 1);
                    int resNumElements = (resEndColumn - resBeginColumn + 1) * (resEndStroke - resBeginStroke + 1);
                    if (numElements > resNumElements) {
                        resBeginColumn = beginColumn;
                        resEndColumn = endColumn;
                        resBeginStroke = i;
                        resEndStroke = endStroke;
                        resMeaning = matrix.getValue(j - 1, i);
                    }

                    beginColumn = j;
                }
            }
        }

        int resStrokes = resEndStroke - resBeginStroke + 1;
        int resColumns = resEndColumn - resBeginColumn + 1;

        int[][] res = new int[resStrokes][resColumns];

//        System.out.println("result:");
        for (int i = 0; i < resStrokes; i++) {
            for (int j = 0; j < resColumns; j++) {
                res[i][j] = resMeaning;
//                System.out.print(resMeaning+"\t");
            }
//            System.out.println();
        }

        IRectangularIntegerMatrix result = new IntMatrix(res);

        return result;
    }

    private int lookThrowColumns (int beginStroke, int beginColumn, int endColumn, int numMeaning, IRectangularIntegerMatrix matrix) {

        int endStroke = beginStroke + 1;
        while (endStroke != matrix.getHeight()) {
            for (int i = beginColumn; i <= endColumn; i++) {
                if (matrix.getValue(i, endStroke) != numMeaning) {
                    return endStroke - 1;
                }
            }
            endStroke++;
        }
        return endStroke - 1;
    }

    class IntMatrix implements IRectangularIntegerMatrix {

        private final int[][] DATA;

        public IntMatrix (int[][] DATA) {
            this.DATA = DATA;
        }

        @Override
        public int getWidth () {
            return DATA[0].length;
        }

        @Override
        public int getHeight () {
            return DATA.length;
        }

        @Override
        public int getValue (int indexWidth, int indexHeight) {
            return DATA[indexHeight][indexWidth];
        }
    }
}

