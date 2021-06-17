package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("d://temp1.txt");
            InputStream inputStream = new FileInputStream("d://temp1.txt");

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user = new User();
            user.setFirstName("firstName");
            user.setLastName("lastName");
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse("28.12.2016");
            user.setBirthDate(date);
            //user.setBirthDate(new SimpleDateFormat("dd MM yyyy").parse("10.06.1982"));
            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            if(javaRush.equals(loadedObject)){
                System.out.println(true);
            }else{
                System.out.println(false);
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {

        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            if ( users.size()>0) {
                int n = users.size();
                writer.write(n + System.lineSeparator());
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    writer.write("FirstName:" + user.getFirstName() + System.lineSeparator());
                    writer.write("LastName:" + user.getLastName() + System.lineSeparator());
                    writer.write(user.isMale() ? "Male:+М" + System.lineSeparator() : "Male:Ж" + System.lineSeparator());
                    writer.write("Country:" + user.getCountry().getDisplayName() + System.lineSeparator());
                    writer.write("BirthDate:" + new SimpleDateFormat("dd.MM.yyyy").format(user.getBirthDate()) + System.lineSeparator());
                    writer.write("endUser");
                }
                writer.flush();
            }

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if(reader.ready()) {
            String line = reader.readLine();
            int koll = Integer.parseInt(line);
                String[] lineSplit;
                for (int i = 0; i < koll; i++) {
                    User user = new User();
                    while (!(line = reader.readLine()).equals("endUser")) {
                        lineSplit = line.split(":");
                        switch (lineSplit[0]) {
                            case "FirstName":
                                user.setFirstName(lineSplit[1]);
                                break;
                            case "LastName":
                                user.setLastName(lineSplit[1]);
                                break;
                            case "Male":
                                user.setMale(lineSplit[1] == "M" ? true : false);
                                break;
                            case "Country":
                                user.setCountry(User.Country.RUSSIA);
                                break;
                            case "BirthDate":
                                user.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").parse(lineSplit[1]));
                                break;
                        }
                        //lineSplit=null;
                    }
                    users.add(user);
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
