import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import lenz.htw.loki.Move;
import lenz.htw.loki.net.NetworkClient;

public class TestClient {

    public static void main(String[] args) throws IOException {
        // Vorbereitung ohne Server
        // Startbrettkonfiguration erstellen

        int [][] neighbors = generateNeighbors();
        Integer[] steine;

        NetworkClient client = new NetworkClient("127.0.0.1", "Team Cap", ImageIO.read(new File("./img/Cap.png")));

        // in diesem Moment läuft das Spiel

        client.getMyPlayerNumber();
//        client.getExpectedNetworkLatencyInMilliseconds();
//        client.getTimeLimitInSeconds();
        if (client.getMyPlayerNumber() == 0){
            steine = new Integer[]{0,1,2,3};
        }
        else if (client.getMyPlayerNumber() == 1){
            steine = new Integer[]{25,26,16,27};
        }
        else{
            steine = new Integer[]{35,34,33,24};
        }


        Move move;

        while (true) {
            while ((move = client.receiveMove()) != null) {
                // verarbeite Zug
                // Brettkonfiguration aktualisieren
            }
            // berechne genialen eigenen Zug
            move = new Move(steine[0],genialerZug(steine,neighbors),0);
            steine[0] = genialerZug(steine,neighbors);
            Arrays.sort(steine);
            client.sendMove(move);
        }


    }

    public static int genialerZug(Integer[] steine, int[][] neighbors){
        List<Integer> list = getNeighbors(steine,neighbors);
        Random random = new Random();
        Integer ran = list.get(random.nextInt(list.size()));
        return ran;
    }

    public static List<Integer> getNeighbors(Integer[] steine, int[][] neighbors)
    {
        List<Integer> nei = new ArrayList<>();
        for (int a = 0; a< steine.length; a++){
            for(int i = 0; i<neighbors[steine[a]].length; i++){
                nei.add(neighbors[steine[a]][i]);
            }
        }
        Collections.sort(nei);
        nei = nei.stream().distinct().collect(Collectors.toList());
        for (Integer s : getOwnNeighbors(steine[0],neighbors)) {
            nei.remove(s);
        }
        for (int i = 0; i< steine.length;i++){
            if (nei.contains(steine[i])){
                nei.remove(steine[i]);
            }
        }
        return nei;
    }

    public static List<Integer> getOwnNeighbors(Integer stein, int[][] neighbors)
    {
        List<Integer> nei = new ArrayList<>();
        for(int i = 0; i<neighbors[stein].length; i++){
            nei.add(neighbors[stein][i]);
        }
        return nei;
    }

    public static int[][] generateNeighbors()
    {
        int[][] arr = {{2},{2,5},{0,1,3},{2,7},{5,10},{1,4,6},{5,7,12},{3,6,8},{7,14},{10,17},{4,9,11},{10,12,19},{6,11,13},{12,14,21},{8,13,15},{14,23},{17,26},{9,16,18},{17,19,28},{11,18,20},{19,21,30},{13,20,22},{21,23,32},{15,22,24},{23,34},{26},{16,25,27},{26,28},{18,27,29},{28,30},{20,29,31},{30,33},{22,31,33},{32,34},{24,33,35},{34}};  //initializing array
        return arr;
    }


}