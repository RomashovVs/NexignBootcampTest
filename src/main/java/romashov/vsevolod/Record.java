package romashov.vsevolod;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import romashov.vsevolod.utils.UtilsDate;

public class Record {
    private String typeRecord;
    private String number;
    private Date startRecordDate;
    private Date endRecordDate;
    private String typeTariff;

    public Record(String typeRecord, String number, Date startRecordDate,
                  Date endRecordDate, String typeTariff) {
        this.typeRecord = typeRecord;
        this.number = number;
        this.startRecordDate = startRecordDate;
        this.endRecordDate = endRecordDate;
        this.typeTariff = typeTariff;
    }

    public static Record createNewRecord(String readLineRecord) throws ParseException {
        String[] arrayWordLine = readLineRecord.split(", ");

        Date startRecordDate = UtilsDate.convertStringToTimestamp(arrayWordLine[2]);
        Date endRecordDate = UtilsDate.convertStringToTimestamp(arrayWordLine[3]);

        return new Record(arrayWordLine[0], arrayWordLine[1],
                startRecordDate, endRecordDate, arrayWordLine[4]);
    }

    public String getTypeRecord() {
        return typeRecord;
    }

    public void setTypeRecord(String typeRecord) {
        this.typeRecord = typeRecord;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getStartRecordDate() {
        return startRecordDate;
    }

    public void setStartRecordDate(Timestamp startRecordDate) {
        this.startRecordDate = startRecordDate;
    }

    public Date getEndRecordDate() {
        return endRecordDate;
    }

    public void setEndRecordDate(Timestamp endRecordDate) {
        this.endRecordDate = endRecordDate;
    }

    public String getTypeTariff() {
        return typeTariff;
    }

    public void setTypeTariff(String typeTariff) {
        this.typeTariff = typeTariff;
    }
}
