/* 4.  Создать приложение с 2 параметрами. 1 параметр задает количество потоков , 2-ой параметр задает целое  положительное число.
        Каждый поток по очереди уменьшает число на 1 и выводит  уменьшенное число и свое имя . Работа потоков заканчивается, когда число будет равно 0.
        Использовать ограничения из задания 3. */

import java.util.concurrent.*;

class CallableExample implements Callable {
    int num;
    public  CallableExample(int num){
        this.num=num;
    }
    public Object call() throws Exception {
        num--;
        return num;
    }
}

public class lab_4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int num = Integer.parseInt(args[1]);

        Callable<String> task = () -> Thread.currentThread().getName();
        Callable callable = new CallableExample(num+1);

        ExecutorService service = Executors.newFixedThreadPool(Integer.parseInt(args[0]));
        for (int i = num; i>=0; i--) {
            Future result = service.submit(task);
            Future result1 = service.submit(callable);
            System.out.print("Номер потока № " + result.get() + " Number = ");
            System.out.println(result1.get());
        }
        service.shutdown();
    }
}
