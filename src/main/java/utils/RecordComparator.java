package utils;

import romashov.vsevolod.Record;

import java.util.Comparator;

public class RecordComparator implements Comparator<Record> {
    @Override
    public int compare(Record recordLeft, Record recordRight) {
        int result = recordLeft.getTypeRecord().compareTo(recordRight.getTypeRecord());
        if (result == 0)
            result = recordLeft.getStartRecordDate().compareTo(recordRight.getStartRecordDate());
        return result;
    }
}
