package mailsend;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;

public class SendMessage {

    public void send(Session session, Attributes obj)  {
        //Создаем объект письмо MimeMessage типа Mime, расширяет Message, и передаем в него сессию
        MimeMessage message = new MimeMessage(session);
        //Добавляем отправителя,получателя и копии, тему
        try {
            message.setFrom(new InternetAddress(obj.getFrom()));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(obj.getTo()));
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(obj.getToCC()));
            message.setSubject("MOTIV_Telecom_srezGUVD");
            MimeBodyPart textbodypart = new MimeBodyPart();
            textbodypart.setText("Добрый день! Высылаем подекадный отчет. Motivtelecom.ru \n" +
                    "\nВНИМАНИЕ! \n" +
                    "\nДанное сообщение сформировано и отправлено автоматически. Не нужно отвечать на данное письмо. \n" +
                    "\nПри возникновении каких-либо вопросов, просьба связаться с контактным лицом организации");
            //создаем объект для хранения объектов MimeBodyPart(BodyPart(source))
            Multipart multipart = new MimeMultipart();
            ArrayList<MimeBodyPart> bodypart = new ArrayList<>();
            for (int i = 0; i < obj.filePaths.size(); i++) {
                //определяем путь до файла и имя файла
                bodypart.add(new MimeBodyPart());
                // надо заполнить BodyPart
                bodypart.get(i).setDataHandler(new DataHandler(new FileDataSource(obj.filePaths.get(i))));
                bodypart.get(i).setFileName(obj.fileNames.get(i));
                multipart.addBodyPart(bodypart.get(i));
            }

            if (bodypart.isEmpty()) {
                System.out.println("Content is null");
                System.out.println("message not send");
            } else {
                multipart.addBodyPart(textbodypart);
                message.setContent(multipart);
                System.out.println("Content set");
                Transport.send(message);
                System.out.println("message send");
            }
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
