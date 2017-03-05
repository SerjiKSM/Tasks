package net.ukr.ksm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

public class PrepareSerializer {

    public static String serialize(Object object) throws IllegalAccessException {
        Class<?> cls = object.getClass();
        StringBuilder stringBuilder = new StringBuilder();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            if (!field.isAnnotationPresent(Save.class)){
                continue;
            }
            if (Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
            }

            stringBuilder.append(field.getName() + "=");

            if (field.getType() == int.class){
                stringBuilder.append(field.getInt(object));
            }else if (field.getType() == String.class){
                stringBuilder.append((String) field.get(object));
            }else if (field.getType() == Date.class){
                stringBuilder.append(((Date) field.get(object)).getTime());
            }

            stringBuilder.append(";");
        }

         return stringBuilder.toString();

    }

    public static <T> T deSerialize(String str, Class<T> tClass) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        T object = (T)tClass.newInstance();

        String[] pairs = str.split(";");
        for (String pair : pairs){
            String[] nameValue = pair.split("=");

            Field field = tClass.getDeclaredField(nameValue[0]);

            if(Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
            }

            if(field.isAnnotationPresent(Save.class)){
                if(field.getType() == String.class){
                    field.set(object, nameValue[1]);
                } else if(field.getType() == int.class){
                    field.setInt(object, Integer.parseInt(nameValue[1]));
                } else if(field.getType() == Date.class){
                    field.set(object, new Date(Long.parseLong(nameValue[1])));
                }
            }


        }

       return object;
    }
}
