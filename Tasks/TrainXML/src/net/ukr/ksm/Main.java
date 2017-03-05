package net.ukr.ksm;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws JAXBException {

        CatalogTrain catalogTrain = new CatalogTrain();

        try {
            catalogTrain.add(1, "Kiev", "Donetsk", "19.12.2015", "15:05");
            catalogTrain.add(2, "Lviv", "Donetsk", "19.12.2015", "19:05");
            catalogTrain.add(3, "Kiev", "Krakov", "15.05.2015", "06:35");
            catalogTrain.add(4, "Donetsk", "Warshawa", "12.05.2015", "22:00");
            catalogTrain.add(5, "Warshawa", "Kiev", "11.05.2015", "23:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        File file = new File("D://test//TrainTimetable.xml");
        Parser parser = new JAXBParser();
        parser.saveObject(file, catalogTrain);

        CatalogTrain parserXml = (CatalogTrain) parser.getObject(file, CatalogTrain.class);
        try {
            catalogTrain.getTimeTable(parserXml, "19.12.2015", "15:00", "19:05");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
