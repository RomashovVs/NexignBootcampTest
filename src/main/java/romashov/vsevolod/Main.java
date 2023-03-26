package romashov.vsevolod;

import utils.RecordComparator;
import utils.UtilsDate;
import utils.UtilsWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args){
        try(BufferedReader br = new BufferedReader(new FileReader("cdr.txt"))) {
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

            RecordComparator recordComparator = new RecordComparator();

            for (LinkedList<Record> recordList: dataRecord.values()) {
                recordList.sort(recordComparator);
                try (FileWriter writer = new FileWriter("reports/report_".concat(recordList.get(0).getNumber()))) {

                    createReportForSixTariff(recordList, writer);

                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateReport(LinkedList<Record> recordList) {

//        switch (recordList.get(0).getTypeTariff()) {
//            case ("06"): {
//                createReportForSixTariff(recordList);
//                break;
//            }
//            case ("03"): {
//                createReportForThirdTariff(recordList);
//                break;
//            }
//            case ("11"): {
//                createReportForElevenTariff(recordList);
//                break;
//            }
//        }
    }

    private static void createReportForSixTariff(LinkedList<Record> recordList, FileWriter writer) throws IOException {
        Double totalSum = 0.0;
        Double totalMinutesPepPeriod = 0.0;
        Double maxMinutes = 300.0;
        Double cost = 0.0;

        UtilsWriter.printHeadReport(recordList.getFirst(), writer);

        for (Record record: recordList) {
            long minutes = record.getStartRecordDate().toInstant().until(
                    record.getEndRecordDate().toInstant(), ChronoUnit.MINUTES);

            totalMinutesPepPeriod += minutes;
            if (totalMinutesPepPeriod > maxMinutes) {
                totalSum += totalMinutesPepPeriod - maxMinutes;
                maxMinutes = totalMinutesPepPeriod;
                cost = totalMinutesPepPeriod - maxMinutes;
            }

            /*String startTimeStr = UtilsDate.convertDateToString(record.getStartRecordDate());
            String endTimeStr = UtilsDate.convertDateToString(record.getEndRecordDate());

            String formatterDuration = UtilsDate.duration(record.getStartRecordDate(), record.getEndRecordDate());

            writer.write(String.format("%-12s%-22s%-22s%-11s| %5.2f |",
                    "|     ".concat(record.getTypeRecord()),
                    "| ".concat(startTimeStr),
                    "| ".concat(endTimeStr),
                    "| ".concat(formatterDuration),
                    cost));
            writer.write("\n");*/
            UtilsWriter.printBodyLineReport(record, cost, writer);

        }
        writer.write("----------------------------------------------------------------------------\n");
        writer.write(String.format("|                                           Total Cost: |     %5.2f rubles |\n",
                totalSum));
        writer.write("----------------------------------------------------------------------------\n");
    }
}