package ru.spb.epam.azykov.first;

import ru.spb.epam.common.first.ISolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Реализует выполнение первого задания
 */
public class Solver implements ISolver {

//    Ввести N строк, найти самую короткую и самую длинную строки.
//    Вывести найденные строки и их длину. Если строк, удовлетворяющих условию, более одной - вывести последнюю из них.
    public void task1 () {
        int n; //количество строк
        String[] text;
        Scanner scan = new Scanner (System.in);
        if (scan.hasNextInt ()) { // возвращает истинну если с потока ввода можно считать целое число
            n = Integer.parseInt (scan.nextLine ());
            int indexMax = 0;
            int indexMin = 0;
            text = new String[n]; //массив строк

            for (int i = 0; i < n; i++) {
                text[i] = scan.nextLine ();
                if (text[i].length () <= text[indexMin].length ())
                    indexMin = i;
                if (text[i].length () >= text[indexMax].length ())
                    indexMax = i;
            }

            //System.out.println("Количество строк:" + n );
            System.out.printf("MIN (%d): \"%s\"%n", text[indexMin].length (), text[indexMin]);
            System.out.printf("MAX (%d): \"%s\"%n", text[indexMax].length (), text[indexMax]);
        }
    }

//    Ввести N строк. Упорядочить и вывести строки в порядке возрастания значений их длины.
//    В случае, если длины строк совпадают - упорядочить их в лексикографическом порядке.
    public void task2 () {
        int n; //кол-во строк
        Scanner scan = new Scanner (System.in);
        if (scan.hasNextInt ()) {
            n = Integer.parseInt (scan.nextLine ());
            List<String> text = new ArrayList<String> ();
            for (int i = 0; i < n; i++) {
                text.add (scan.nextLine ());
            }
            StringLengthListSort sort = new StringLengthListSort ();
            Collections.sort (text, sort);

            for (int i = 0; i < n; i++) {
                System.out.println ("(" + text.get (i).length () + "): \"" + text.get (i) + "\"");
            }
        }
    }

    // Sort method for the task2
        class StringLengthListSort implements Comparator<String> {

