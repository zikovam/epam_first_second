package ru.spb.epam.azykov.second;

import javafx.beans.binding.When;
import ru.spb.epam.common.second.ITestableTask14;
import ru.spb.epam.common.second.ITestableTask14.INumberCollection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static javafx.scene.input.KeyCode.T;

//import static javafx.scene.input.KeyCode.T;

/**
 * Created by zikovam on 09.05.16.
 */
public class Task14 implements ITestableTask14 {

    @Override
    public <T extends Number> INumberCollection<T> createCollection (Class<T> required) {

        return new myCollection<>();
    }


}

class myCollection <T extends Number> extends ArrayList<T> implements INumberCollection<T>{


    @Override
    public T nearest (T value) {
        if (this.isEmpty()) throw new NoSuchElementException();
        if (this.contains(value)) return value;
        BigDecimal temp = BigDecimal.valueOf(value.doubleValue() - this.get(0).doubleValue()).abs();
        T result = this.get(0);
        for (T number :
                this) {
            if (BigDecimal.valueOf(value.doubleValue() - number.doubleValue()).abs().compareTo(BigDecimal.valueOf(temp.doubleValue()))<0){
                result = number;
                temp = BigDecimal.valueOf(value.doubleValue() - number.doubleValue()).abs();
            }
        }
        return result;
    }
}