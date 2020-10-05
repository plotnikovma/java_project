package mailsend;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


public class DataCollection {

    public static void collectFiles() {

        SendMessage sendMessage = new SendMessage();
        Attributes attributes = new Attributes();
        String regions = attributes.getProperties("src\\main\\resources\\propertiesRegion.properties").getProperty("region");

        for (String r : Arrays.asList(regions.split(","))) {
            File filer = new File("src\\main\\resources\\properties" + r + ".properties");
            if (filer.exists()) {
                System.out.println("open properties " + r);
                Properties properties = attributes.getProperties("src\\main\\resources\\properties" + r + ".properties");
                //resources\properties" + r + ".properties  //src\main\resources\properties" + r + ".properties
                attributes.setLogin(properties.getProperty("login"));
                attributes.setPass(properties.getProperty("pass"));
                attributes.setTo(properties.getProperty("To"));
                attributes.setToCC(properties.getProperty("ToCC"));
                attributes.setFrom(properties.getProperty("From"));

                //собираем расположения всех элементов
                String dir = properties.getProperty("dir");
                String name = properties.getProperty("name");

                ArrayList<String> paths = new ArrayList<>(Arrays.asList(dir.split(",")));
                ArrayList<String> names = new ArrayList<>(Arrays.asList(name.split(",")));

                ArrayList<Integer> index = new ArrayList<>();
                for (String j : paths) {
                    System.out.println("Search " + j);
                    int k =  paths.indexOf(j);
                    File file = new File(paths.get(k));
                    if (file.exists()) {
                        System.out.println("find file " + names.get(k));
                        attributes.addObjnames(names.get(k));
                        attributes.addObjpaths(paths.get(k));

                        //далее уперся в java.util.ConcurrentModificationException))) , пришлось избавляться...
                        index.add(k);
                        } else {
                            System.out.println("file " + names.get(k) + " not exist");
                            index.add(k);
                        }
                    }
                    for (Integer i : index) {
                        paths.remove(i);
                        names.remove(i);
                    }
                    //send
                    sendMessage.send(Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(attributes.getLogin(), attributes.getPass());
                        }
                    }), attributes);

            } else {
                    System.out.println("properties" + r + ".properties not exist");
            }
        }
    }
}


