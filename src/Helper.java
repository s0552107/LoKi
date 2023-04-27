import org.lwjgl.Sys;

import java.util.Collections;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Helper {

    public static void main (String[] args)
    {

        int[] fieldP0 = generateFieldP0();

        int[] fieldP1 = generateFieldP1();

        int[] fieldP2 = generateFieldP2();

        int [][] neighbors = generateNeighbors();

        Integer[] steine;
        steine = new Integer[] {0,1,2,3};



        for (int i = 0; i<4; i++){
            System.out.println(Arrays.asList(steine));
            List<Integer> list = getNeighbors(steine,neighbors);
            System.out.println(list);
            Random random = new Random();
            Integer ran = list.get(random.nextInt(list.size()));
            System.out.println("Stein: "+ steine[0]+" zieht nach: "+ran);
            steine[0] = ran;
            Arrays.sort(steine);
            System.out.println("____");

        }




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

    public static int findIndex(int[] arr, int target){
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                index = i; // set the index variable to the current index if the integer is found
                break; // exit the loop once the integer is found
            }
        }
        return index;
    }

    public static int[] generateFieldP0()
    {
        int[] arr = IntStream.range(0, 36).toArray();  //initializing array
        return arr;
    }

    public static int[] generateFieldP1()
    {
        int[] arr={25,27,26,16,29,28,18,17,9,31,30,20,19,11,10,4,33,32,22,21,13,12,6,5,1,35,34,24,23,15,14,8,7,3,2,0};  //initializing array
        return arr;
    }

    public static int[] generateFieldP2()
    {
        int[] arr={35,24,34,33,15,23,22,32,31,8,14,13,21,20,30,29,3,7,6,12,11,19,18,28,27,0,2,1,5,4,10,9,17,16,26,25};  //initializing array
        return arr;
    }

    public static int[][] generateNeighbors()
    {
        int[][] arr = {{2},{2,5},{0,1,3},{2,7},{5,10},{1,4,6},{5,7,12},{3,6,8},{7,14},{10,17},{4,9,11},{10,12,19},{6,11,13},{12,14,21},{8,13,15},{14,23},{17,26},{9,16,18},{17,19,28},{11,18,20},{19,21,30},{13,20,22},{21,23,32},{15,22,24},{23,34},{26},{16,25,27},{26,28},{18,27,29},{28,30},{20,29,31},{30,33},{22,31,33},{32,34},{24,33,35},{34}};  //initializing array
        return arr;
    }

}
