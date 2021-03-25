package task3;

public class GetResult implements Runnable{
    private Results result;

    public GetResult(Results result){
        this.result = result;
    }

    @Override
    public void run() {
        if (result.getStatisticThreadTCurrent() != result.model.getTCurrent()){
            result.setStatisticThreadTCurrent(result.model.getTCurrent());
            System.out.println("\nTime: " + result.model.getTCurrent());
            System.out.println("Average queue: " + result.getAvgQueue());
            System.out.println("Failure probability: " + result.getFailureProbability());
        }
        else return;
    }
}
