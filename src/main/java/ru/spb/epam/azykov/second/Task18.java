package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask18;

/**
 * Created by zikovam on 13.05.16.
 */
public class Task18 implements ITestableTask18 {
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix (IRectangularIntegerMatrix matrix) {


        int i1,j1,start_item,end_n,end_m,count,found,by_row,bad_steps;
        for (int i=0; i<matrix.getHeight()-1; i++) {
            for (int j=0; j<matrix.getWidth()-1; j++) {
                start_item=matrix.getValue(i,j);
                end_n=i+1;
                end_m=j+1;
                found=0;
                by_row=0;
                bad_steps=0;
                while (end_n<matrix.getHeight() && end_m<matrix.getWidth()) {
                    count=0;
                    for (i1=i; i1<=end_n; i1++) {
                        for (j1=j; j1<=end_m; j1++) {
                            if (matrix.getValue(i1,j1)==start_item) count++;
                        }
                    }
                    if (count==(end_n-i+1)*(end_m-j+1)) {
                        found=1;
                        if (end_n<matrix.getHeight()-1) {
                            if (bad_steps<1) { end_n++; by_row=1; continue; }
                        }
                        else { bad_steps=1; }
                        if (end_m<matrix.getWidth()-1) {
                            if (bad_steps<2) { end_m++; by_row=2; continue; }
                        }
                        else { bad_steps=2; }
                    }
                    else {
                        if (found==0 && by_row==0) break;
                        else if (by_row==1) { end_n--; bad_steps++; }
                        else if (by_row==2) { end_m--; bad_steps++; }
                    }
                    if (bad_steps==2) break;
                }
//                if (found == 1) {
//                    for (i1=i; i1<=end_n; i1++)
//                        for (j1=j; j1<=end_m; j1++) a[i1][j1]=0;
//                }
            }
        }

        return null;
    }
}
