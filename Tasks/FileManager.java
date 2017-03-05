package ua.kiev.prog;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileManager {

    private static long timeLimit = 20000;
    private long timeStart;
    private long currentTime;

    private String path;
    private static ConcurrentHashMap<String, byte[]> map = new ConcurrentHashMap<String, byte[]>();

    public FileManager(String path) {
        // "c:\folder\" --> "c:\folder"
        if (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);

        this.path = path;

        this.timeStart = System.currentTimeMillis() + timeLimit;
    }

    public byte[] get(String url) {
        try {

//            byte[] buf = map.get(url);
//            if (buf != null) // in cache
//                return buf;

            currentTime = System.currentTimeMillis();

            byte[] buf = map.get(url);

            if(timeStart > currentTime && buf != null){
                return buf;
            }else{
                timeStart = System.currentTimeMillis() + timeLimit;
            }

            // "c:\folder" + "/index.html" -> "c:/folder/index.html"
            String fullPath = path.replace('\\', '/') + url;

            RandomAccessFile f = new RandomAccessFile(fullPath, "r");
            try {
                buf = new byte[(int) f.length()];
                f.read(buf, 0, buf.length);
            } finally {
                f.close();
            }

            map.put(url, buf); // put to cache
            return buf;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
