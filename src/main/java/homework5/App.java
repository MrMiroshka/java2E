package homework5;

/**
 * Домашнее задание №1 курс Java 2
 *
 * @author Miroshnichenko Igor
 * @version 5.1
 * created on 2022-04-10
 */
public class App {
    public static void main(String[] args) {
        System.out.println("***Многопоточность***");
        System.out.println("Количество логических процессоров на пк - "+Runtime.getRuntime().availableProcessors());

        try {
            MathHW5 zadanie = new MathHW5.Builder().size(10000000).build();
            zadanie.singleTthreaded();
            zadanie.doubleThreadForMetodichka();



            //без склейки массива (доп.)
            // в два потока
            zadanie.doubleThread();
            //в два потока (значения по умолчанию - подмассив равено половине массива, соответственно будет всего два потока)
            zadanie.multiTthreaded();

            //дополнительно, решение в несколько потоков - больше чем 2.
            //можем указать максимальное количество создоваемых потоков (потоки которые уже запущены не учитываются)
            //можем указать длинну подмассива на которые разбивается наш основной массив
            MathHW5 multiThreads = new MathHW5.Builder().size(10000000).sizeSubarray(1000).build();
            multiThreads.multiTthreaded();

            //дополнительно, решение в несколько потоков - длинна подмассивов - 10000 и количество потоков не более = количество логических процессоров -1.
            (new MathHW5.Builder().size(10000000).sizeSubarray(10000).build()).multiTthreaded();
            //дополнительно, решение в несколько потоков - длинна подмассивов - 100000 и количество потоков не более 25.
            (new MathHW5.Builder().size(10000000).sizeSubarray(100000).threads(25).build()).multiTthreaded();

        }
        catch (ArrayIndexOutOfBoundsException exp){
            exp.printStackTrace();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }


    }


}
