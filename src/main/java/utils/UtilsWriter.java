package utils;

import romashov.vsevolod.Record;

import java.io.FileWriter;
import java.io.IOException;

public class UtilsWriter {

    public static void printHeadReport(Record record, FileWriter writer) throws IOException {
        writer.write("Tariff index: ".concat( record.getTypeTariff()).concat("\n"));
        writer.write("----------------------------------------------------------------------------\n");
        writer.write(String.format("%-50s", "Report for phone number "
                .concat(record.getNumber())
                .concat(":")));
        writer.write("\n");
        writer.write("----------------------------------------------------------------------------\n");
        writer.write(String.format("%-12s%-22s%-22s%-11s%-8s", "| Call Type",
                "|   Start Time",
                "|     End Time", "| Duration", "| Cost"));
        writer.write("|\n");
        writer.write("----------------------------------------------------------------------------\n");
    }

    public static void printBodyLineReport(Record record, Double cost, FileWriter writer) throws IOException {
        String startTimeStr = UtilsDate.convertDateToString(record.getStartRecordDate());
        String endTimeStr = UtilsDate.convertDateToString(record.getEndRecordDate());

        String formatterDuration = UtilsDate.duration(record.getStartRecordDate(), record.getEndRecordDate());

        writer.write(String.format("%-12s%-22s%-22s%-11s| %5.2f |",
                "|     ".concat(record.getTypeRecord()),
                "| ".concat(startTimeStr),
                "| ".concat(endTimeStr),
                "| ".concat(formatterDuration),
                cost));
        writer.write("\n");
    }

    public static void printFooterReport(Double totalSum, FileWriter writer) throws IOException {
        writer.write("----------------------------------------------------------------------------\n");
        writer.write(String.format("|                                           Total Cost: |     %5.2f rubles |\n",
                totalSum));
        writer.write("----------------------------------------------------------------------------\n");
    }
}