        public int compare (String s1, String s2) {

            return s1.compareTo (s2);
        }
    }

//    Ввести N строк. Вывести те строки, длина которых меньше средней.
//    Под 'средней' подразумевается среднеарифметическая величина длины всех строк, округленная до целого в меньшую сторону.
    public void task3 () {
        int n; //кол-во строк
        Scanner scan = new Scanner (System.in);
        if (scan.hasNextInt ()) {
            n = Integer.parseInt (scan.nextLine ());
            int average = 0;
            String[] text = new String[n];
            for (int i = 0; i < n; i++) {
                text[i] = scan.nextLine ();
                average += text[i].length ();
            }
            average = average / n;

            System.out.printf ("AVERAGE (%d)\n", average);
            for (int i = 0; i < n; i++) {
                if (text[i].length () < average)
                    System.out.println ("(" + text[i].length () + "): \"" + text[i] + "\"");
            }
        }
    }

//    Ввести N слов. Найти слово, в котором число различных символов минимально. Если таких слов несколько, найти первое из них.
    public void task4 () {
        int n; //количество строк
        String[] words;
        String text;
        Scanner scan = new Scanner (System.in);
        if (scan.hasNextInt ()) {
            n = Integer.parseInt (scan.nextLine ());
            text = scan.nextLine ();
            words = text.split (" ", n);

            String wordMinCharVar = words[0];
            int sizeMinCharVar = new HashSet<String> (Arrays.asList (words[0].split (""))).size ();

            //другой вариант реализации
//            for (int i = 1; i < words.length; i++) {
//                Set<String> set = new HashSet<String> (Arrays.asList (words[i].split ("")));
//                if (set.size () < sizeMinCharVar) {
//                    //System.out.println("Слово - " + word[i]);
//                    indexMinCharVar = i;
//                    sizeMinCharVar = set.size ();
//                }
//            }

            for (String word :
                    words) {
                Set<String> set = new HashSet<String> (Arrays.asList (word.split ("")));
                if (set.size () < sizeMinCharVar) {
                    //System.out.println("Слово - " + word[i]);
                    wordMinCharVar = word;
                    sizeMinCharVar = set.size ();
                }
            }
            System.out.println (wordMinCharVar);
        }
    }

//    Ввести N слов. Найти количество слов, содержащих только символы латинского алфавита,
//    а среди них – количество слов с равным числом гласных и согласных букв.
    public void task5 () {
        int n; //количество строк
        int numWordsWithEqualLetters = 0;
        String text;
        Scanner scan = new Scanner (System.in);
        if (scan.hasNextInt ()) {
            n = Integer.parseInt (scan.nextLine ());
            text = scan.nextLine ();
            for (String nowWord : text.split (" ", n)) {                 //разделили строку на слова и делаем проход по словам
                int countEngLetters = 0;
                int balance = 0;
                for (Character letter : nowWord.toCharArray ()             //побуквенно бежим по слову
                        ) {
                    //System.out.print(letter + " ");
                    if (((letter >= 'A') && (letter <= 'Z')) || ((letter >= 'a') && (letter <= 'z'))) {
                        countEngLetters++;
                        if ((letter == 'A') || (letter == 'E') || (letter == 'I') || (letter == 'O') || (letter == 'U') || (letter == 'Y')
                                || (letter == 'a') || (letter == 'e') || (letter == 'i') || (letter == 'o') || (letter == 'u') || (letter == 'y'))
                            ++balance;
                        else
                            --balance;
                    }
                }
                if (countEngLetters == nowWord.length ()) {                 //проверяем, всё ли слово английское
                    //System.out.println("english word");
                    if (balance == 0) {                                 //проверяем, есть ли "баланс букв"
                        ++numWordsWithEqualLetters;
                        //System.out.println(nowWord + " this word ia our word!");
                    }
                }

            }
            System.out.println (numWordsWithEqualLetters);
        }
    }

//    Ввести N слов. Найти слово, символы в котором идут в строгом порядке возрастания их кодов.
//    Если таких слов несколько, найти первое из них.
    public void task6 () {
        int n; //кол-во слов
        String text;
        Scanner scan = new Scanner (System.in);
        if (scan.hasNextInt ()) {
            n = Integer.parseInt (scan.nextLine());
            text = scan.nextLine ();
            int wordCount = 0;
            for (String word :
                    text.split (" ", n)) {
                int count = 0;
                for (int i = 1; i < word.length (); i++) {
                    if (word.toCharArray ()[i] > word.toCharArray ()[i - 1]) {
                        count++;
                    }
                }
                //System.out.println("Word = "+word+": count = "+count);
                if ((word.length () - 1 == count) && (word.length () > 1)) {
                    System.out.print (word);
                    wordCount++;
                }

            }
            if (wordCount == 0)
                System.out.print ("NOT FOUND");
        }
    }

//    Ввести N слов. Найти слова, состоящие только из различных символов.
//    В случае, если слово встречается более одного раза - вывести его единожды.
//    ТУТ НЕ УЧИТЫВАЕТСЯ ЧТО СЛОВО МОЖЕТ ВСТРЕТИТЬСЯ НЕСКОЛЬКО РАЗ
    public void task7 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        String text = scan.nextLine ();
        for (String word :
                text.split (" ", n)) {
            int count = new HashSet<String> (Arrays.asList (word.split (""))).size () - 1;
            if (word.length () == count)
                System.out.print (word + "\t");
        }
    }

//    Ввести N слов. Помимо обычных слов, во входной последовательности могут встречаться целые числа.
//    Среди них необходимо найти число-палиндром (одинаково читающееся в обоих направлениях).
//    Если таких чисел больше одного, найти второе из них.
    public void task8 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        String text = scan.nextLine ();
        int countPalindromeNum = 0;
        for (String word :
                text.split (" ", n)) {
            Pattern isItNumber = Pattern.compile ("\\d+");
            Matcher mIsItNumber = isItNumber.matcher (word);
            if (mIsItNumber.find ()) {
                //System.out.println(word);
                if (word.equals ( new StringBuilder(word).reverse().toString())){
                    countPalindromeNum++;
                    if (countPalindromeNum == 2)
                        System.out.print (word);
                }
            }
        }
        if (countPalindromeNum == 0)
            System.out.print ("NOT FOUND");
    }

