package task3;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Create create = new Create(2.0, 0.0, "Uniform");
        Process process = new Process(0.6, 0.0, "Uniform", 1, 5);
        Model model = new Model(process, create, 10000.0);
        model.simulation();
    }
}
