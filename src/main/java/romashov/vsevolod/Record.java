package romashov.vsevolod;

import java.sql.Timestamp;

public class Record {
    private String typeRecord;
    private String number;
    private Timestamp startRecordDate;
    private Timestamp endRecordDate;
    private String typeTariff;

    public Record(String typeRecord, String number, Timestamp startRecordDate,
                  Timestamp endRecordDate, String typeTariff) {
        this.typeRecord = typeRecord;
        this.number = number;
        this.startRecordDate = startRecordDate;
        this.endRecordDate = endRecordDate;
        this.typeTariff = typeTariff;
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

    public Timestamp getStartRecordDate() {
        return startRecordDate;
    }

    public void setStartRecordDate(Timestamp startRecordDate) {
        this.startRecordDate = startRecordDate;
    }

    public Timestamp getEndRecordDate() {
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