//    Написать программу, которая выводит числа от 1 до N^2 в виде матрицы NxN слева направо и сверху вниз.
    public void task9 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());

        if (n > 0) {
            int counter = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    System.out.print (counter + "\t");
                    counter++;
                }
                System.out.println ();
            }
        }
    }

//    Написать программу, позволяющую корректно находить корни квадратного уравнения.
//    Параметры уравнения должны задаваться с командной строки.
    public void task10 () {
        Scanner scan = new Scanner (System.in);
        int a = scan.nextInt ();
        int b = scan.nextInt ();
        int c = scan.nextInt ();
        int discriminant = b * b - 4 * a * c;
        if (discriminant < 0)
            System.out.print ("No solution");
        else if (discriminant == 0) {
            double x = (double) -b / (2 * a);
            System.out.print ("One solution: " + x);
        } else if (discriminant > 0) {
            double x1 = (-b - Math.sqrt (b * b - 4 * a * c)) / (2 * a);
            double x2 = (-b + Math.sqrt (b * b - 4 * a * c)) / (2 * a);
            System.out.print ("Two solutions: " + x1 + "\t" + x2);
        }
    }

//    Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу.
//    При реализации использовать оператор switch. Осуществить проверку корректности ввода числа.
    public void task11 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        switch (n) {
            case (1):
                System.out.println ("January");
                break;
            case (2):
                System.out.println ("February");
                break;
            case (3):
                System.out.println ("March");
                break;
            case (4):
                System.out.println ("April");
                break;
            case (5):
                System.out.println ("May");
                break;
            case (6):
                System.out.println ("June");
                break;
            case (7):
                System.out.println ("July");
                break;
            case (8):
                System.out.println ("August");
                break;
            case (9):
                System.out.println ("September");
                break;
            case (10):
                System.out.println ("October");
                break;
            case (11):
                System.out.println ("November");
                break;
            case (12):
                System.out.println ("December");
                break;
            default:
                System.out.println ("INCORRECT INPUT DATA");
                break;
        }
    }

//    Упорядочить строки матрицы размерности N в порядке возрастания значений элементов k-го столбца.0
    public void task12 () {
        Scanner scan = new Scanner (System.in);
        final int k = Integer.parseInt (scan.nextLine ());
        int n = Integer.parseInt (scan.nextLine ());
        Integer[][] matrix = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
            }
        }

        {
            List<Integer[]> matrixList = Arrays.asList (matrix);     //let's override sort of our matrix FOR THE KING!
            Collections.sort (matrixList, new Comparator<Integer[]> () {
                public int compare (Integer[] o1, Integer[] o2) {
                    return o1[k] - o2[k];
                }
            });
        }

        System.out.println (n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (matrix[i][j]+"\t");
            }
            System.out.println ();
        }
    }

//    Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
    public void task13 () {
        Scanner scan = new Scanner (System.in);
        int k = Integer.parseInt (scan.nextLine ());
        int n = Integer.parseInt (scan.nextLine ());
        String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            lines[i] = scan.nextLine ();
        }
        if (k > 0)
            moveDown (lines,k,n);
        else if (k < 0)
            moveUp (lines,k,n);
        else if (k == 0)
            printStringArrayTask13 (lines,n);
    }

        private void moveDown(String[] array, int positions, int size) {
        for (int i = 0; i < positions; i++) {
            String temp = array[size - 1];
            for (int j = size - 1; j > 0; j--) {
                array[j] = array[j-1];
            }
            array[0] = temp;
        }

        printStringArrayTask13 (array, size);
    }

        private void moveUp(String[] array, int positions, int size) {
        positions = Math.abs (positions);
        for (int i = size; i > positions; i--) {
            String temp = array[size - 1];
            for (int j = size - 1; j > 0; j--) {
                array[j] = array[j - 1];
            }
            array[0] = temp;
        }

        printStringArrayTask13 (array, size);
    }

        private void printStringArrayTask13 (String[] array, int size){

        System.out.println (size);
        for (String line :
                array) {
            System.out.println (line);
        }
    }

