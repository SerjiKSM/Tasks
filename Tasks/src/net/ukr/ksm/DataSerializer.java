package net.ukr.ksm;

import java.util.Date;

public class DataSerializer {

    @Save private int number;
    @Save public Date date;
    @Save private String string;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
