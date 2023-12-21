import java.io.*;
import java.util.*;


public class Toy {
    private int id;
    private String name;
    private double frequency;

    public Toy(int id, String name, Object frequency) {
        this.id = id;
        this.name = name;
        this.frequency = (double) frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getFrequency() {
        return frequency;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\Юра\\IdeaProjects\\Toy_houm\\src\\toys.txt"));
        List ids = new ArrayList<>();
        List names = new ArrayList<>();
        List frequencies = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            ids.add(Integer.parseInt(tokens[0]));
            names.add(tokens[1]);
            frequencies.add(Double.parseDouble(tokens[2]));
        }
        PriorityQueue toysQueue = new PriorityQueue<>(frequencies.size(),
                Comparator.comparingDouble(Toy::getFrequency));
        for (int i = 0; i < frequencies.size(); i++){
            Toy toy = new Toy((Integer) ids.get(i), (String) names.get(i), frequencies.get(i));
            toysQueue.add(toy);
        }
        Queue queue = new LinkedList();
        queue.addAll(toysQueue);
        writeToFile("result.txt", queue);
    }

    public static void writeToFile(String filename, Queue queue) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < 10; i++) {
            Toy toy = (Toy) queue.poll();
            if (toy != null) {
                bw.write(toy.getId() + ", " + toy.getName() + ", " + toy.getFrequency() + "\n");
            }

        }
        bw.close();

    }

}