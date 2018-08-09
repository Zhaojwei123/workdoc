package thread;

import java.util.concurrent.*;

/**
 * Created by ZhaoJiwei on 2018/8/9.
 */
public class ThreadStdy {

    public void createExecutorTask(){//大致分为schedule和thread两类
        //1
        Executor executor = Executors.newSingleThreadExecutor();
        //2
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //3
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.schedule() schedule()
//        scheduledExecutorService.scheduleAtFixedRate()
//        scheduledExecutorService.scheduleWithFixedDelay()
        //4  当一个任务被提交时，如果执行中的线程数量小于 corePoolSize，一个新的线程被创建。如果运行的线程数量大于 corePoolSize，但小于 maximumPoolSize，并且任务队列已满时，依然会创建新的线程。如果多于 corePoolSize 的线程空闲时间超过 keepAliveTime，它们会被终止。
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        //5
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
    }

    /*
    ork/join 框架基于“工作窃取算法”。简而言之，意思就是执行完任务的线程可以从其他运行中的线程“窃取”工作。
    ForkJoinPool 适用于任务创建子任务的情况，或者外部客户端创建大量小任务到线程池。
    这种线程池的工作流程如下：
    创建 ForkJoinTask 子类
    根据某种条件将任务切分成子任务
    调用执行任务
    将任务结果合并
    实例化对象并添加到池中
    */
    public void createForkJoinTask(){
        
    }

    public void futureTask(){//非阻塞的处理模式
        Future<Integer> future = Executors.newSingleThreadExecutor().submit(new Callable<Integer>() {

            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 1;
            }


        });

        try {
            while (!future.isDone()) {
                int ret = future.get();
            }
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {


    }
}
