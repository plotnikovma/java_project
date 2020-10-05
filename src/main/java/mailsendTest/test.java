package mailsendTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class test {
    public static void main(String[] args) throws IOException {
        ArrayList<String> path = new ArrayList();
        Properties prop = new Properties();
        //resources\properties" + r + ".properties  //src\main\resources\properties" + r + ".properties
        FileInputStream fIS = new FileInputStream("src\\main\\resources\\propertiesSO.properties");
        prop.load(fIS);
        String path_s = prop.getProperty("dir");
        ArrayList<String> paths = new ArrayList<>(Arrays.asList(path_s.split(",")));

        for (String s : paths) {
            System.out.println(s);
        }

       /* for (String j : paths) {
            System.out.println(j);
            int k =  paths.indexOf(j);
            File file = new File(paths.get(k));
            if (file.exists()) {
                System.out.println("find file " + names.get(k));
                obj.addObjnames(names.get(k));
                obj.addObjpaths(paths.get(k));
                //далее уперся в java.util.ConcurrentModificationException))) , пришлось избавляться...
                names.remove(k);
                paths.remove(k);*/
    }
}

