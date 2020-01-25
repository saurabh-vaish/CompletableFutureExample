package test;

import java.util.concurrent.*;

/***
 * @author saurabh vaish
 * @version 1.0
 * @since 25/01/20
 ***/

class Demo{

    private int count = 10;
    private int sleep = 100;

    public void m1()  {
        for (int i = 0; i < count; i++) {
            System.out.println("m1 "+i + " " + Thread.currentThread());
            sleep(sleep);
        }
    }

    public void m2() {
        for (int i = 0; i < count; i++) {
            System.out.println("m2 "+i + " " + Thread.currentThread());
            sleep(sleep);
        }
    }
    public int m3()  {
        int i=0;
        for ( i = 0; i < count; i++) {
            System.out.println("m3 "+i + " " + Thread.currentThread());
            sleep(sleep);
        }
        return i;
    }

    public int m4() {
        int i=0;
        for (i = 0; i < count; i++) {
            System.out.println("m4 "+i + " " + Thread.currentThread());
            sleep(sleep);
        }
        return i;
    }

    private void sleep(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            // never mind
        }
    }

}



public class CompletableFutureTest {

    public  void run() throws ExecutionException, InterruptedException {
        Demo demo = new Demo();

        // runAsync() === used when method is not returning anything
        // supplyAsync() === used when method is returning something

        // get() == used to get the results , we need to call this method on CompletableFutures. It is like subscribe().
//        System.out.println("with thread pool stared ---> "+System.currentTimeMillis());

        // in this case one thread pool will be created with 4 threads and from this pool threads will be shared to everyone

//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//
//        CompletableFuture<Void> voidCompletableFuture1  = CompletableFuture.runAsync(() -> demo.m1(),executorService );
//        CompletableFuture<Void> voidCompletableFuture2 =  CompletableFuture.runAsync(() -> demo.m2(),executorService);
//
//        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> demo.m3(),executorService);
//        CompletableFuture<Integer> integerCompletableFuture2 =  CompletableFuture.supplyAsync(() -> demo.m4(),executorService);
//
//        voidCompletableFuture1.get();
//        voidCompletableFuture2.get();
//
//        integerCompletableFuture1.get();
//        integerCompletableFuture2.get();
//
//        executorService.shutdown();
//
//        System.out.println("with thread pool ended ---> "+System.currentTimeMillis());    // approx time taken in this case is 10060 ms

//        System.out.println("with thread pool independently stared ---> "+System.currentTimeMillis());
//
        // In this case 4 thread pool is being created and their single thread will execute task

//        CompletableFuture<Void> voidCompletableFuture1  = CompletableFuture.runAsync(() -> demo.m1(),Executors.newSingleThreadExecutor() );
//        CompletableFuture<Void> voidCompletableFuture2 =  CompletableFuture.runAsync(() -> demo.m2(),Executors.newSingleThreadExecutor());
//
//        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> demo.m3(),Executors.newSingleThreadExecutor());
//        CompletableFuture<Integer> integerCompletableFuture2 =  CompletableFuture.supplyAsync(() -> demo.m4(),Executors.newSingleThreadExecutor());
//
//        voidCompletableFuture1.get();
//        voidCompletableFuture2.get();
//
//        integerCompletableFuture1.get();
//        integerCompletableFuture2.get();
//
//        System.out.println("with thread pool ended ---> "+System.currentTimeMillis());    // approx time taken in this case is 10060 ms

//        System.out.println("without thread pool stared ---> "+System.currentTimeMillis());
//
        // In this case one common pool will gets created and from this pool 4 worker threads will gets executed

//        CompletableFuture<Void> voidCompletableFuture1  = CompletableFuture.runAsync(() -> demo.m1());
//        CompletableFuture<Void> voidCompletableFuture2 =  CompletableFuture.runAsync(() -> demo.m2());
//
//        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> demo.m3());
//        CompletableFuture<Integer> integerCompletableFuture2 =  CompletableFuture.supplyAsync(() -> demo.m4());
//
//
//        voidCompletableFuture1.get();
//        voidCompletableFuture2.get();
//
//        integerCompletableFuture1.get();
//        integerCompletableFuture2.get();
//
//        System.out.println("without thread pool ended ---> "+System.currentTimeMillis());   // approx time taken in this case is 10060 ms


//        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()-> {return "saurabh" ;} );
//
//        CompletableFuture<String> total = future1.thenCompose(name->d1(name));     // this method is used to combine two dependent CompletableFutures
//
//        System.out.println(total.get());

//        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()-> {return "saurabh" ;} );
//
//        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()-> {return "vaish" ;} );
//
//        CompletableFuture<String> total = future1.thenCombine(future2,(future1result,future2result)-> future1result+" "+future2result );     // this method is used to combine two
//        // independent CompletableFutures
//
//        System.out.println(total.get());

        // handling exception

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()-> {return "saurabh" ;} ).exceptionally(e->e.getMessage());

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()-> {return "vaish" ;} ).handle((res,ex)-> {                // will execute wheather exception came or not 
            if(ex!=null)
            {
                System.out.println(ex.getMessage());
            }
            return res;
        });


    }

    private static CompletableFuture<String> d1(String s){
        return CompletableFuture.supplyAsync(() -> {
            return s+" "+"vaish";
        });
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFutureTest completableFutureTest = new CompletableFutureTest();
        completableFutureTest.run();

    }
}
