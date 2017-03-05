package com.company.net.ukr.ksm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args) throws Exception {

        byte[] buf;
        RandomAccessFile f = new RandomAccessFile("D:\\КурсыJAVA\\JavaPro\\tasksInClass\\3_Lessons\\3-2\\GSON1\\JSON1\\json.txt", "r");
        try {
            buf = new byte[(int)f.length()];
            f.read(buf);
        } finally {
            f.close();
        }

        String result = new String(buf);

        Gson gson = new GsonBuilder().create();
        Person person = gson.fromJson(result, Person.class);

        System.out.println("Name: " + person.name);
        System.out.println("SurName: " + person.surname);

        for (String phone : person.phones){
            System.out.println("Phone: " + phone);
        }

        for (String site : person.sites){
            System.out.println("Site: " + site);
        }

        System.out.println("Country: " + person.address.country);
        System.out.println("City: " + person.address.city);
        System.out.println("Street: " + person.address.street);

//        Person json = (Person) gson.fromJson(result, Person.class);
//
//        System.out.println("" + json.address.city);
//        System.out.println("JSON: \n\t" + gson.toJson(json));

    }
}
