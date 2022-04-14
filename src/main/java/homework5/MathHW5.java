package homework5;

public class MathHW5 {
    private final int SIZE;
    private final int HALF;
    private final float[] arr;
    private final int threads;
    //сколько раз размер подмассива входит в размер массива
    private final int indexSizeSubarray;
    private final int sizeSubarray;
    private boolean strartThreads;

    private int threadsOnline;
    private int maxThreadsOnline;


    private synchronized void inc() {
        threadsOnline++;
        if (threadsOnline > maxThreadsOnline) {
            maxThreadsOnline = threadsOnline;
        }
    }

    private synchronized void dec() {
        threadsOnline--;
    }


    private MathHW5(Builder builder) {
        this.SIZE = builder.SIZE;
        this.HALF = builder.SIZE / 2;
        this.threadsOnline = 0;
        this.maxThreadsOnline = 0;
        //Если 0 или не <0  - то бишь не указано, то берем значение по умолчанию - количество физических ядер умножить на два потока иминус 1 поток.

        arr = new float[SIZE];

        if (builder.sizeSubArray > 0) {
            this.sizeSubarray = builder.sizeSubArray;
            this.indexSizeSubarray = builder.indexSizeSubArray;
        } else {
            builder.indexSizeSubArray = 2;
            while (SIZE % builder.indexSizeSubArray != 0) {
                builder.indexSizeSubArray++;
            }
            this.sizeSubarray = SIZE / builder.indexSizeSubArray;
            this.indexSizeSubarray = SIZE / builder.indexSizeSubArray;
        }

        if (builder.threads > 0) {
            this.threads = builder.threads;
        } else {
            this.threads = Runtime.getRuntime().availableProcessors() - 1;
        }
        resetArrayValues();
    }

    /**
     * Заполняет массив значениями по умолчанию = 1
     */
    private void resetArrayValues() {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1f;
        }
    }

    /**
     * Проводим математические вычисления
     *
     * @param head - начало массива
     * @param tail - конецмассива
     */
    private void mathMagic(int head, int tail, float[] array) throws ArrayIndexOutOfBoundsException {
        if (SIZE >= head && head >= 0 && tail >= 0 && tail <= SIZE) {
            for (int i = head; i < tail; i++) {
                array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Не верно заданы крайние значения массива при заполнении значениями по умолчанию");
        }
    }

    public void singleTthreaded() {
        maxThreadsOnline = 1;
        threadsOnline = 1;
        try {
            long start = System.currentTimeMillis();
            mathMagic(0, SIZE, arr);
            print("Расчет в одном (главном) потоке (singleTthreaded):", System.currentTimeMillis() - start, 1, maxThreadsOnline);
            resetArrayValues();
        } catch (ArrayIndexOutOfBoundsException exp) {
            exp.printStackTrace();
        }
    }

    public void doubleThread() {
        strartThreads = false;
        threadsOnline = 0;
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            mathMagic(0, HALF, arr);
        });
        Thread thread2 = new Thread(() -> {
            mathMagic(HALF, SIZE, arr);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("Расчет в двух потоках (без склейки массивов):", System.currentTimeMillis() - start, 2, 2);
        resetArrayValues();

    }

    public void multiTthreaded() throws InterruptedException {
        strartThreads = false;
        maxThreadsOnline = 0;
        threadsOnline = 0;
        //узнаем сколько потоков у нас в начале
        int initThread = Thread.activeCount();

        int tail = 0, head = 0;
        long start = System.currentTimeMillis();
        while (tail < SIZE) {
            if (Thread.activeCount() < threads + initThread) {
                head = tail;
                tail = head + sizeSubarray;
                int finalHead = head;
                int finalTail = tail;
                inc();
                new Thread(() -> {
                    strartThreads = true;
                    mathMagic(finalHead, finalTail, arr);
                    dec();
                }).start();
            }
        }

        while (Thread.activeCount() > initThread || strartThreads == false) {
        }

        print("Расчет в многопоточном режиме:", System.currentTimeMillis() - start, threads, maxThreadsOnline);
        System.out.println("На начало многопоточного метода у нас потоков - " + initThread);
        System.out.println("На конец многопоточного метода у нас потоков - " + Thread.activeCount());
        resetArrayValues();
    }

    private void print(String nameMetod, long time, int countThreads, int countThreadsOnline) {
        System.out.println();
        System.out.println("************************************");
        System.out.println(nameMetod);
        System.out.println("Количество потоков до  " + countThreads);
        System.out.println("Подмассивы размером = " + sizeSubarray);
        System.out.println("Одновременно работало - " + countThreadsOnline + " нити(ей)!");
        System.out.println("Время в милисекундах - " + time);
    }

    public void doubleThreadForMetodichka() {
        strartThreads = false;
        threadsOnline = 0;
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        long start = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

        Thread thread1 = new Thread(() -> {
            mathMagic(0, HALF, a1);
        });
        Thread thread2 = new Thread(() -> {
            mathMagic(0, HALF, a2);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);
        print("Расчет в двух потоках по заданию из методички (с копированием массивов):", System.currentTimeMillis() - start, 2, 2);
        resetArrayValues();

    }

    public static class Builder {
        private int SIZE;
        private int threads;
        private int sizeSubArray;
        private int indexSizeSubArray;

        /**
         * Размер подмассива, на которые разбивается основной массив прииспользовании многопоточности
         * P.S.: по умолчанию значение равно значению используемых потоков
         *
         * @param sizeSubArray
         * @return размер подмассива
         * @throws Exception
         */
        public Builder sizeSubarray(int sizeSubArray) throws Exception {
            if (sizeSubArray <= 0 || SIZE % sizeSubArray != 0) {
                throw new Exception("При формировании запроса на размер подмассивов для вычисления в одном потоке!");
            } else {
                this.indexSizeSubArray = SIZE / sizeSubArray;
                this.sizeSubArray = sizeSubArray;
            }
            return this;
        }

        public Builder size(int size) throws ArrayIndexOutOfBoundsException {
            if (size < 0) {
                throw new ArrayIndexOutOfBoundsException("При формировании массива, был задан неверный размер массива!");
            }
            this.SIZE = size;
            return this;
        }

        public Builder threads(int countProcessors) throws Exception {
            if (countProcessors <= 0) {
                throw new Exception("При формировании запроса на вычисление, было задано неверное значения количества процессоров!");
            } else {
                this.threads = countProcessors;
            }
            return this;
        }


        public MathHW5 build() {
            return new MathHW5(this);
        }
    }
}
