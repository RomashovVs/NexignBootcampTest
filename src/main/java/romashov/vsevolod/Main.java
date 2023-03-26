package romashov.vsevolod;

import utils.Generator;
import utils.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args){
        try(BufferedReader br = new BufferedReader(new FileReader("cdr.txt"))) {

            HashMap<String, LinkedList<Record>> dataRecord = Reader.readFile(br);

            Generator.generateReport(dataRecord);

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

}