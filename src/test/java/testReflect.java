import util.StringTuple2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class testReflect {

    public  void  printFieldsAndValues(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Field[] declaredFields = o.getClass().getDeclaredFields();
        String[] strings = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            strings[i] = declaredFields[i].getName();
        }
            for (String string : strings) {
                String mtName = "get" + string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
                System.out.println("对象属性名:" + mtName);
                Method method = o.getClass().getMethod(mtName, new Class[]{});
                Object invoke = method.invoke(o);
                System.out.println("对象属性值:" + invoke);
            }


    }
    public  String[]  getFieldsArray(Object o) throws NoSuchMethodException {

        Field[] declaredFields = o.getClass().getDeclaredFields();
        String[] strings = new String[declaredFields.length];
            return  strings;
        }
    public  Object[]  getValuesArray(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        String[] strings = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            strings[i] = declaredFields[i].getName();
        }
        Object[] values = new Object[declaredFields.length];
        for (int i = 0; i < strings.length; i++) {
            String mtName = "get" + strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1, strings[i].length());
            Method method = o.getClass().getMethod(mtName, new Class[]{});
            values[i] =  method.invoke(o);
        }
        return values;
    }

}

