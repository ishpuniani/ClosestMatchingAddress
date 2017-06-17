import java.io.*;
import java.util.*;

/**
 * Created by Dhruv on 17/06/17.
 */
public class Main {
    /*
        1. Generate Dictionary
        2. Get input
        3. Match
     */
    //private static Set<String> masterList = new HashSet<String>();
    private static Trie addressTrie = new Trie();
    private static List<String> inputAddress;

    private static final String MASTER_PATH = "./Input/MasterList.txt";
    private static final String INPUT_PATH = "./Input/input.txt";
    private static final String OUTPUT_PATH = "./output.txt";

    public static final String DELIM = ",";

    public static void main(String[] args) {
        getMasterTrie();
        getInputAddress();
        List<String> output = matchAndReturn();
        writeToFile(output, OUTPUT_PATH);
    }

    /*
        Read the master file and get address trie
     */
    private static void getMasterTrie() {
        List<String> masterList = getInputFromFile(MASTER_PATH);
        for(String master: masterList) {
            addressTrie.insert(StringUtils.reverse(master,DELIM));
        }
        System.out.println("Address trie generated");
    }

    /*
        Read input file and get inputs
     */
    private static void getInputAddress() {
        inputAddress = getInputFromFile(INPUT_PATH);
        System.out.println("input address: " + inputAddress);
    }

    /*
        Iterate over the input list and return
        the closest matching address from master list
     */
    private static List<String> matchAndReturn() {
        List<String> matched = new ArrayList<String>();
        for(String input : inputAddress) {
            matched.add(addressTrie.getFullLine(StringUtils.reverse(input,DELIM)));
        }
        System.out.println("Matched: " + matched);
        return matched;
    }

    private static List <String> getInputFromFile(String path) {
        List <String> input = new ArrayList<String>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(path));
            while ((sCurrentLine = br.readLine()) != null) {
                String address = sCurrentLine;
                input.add(address);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Master File Not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading master file");
            e.printStackTrace();
        } finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error while closing BufferedReader");
                    e.printStackTrace();
                }
            }
        }
        return input;
    }

    /*
        Write Output to file
     */
    private static void writeToFile(List<String> output, String path) {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            //Path to o/p file
            fileWriter = new FileWriter(path);
            bufferedWriter = new BufferedWriter(fileWriter);
            for(String str : output) {
                bufferedWriter.write(str + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
            throw new RuntimeException(e);
        } finally {
            try {
                if(bufferedWriter != null)
                    bufferedWriter.close();
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                System.out.println("Exception closing writers");
                throw new RuntimeException(e);
            }
        }
    }

}
