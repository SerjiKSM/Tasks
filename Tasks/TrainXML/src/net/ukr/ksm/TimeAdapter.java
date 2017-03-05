package net.ukr.ksm;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeAdapter extends XmlAdapter <String, LocalTime> {

    public static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public String marshal(LocalTime localTime) throws Exception {
        return localTime.format(timeFormat);
    }

    @Override
    public LocalTime unmarshal(String time) throws Exception {
        return LocalTime.parse(time, timeFormat);
    }



}
