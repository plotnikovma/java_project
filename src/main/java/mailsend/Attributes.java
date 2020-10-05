package mailsend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Attributes {
    private String login;
    private String pass;
    private String to;
    private String toCC;
    private String from;
    ArrayList<String> filePaths = new ArrayList();
    ArrayList<String> fileNames = new ArrayList();

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getTo() {
        return to;
    }

    public String getToCC() {
        return toCC;
    }

    public String getFrom() {
        return from;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setToCC(String toCC) {
        this.toCC = toCC;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void addObjpaths(String path) {
        filePaths.add(path);
    }

    public void addObjnames(String name) {
        fileNames.add(name);
    }

    public Properties getProperties(String path) {
        Properties properties = new Properties();
        try (FileInputStream r_fileInputStream = new FileInputStream(path);) {
            properties.load(r_fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
