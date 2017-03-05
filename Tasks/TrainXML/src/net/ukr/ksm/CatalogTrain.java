package net.ukr.ksm;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "trains")
public class CatalogTrain {

    @XmlElement(name = "train")
    private List<Train> trainList = new ArrayList<>();

    public void add(int id, String from, String to, String date, String departure) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Train train = new Train(id, from, to, sdf.parse(date), LocalTime.parse(departure, TimeAdapter.timeFormat));
        trainList.add(train);

    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (Train train : trainList) {
            stringBuilder.append("Train id: ").append(train.getId()).append("\n")
                    .append("from: ").append(train.getFrom()).append("\n")
                    .append("to: ").append(train.getTo()).append("\n")
                    .append("date: ").append(new SimpleDateFormat("dd.MM.yyyy").format(train.getDate())).append("\n")
                    .append("departure: ").append(train.getDeparture()).append("\n")
                    .append("\n");

        }

        return stringBuilder.toString();
    }

    public void getTimeTable(CatalogTrain parserXml, String dateDeparture, String timeFrom, String timeTo) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse(dateDeparture);

        LocalTime startTime = LocalTime.parse(timeFrom, DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime endTime = LocalTime.parse(timeTo, DateTimeFormatter.ISO_LOCAL_TIME);

        if (startTime.isAfter(endTime)){
            System.out.println("The start time is greater end time!");
            return;
        }

        for (Train train : trainList) {

            if (date.equals(train.getDate())) {

                LocalTime timeDeparture = train.getDeparture();
                if ((timeDeparture.equals(startTime) || timeDeparture.isAfter(startTime))
                        && (timeDeparture.equals(endTime) || timeDeparture.isBefore(endTime))) {

                    System.out.println("Train id: " + train.getId());
                    System.out.println("from: " + train.getFrom());
                    System.out.println("to: " + train.getTo());
                    System.out.println();

                }

            }

        }

    }


}