//    Найти наибольшее число строго возрастающих элементов последовательности, идущих подряд.
    public void task14 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int maxLength = 1;
        int nowLength = 1;
        int[] array = new int[n];

        array[0] = scan.nextInt ();
        for (int i = 1; i < n; i++) {
            array[i] = scan.nextInt ();
            if (array[i]>array[i-1]) {
                nowLength++;
            }
            else if (array[i]<=array[i-1]){
                maxLength = nowLength;
                nowLength = 1;
            }
        }
        System.out.println (maxLength);
    }

//    Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
    //если в конце строки нет положительного - не считать эту сумму?
    public void task15 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int[][] matrix = new int[n][n];
        int sum = 0;
        int sumAll = 0;
        boolean firstPositive = false;
        boolean secondPositive = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();

                if ((matrix[i][j]>0) && (firstPositive))
                    secondPositive = true;

                if ((firstPositive) && (!secondPositive)){
                    sum += matrix[i][j];
                }
                if ((matrix[i][j]>0) && (!firstPositive))
                    firstPositive = true;
            }
            if (firstPositive && !secondPositive)
            {
                sum = 0;
            }
            firstPositive = false;
            secondPositive = false;
            sumAll += sum;
        }
        System.out.println (sumAll);
    }

//    Повернуть матрицу на 90 градусов против часовой стрелки.
    public void task16 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int matrix90[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = n-1; j >= 0; j--) {
                matrix90[i][j] = scan.nextInt ();
            }
        }

        System.out.println (n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (matrix90[j][i] + "\t");
            }
            System.out.println ();
        }
    }

//    Вычислить определитель матрицы.
    //а определитель может быть дробным?
    public void task17 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
            }
        }
        System.out.println (((int) CalculateMatrix (matrix)));
    }

        public double CalculateMatrix(double[][] matrix){
        double calcResult=0.0;
        if (matrix.length==1){
            calcResult=matrix[0][0];
        }
        else if (matrix.length == 2){
            calcResult=matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1];
        }
        else{
            int koeff;
            for(int i=0; i<matrix.length; i++){
                if(i%2==1){  //я решил не возводить в степень, а просто поставить условие - это быстрее. Т.к. я раскладываю всегда по первой (читай - "нулевой") строке, то фактически я проверяю на четность значение i+0.
                    koeff=-1;
                }
                else{
                    koeff=1;
                }
                //собственно разложение:
                calcResult += koeff*matrix[0][i]*this.CalculateMatrix(this.GetMinor(matrix,0,i));
            }
        }
        //возвращаем ответ
        return calcResult;
    }

    //функция, к-я возвращает нужный нам минор. На входе - определитель, из к-го надо достать минор и номера строк-столбцов, к-е надо вычеркнуть.
        private double[][] GetMinor(double[][] matrix, int row, int column){
        int minorLength = matrix.length-1;
        double[][] minor = new double[minorLength][minorLength];
        int dI = 0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        int dJ;
        for(int i=0; i<=minorLength; i++){
            dJ=0;
            for(int j=0; j<=minorLength; j++){
                if(i==row){
                    dI=1;
                }
                else{
                    if(j==column){
                        dJ=1;
                    }
                    else{
                        minor[i-dI][j-dJ] = matrix[i][j];
                    }
                }
            }
        }
        return minor;
    }

//    Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.
    //переделать чтобы работало с несколькими максимумами в одной строке/ одном столбце
    public void task18 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int[][] matrix = new int[n][n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
                if (max<matrix[i][j])
                    max = matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == max) {
                    matrix = reorganizeMatrix (matrix,i,j);
                    i = 0;
                    //System.out.println ("length = "+matrix.length);
                    break;
                }
            }
        }

        System.out.println (matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print (matrix[i][j]+"\t");
            }
            System.out.println ();
        }
    }

    //переделанный метод GetMinor
        private int[][] reorganizeMatrix(int[][] matrix, int row, int column){
        int minorLength = matrix.length-1;
        int[][] minor = new int[minorLength][minorLength];
        int dI = 0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        int dJ;
        for(int i=0; i<=minorLength; i++){
            dJ=0;
            for(int j=0; j<=minorLength; j++){
                if(i==row){
                    dI=1;
                }
                else{
                    if(j==column){
                        dJ=1;
                    }
                    else{
                        minor[i-dI][j-dJ] = matrix[i][j];
                    }
                }
            }
        }
        return minor;
    }

