package romashov.vsevolod.utils;

import romashov.vsevolod.Record;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Reader {
    public static HashMap<String, LinkedList<Record>> readFile(BufferedReader br) throws IOException, ParseException {
        String readLineRecord = br.readLine();
        HashMap<String, LinkedList<Record>> dataRecord = new HashMap<>();
        while (readLineRecord != null) {

            Record newRecord = Record.createNewRecord(readLineRecord);

            if (!dataRecord.containsKey(newRecord.getNumber())){
                dataRecord.put(newRecord.getNumber(), new LinkedList<>());
            }

            LinkedList<Record> recordList = dataRecord.get(newRecord.getNumber());
            recordList.add(newRecord);

            readLineRecord = br.readLine();
        }

        return dataRecord;
    }
}
