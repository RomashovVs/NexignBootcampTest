package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilsDate {

    public static Date convertStringToTimestamp(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyyMMddHHmmss").parse(dateString);
    }

    public static String convertDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }

    public static String duration(Date startDate, Date endDate){
        LocalDateTime startTimeDateLocal = LocalDateTime.ofInstant(
                startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime endTimeDateLocal = LocalDateTime.ofInstant(
                endDate.toInstant(), ZoneId.systemDefault());

        Duration durationTimeDate = Duration.between(startTimeDateLocal, endTimeDateLocal);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime durationDateLocal = LocalDateTime.of(1,1,1,0,0,0)
                .plus(durationTimeDate);
        return formatter.format(durationDateLocal);
    }

}