//    Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
    public void task19 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>> ();
        int[] rows = new int[n];
        int[] columns = new int[n];

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> line = new ArrayList<Integer> ();
            for (int j = 0; j < n; j++) {
                line.add (scan.nextInt ());
                if (line.get (j) == 0) {
                    columns[j]++;
                    rows[i]++;
                }
            }
            matrix.add (line);
        }

        int counter = 0;
        for (int i = 0; i < matrix.size (); i++) {
            if (rows[counter] == n) {
                matrix.remove (i);
                i--;
            }
            counter++;
        }

        //System.out.println ();
        //System.out.println (matrix.get (matrix.size () - 1).size ());
        //System.out.println ();

        counter = 0;
        for (int i = 0; i < matrix.get(matrix.size()-1).size(); i++) {
            if (columns[counter] == n){
                for (ArrayList<Integer> line :
                        matrix) {
                    line.remove (i);
                }
                i--;
            }
            counter++;
        }

        System.out.println (matrix.size ());
        System.out.println (matrix.get (0).size ());
        for (ArrayList<Integer> line :
                matrix) {
            for (int numb :
                    line) {
                System.out.print (numb+"\t");
            }
            System.out.println ();
        }

    }

//    В матрице найти минимальный элемент и переместить его на место заданного элемента
//    путем перестановки строк и столбцов.
//    Гарантируется, что минимальный элемент в матрице встречается ровно один раз.
    public void task20 () {
        Scanner scan = new Scanner (System.in);
        int x = Integer.parseInt (scan.nextLine ());
        int y = Integer.parseInt (scan.nextLine ());
        int n = Integer.parseInt (scan.nextLine ());
        int[][] matrix = new int[n][n];

        int xNew = 0;
        int yNew = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
                if (matrix[i][j] < matrix[xNew][yNew]){
                    xNew = i;
                    yNew = j;
                }
            }
        }

        //System.out.println ("x = "+xNew+"; y = "+yNew);
        //System.out.println (xyResult);
        //System.out.println ();

        matrix = exchangeRows (matrix, x, xNew);
        matrix = exchangeColumns (matrix, y, yNew);

        System.out.println (n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (matrix[i][j]+"\t");
            }
            System.out.println ();
        }

    }

        private int[][] exchangeRows (int[][] matrix, int row1, int row2){
        for (int i = 0; i < matrix.length; i++) {
            int temp = matrix[row1][i];
            matrix[row1][i] = matrix[row2][i];
            matrix[row2][i] = temp;
        }
        return matrix;
    }

        private int[][] exchangeColumns (int[][] matrix, int col1, int col2){
        for (int i = 0; i < matrix.length; i++) {
            int temp = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
        return matrix;
    }

//    Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю, располагались после всех остальных.
    public void task21 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((matrix[i][j] == 0)&&(j < n-1)){
                    matrix[i][j] = matrix[i][j+1];
                    matrix[i][j+1] = 0;
                }
            }
        }

        System.out.println (n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (matrix[i][j] + "\t");
            }
            System.out.println ();
        }
    }

//    Округлить все элементы матрицы до целого числа.
    //спросить про окугление
    public void task22 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        double[][] matrix = new double[n][n];

        System.out.println (n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Double.parseDouble (scan.next ());
                System.out.print (Math.round (matrix[i][j]) + "\t");
            }
            System.out.println ();
        }
    }

