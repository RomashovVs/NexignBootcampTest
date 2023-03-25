package romashov.vsevolod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args){
        try(BufferedReader br = new BufferedReader(new FileReader("cdr.txt"))) {
            String readLineRecord = br.readLine();
            HashMap<String, LinkedList<Record>> dataRecord = new HashMap<>();
            while (readLineRecord != null) {
                String[] arrayWordLine = readLineRecord.split(",");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                Date parseDate = dateFormat.parse(arrayWordLine[2]);
                Timestamp startRecordDate = new java.sql.Timestamp(parseDate.getTime());

                parseDate = dateFormat.parse(arrayWordLine[3]);
                Timestamp endRecordDate = new java.sql.Timestamp(parseDate.getTime());

                Record newRecord = new Record(arrayWordLine[0], arrayWordLine[1],
                        startRecordDate, endRecordDate, arrayWordLine[4]);

                if (!dataRecord.containsKey(newRecord.getNumber())){
                    dataRecord.put(newRecord.getNumber(), new LinkedList<>());
                }

                LinkedList<Record> recordList = dataRecord.get(newRecord.getNumber());
                recordList.add(newRecord);

                readLineRecord = br.readLine();
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}