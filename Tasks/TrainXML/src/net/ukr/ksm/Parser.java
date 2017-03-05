package net.ukr.ksm;


import javax.xml.bind.JAXBException;
import java.io.File;

public interface Parser {

    Object getObject(File file, Class cls) throws JAXBException;
    void saveObject(File file, Object object)throws JAXBException;

}