//    Найти количество всех седловых точек матрицы.
//    Матрица А имеет седловую точку (i, j),
//    если А[i, j] является минимальным элементом в i-й строке и максимальным в j-м столбце.
    public void task23 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int[][] matrix = new int[n][n];
        int[] strMin = new int [n];

        for (int num :
                strMin) {
            num = 1000;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
                if (matrix[i][j] < strMin[i])
                    strMin[i] = matrix[i][j];
            }
        }

        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == strMin[i])
                    if (isThisSedlovaya (matrix, strMin[i], j, i))
                        counter++;
            }
        }

        System.out.println (counter);
    }

        private boolean isThisSedlovaya (int[][]matrix, int numb, int column, int row){
        for (int i = 0; i < matrix.length; i++) {
            if ((matrix[i][column] > numb) && (i != row))
                return false;
        }
        return true;
    }

    //    Перестроить матрицу, переставляя в ней строки так, чтобы сумма элементов в строках полученной матрицы возрастала.
    public void task24 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        Integer[][] matrix = new Integer[n][n+1];
        int sum;

        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = scan.nextInt ();
                sum+=matrix[i][j];
            }
            matrix[i][0] = sum;
        }

        List<Integer[]> matrixList = Arrays.asList (matrix);     //let's override sort of our matrix FOR THE KING!
        Collections.sort (matrixList, new Comparator<Integer[]> () {
            public int compare (Integer[] o1, Integer[] o2) {
                return o1[0] - o2[0];
            }
        });

        System.out.println (n);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print (matrix[i][j] + "\t");
            }
            //System.out.print ("sum = " + matrix[i][0] + "\t");
            System.out.println ();
        }
    }

