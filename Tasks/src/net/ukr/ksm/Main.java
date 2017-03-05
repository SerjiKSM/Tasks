package net.ukr.ksm;

import java.io.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
        DataSerializer testSerializer = new DataSerializer();

        testSerializer.setNumber(100);
        testSerializer.setString("How are you?");

        Date date = new Date();
        testSerializer.setDate(date);

        String stringSerializer = PrepareSerializer.serialize(testSerializer);

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\Test\\testSerializer.out");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(stringSerializer);
        objectOutputStream.flush();
        objectOutputStream.close();

        System.out.println("Serialization done: " + stringSerializer);

        FileInputStream fileInputStream = new FileInputStream("d:\\Test\\testSerializer.out");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        String str = (String) objectInputStream.readObject();

        try {
            testSerializer = PrepareSerializer.deSerialize(str, DataSerializer.class);
            System.out.println("DeSerialization done: " + testSerializer.getDate() + ", " + testSerializer.getString()
                                                    + ", " + testSerializer.getNumber());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
