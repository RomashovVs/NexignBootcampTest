package romashov.vsevolod.utils;

import romashov.vsevolod.Record;

import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;

public class Generator {
    public static void generateReport(HashMap<String, LinkedList<Record>> dataRecord) {
        RecordComparator recordComparator = new RecordComparator();

        for (LinkedList<Record> recordList: dataRecord.values()) {
            recordList.sort(recordComparator);
            try (FileWriter writer = new FileWriter("reports/report_".concat(recordList.get(0).getNumber()))) {

                checkGeneratorReportTariff(recordList, writer);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void checkGeneratorReportTariff(LinkedList<Record> recordList, FileWriter writer) throws IOException {
        UtilsWriter.printHeadReport(recordList.getFirst(), writer);

        switch (recordList.get(0).getTypeTariff()) {
            case ("06"): {
                generatorReportForSixTariff(recordList, writer);
                break;
            }
            case ("03"): {
                generatorReportForThirdTariff(recordList, writer);
                break;
            }
            case ("11"): {
                generatorReportForElevenTariff(recordList, writer);
                break;
            }
        }
    }

    private static void generatorReportForSixTariff(LinkedList<Record> recordList, FileWriter writer) throws IOException {
        double totalSum = 0.0;
        double totalMinutesPepPeriod = 0.0;
        double maxMinutes = 300.0;
        double cost;

        for (Record record: recordList) {
            cost = 0.0;
            long minutes = record.getStartRecordDate().toInstant().until(
                    record.getEndRecordDate().toInstant(), ChronoUnit.MINUTES);

            totalMinutesPepPeriod += minutes;
            if (totalMinutesPepPeriod > maxMinutes) {
                totalSum += totalMinutesPepPeriod - maxMinutes;
                maxMinutes = totalMinutesPepPeriod;
                cost = totalMinutesPepPeriod - maxMinutes;
            }

            UtilsWriter.printBodyLineReport(record, cost, writer);

        }
        UtilsWriter.printFooterReport(totalSum, writer);
    }

    private static void generatorReportForThirdTariff(LinkedList<Record> recordList, FileWriter writer) throws IOException {
        double totalSum = 0.0;
        double cost;

        for (Record record: recordList) {
            long minutes = record.getStartRecordDate().toInstant().until(
                    record.getEndRecordDate().toInstant(), ChronoUnit.MINUTES);

            cost = minutes * 1.5;
            totalSum += cost;

            UtilsWriter.printBodyLineReport(record, cost, writer);

        }
        UtilsWriter.printFooterReport(totalSum, writer);
    }

    private static void generatorReportForElevenTariff(LinkedList<Record> recordList, FileWriter writer) throws IOException {
        double totalSum = 0.0;
        double totalMinutesPepPeriod = 0.0;
        double maxMinutes = 100.0;
        double cost;

        for (Record record: recordList) {
            cost = 0.0;
            long minutes = record.getStartRecordDate().toInstant().until(
                    record.getEndRecordDate().toInstant(), ChronoUnit.MINUTES);

            totalMinutesPepPeriod += minutes;
            if (totalMinutesPepPeriod > maxMinutes && record.getTypeRecord().equals("02")) {
                cost = minutes * 1.5;
                totalSum += cost;
            } else if(record.getTypeRecord().equals("02")) {
                cost = minutes * 0.5;
                totalSum += cost;
            }

            UtilsWriter.printBodyLineReport(record, cost, writer);

        }
        UtilsWriter.printFooterReport(totalSum, writer);
    }
}
