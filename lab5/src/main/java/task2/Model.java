package task2;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Model implements Callable<Result>{

    public BlockingQueue<Task> processQueue;
    private double tCurrent;
    public double timeModeling;
    private Process process;
    private Create create;
    private ReentrantLock lock = new ReentrantLock();

    public Model(Process process, Create create, double timeModeling){
        this.process = process;
        this.create = create;
        this.timeModeling = timeModeling;
        this.processQueue = new ArrayBlockingQueue<>(process.getQueueCapacity());
    }

    public void incrementTCurrent (double delay){
        lock.lock();
        try {
            tCurrent+=delay;
        }finally {
            lock.unlock();
        }
    }
    public synchronized double getTCurrent() {
        return tCurrent;
    }

    @Override
    public Result call() {
        Result result = new Result(this);
        process.setModel(this);
        create.setModel(this);
        process.setStatistics(result);
        create.setStatistics(result);
        ArrayList<Channel> channels = process.channels();
        ExecutorService channelPool = Executors.newFixedThreadPool(channels.size());
        Thread creator = new Thread(create);
        creator.start();
        for (Channel channel: channels){
            channelPool.execute(channel);
        }
        channelPool.shutdown();

        while (!channelPool.isTerminated()){

        }
        //result.printStatistics();
        return result;
    }
}
