package proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        //Создаем оригинальный объект
        Man vasia = new Man("Вася", 30, "Санкт-Петербург", "Россия");

        //Получаем загрузчик класса у оригинального объекта
        ClassLoader vasiaClassLoader = vasia.getClass().getClassLoader();

        //Получаем все интерфейсы, которые реализует оригинальный объект
        Class[] interfaces = vasia.getClass().getInterfaces();

        //Создаем прокси нашего объекта vasia
        Person proxyVasia = (Person) Proxy.newProxyInstance(vasiaClassLoader, interfaces, (Object proxy, Method method, Object[] args1) -> {
                System.out.println("Привет!");
                return method.invoke(vasia, args1);
        });

        //Вызываем у прокси объекта один из методов нашего оригинального объекта
        proxyVasia.sayFrom(vasia.getCity(), vasia.getCountry());

    }
}