//    Найти число локальных минимумов.
//    Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или угол.
//    Элемент матрицы называется локальным минимумом, если он строго меньше всех своих соседей.
    public void task25 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (localMin (matrix, i, j)) {
                    //System.out.println ("point = " + i + " " + j);
                    count++;
                }
            }
        }
        System.out.println (count);
    }

        private boolean localMin (int[][] matrix, int row, int column){
        int countRowFrom = row - 1;
        int countRowFor = row + 1;
        int countColumnFrom = column - 1;
        int countColumnFor = column + 1;

        // верхний левый угол матрицы
        if ((row == 0)&&(column == 0)){
            countRowFrom = 0;
            countRowFor = row + 1;
            countColumnFrom = 0;
            countColumnFor = column + 1;
        }
        //верхняя строка матрицы (крому угловых)
        else if ((row == 0)&&(column > 0)&&(column < matrix.length-1)){
            countRowFrom = 0;
            countRowFor = row + 1;
            countColumnFrom = column - 1;
            countColumnFor = column + 1;
        }
        //верхний правый угол матрицы
        else if ((row == 0)&&(column == matrix.length-1)){
            countRowFrom = 0;
            countRowFor = row + 1;
            countColumnFrom = column - 1;
            countColumnFor = column;
        }
        //первый столбец (кроме угловых)
        else if ((column == 0)&&(row > 0)&&(row < matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row + 1;
            countColumnFrom = 0;
            countColumnFor = column + 1;
        }
        //последний слобец матрицы (кроме угловых)
        else if ((column == matrix.length-1)&&(row > 0)&&(row < matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row + 1;
            countColumnFrom = column - 1;
            countColumnFor = column;
        }
        //нижний левый угол матрицы
        else if ((column == 0)&&(row == matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row;
            countColumnFrom = 0;
            countColumnFor = column + 1;
        }
        //нижняя линия матрицы (кроме угловых)
        else if ((column > 0)&&(column < matrix.length-1)&&(row == matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row;
            countColumnFrom = column - 1;
            countColumnFor = column + 1;
        }
        //нижний правый угол
        else if ((row == matrix.length-1)&&(column == matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row;
            countColumnFrom = column - 1;
            countColumnFor = column;
        }

        for (int i = countRowFrom; i <= countRowFor; i++) {
            for (int j = countColumnFrom; j <= countColumnFor; j++) {
                if ((i == row)&&(j == column)){

                }
                else if (matrix[i][j] <= matrix[row][column]) {
                    return false;
                }
            }
        }
        return true;
    }

//    Найти наибольший среди локальных максимумов.
//    Соседями элемента матрицы назовем элементы, имеющие с ним общую сторону или угол.
//    Элемент матрицы называется локальным максимумом, если он строго больше всех своих соседей.
    public void task26 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scan.nextInt ();
            }
        }

        int maxCount = 0;
        int maxLocal = -100000;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (localMax (matrix,i,j)){
                    //System.out.println ("point = " + i + " " + j);
                    maxCount++;
                    if (matrix[i][j] > maxLocal)
                        maxLocal = matrix[i][j];
                }
            }
        }

        if (maxCount == 0)
            System.out.println ("NOT FOUND");
        else
            System.out.println (maxLocal);
    }

        private boolean localMax (int[][] matrix, int row, int column){
        int countRowFrom = row - 1;
        int countRowFor = row + 1;
        int countColumnFrom = column - 1;
        int countColumnFor = column + 1;

        // верхний левый угол матрицы
        if ((row == 0)&&(column == 0)){
            countRowFrom = 0;
            countRowFor = row + 1;
            countColumnFrom = 0;
            countColumnFor = column + 1;
        }
        //верхняя строка матрицы (крому угловых)
        else if ((row == 0)&&(column > 0)&&(column < matrix.length-1)){
            countRowFrom = 0;
            countRowFor = row + 1;
            countColumnFrom = column - 1;
            countColumnFor = column + 1;
        }
        //верхний правый угол матрицы
        else if ((row == 0)&&(column == matrix.length-1)){
            countRowFrom = 0;
            countRowFor = row + 1;
            countColumnFrom = column - 1;
            countColumnFor = column;
        }
        //первый столбец (кроме угловых)
        else if ((column == 0)&&(row > 0)&&(row < matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row + 1;
            countColumnFrom = 0;
            countColumnFor = column + 1;
        }
        //последний слобец матрицы (кроме угловых)
        else if ((column == matrix.length-1)&&(row > 0)&&(row < matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row + 1;
            countColumnFrom = column - 1;
            countColumnFor = column;
        }
        //нижний левый угол матрицы
        else if ((column == 0)&&(row == matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row;
            countColumnFrom = 0;
            countColumnFor = column + 1;
        }
        //нижняя линия матрицы (кроме угловых)
        else if ((column > 0)&&(column < matrix.length-1)&&(row == matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row;
            countColumnFrom = column - 1;
            countColumnFor = column + 1;
        }
        //нижний правый угол
        else if ((row == matrix.length-1)&&(column == matrix.length-1)){
            countRowFrom = row - 1;
            countRowFor = row;
            countColumnFrom = column - 1;
            countColumnFor = column;
        }

        for (int i = countRowFrom; i <= countRowFor; i++) {
            for (int j = countColumnFrom; j <= countColumnFor; j++) {
                if ((i == row)&&(j == column)){

                }
                else if (matrix[i][j] >= matrix[row][column]) {
                    return false;
                }
            }
        }
        return true;
    }


//    Перестроить заданную матрицу, переставляя в ней столбцы так, чтобы значения их характеристик убывали.
//    Характеристикой столбца прямоугольной матрицы называется сумма модулей его элементов.
//    Если значения характеристики совпадают - столбцы должны следовать в том же порядке, что и в исходной матрице.
    public void task27 () {
        Scanner scan = new Scanner (System.in);
        int n = Integer.parseInt (scan.nextLine ());
        Integer[][] matrix = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[j][i] = scan.nextInt ();
            }
        }

        List<Integer[]> matrixList = Arrays.asList (matrix);     //let's override sort of our matrix FOR THE KING!
        Collections.sort (matrixList, new Comparator<Integer[]> () {
            public int compare (Integer[] o1, Integer[] o2) {
                int sum1 = 0;
                int sum2 = 0;
                for (int numb:
                        o1) {
                    sum1 += Math.abs (numb);
                }
                for (int numb :
                        o2) {
                    sum2 += Math.abs (numb);
                }
                return sum2 - sum1;
            }
        });

        System.out.println (n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print (matrix[j][i] + " ");
            }
            System.out.println ();
        }
    }

}
