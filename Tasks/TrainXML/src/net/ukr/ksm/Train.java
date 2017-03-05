package net.ukr.ksm;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;
import java.util.Date;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "train")
@XmlType(propOrder = {"id", "from", "to", "date", "departure"})
public class Train {

    @XmlElement(name = "date", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;

    @XmlAttribute(name = "id")
    private int id;

    @XmlElement(name = "from")
    private String from;

    @XmlElement(name = "to")
    private String to;

    @XmlElement(name = "departure")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    private LocalTime departure;

    public Train() {

    }

    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public Train(int id, String from, String to, Date date, LocalTime departure) {
        this.id = id;
        this.date = date;
        this.from = from;
        this.to = to;
        this.departure = departure;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {

        String result = "Train id: " + id + " "
                + "From: " + from + " "
                + "To: " + to + " "
                + "Date: " + date + " "
                + "Departure: " + departure + " ";
        return result;
    }
}
