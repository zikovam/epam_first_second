package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask8;

import java.util.Stack;

/**
 * Created by zikovam on 09.05.16.
 */
public class Task8 implements ITestableTask8 {

    @Override
    public boolean isNormalBrackets (String string) {
        Stack<Character> brackets = new Stack<>();

        for (Character symbol:
                string.toCharArray()){

            if (symbol == '(' || symbol == '{' || symbol == '[') {
                brackets.push(symbol);
            }
            else if (symbol == ')') {
                if (brackets.empty())
                    return false;
                else if (symbol - brackets.peek() == 1)
                    brackets.pop();
            }
            else if (symbol == '}' || symbol == ']') {
                if (brackets.empty())
                    return false;
                else if (symbol - brackets.peek() == 2)
                    brackets.pop();
            }
        }

        //System.out.println(brackets);

        if (brackets.empty())
            return true;
        else
            return false;
    }
}
