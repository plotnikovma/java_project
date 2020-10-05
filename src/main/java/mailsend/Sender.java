package mailsend;

import javax.mail.MessagingException;
import java.io.IOException;

public class Sender {
    static {
        System.out.println("Start Mail_sender");
    }
    public static void main (String[] args) throws IOException,MessagingException{
        DataCollection.collectFiles();

    }
}
