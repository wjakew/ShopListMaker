/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
 */

package shoplistmaker;
import java.io.IOException;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
/**
 *MailSender object
 * @author jakub
 * Object for sending e-mails with notes
 */
public class MailSender {

    final String version = "v1.0.0";
    final String HOST = "smtp.gmail.com";
    final String USER="main.tes.instruments@gmail.com";
    private String password="";
    
    Properties props;
    
    ArrayList<String> log;
    
    MimeMessage message;
    String mail_email;
    String mail_password;
    InfoHandler to_send;
    DictReader to_send2;
    String email_address;
    Date act;
    
    
    MailSender(InfoHandler list,String email) throws MessagingException, IOException{

        System.out.println("MailSender by ProgramNote "+version);
        mail_email = "main.tes.instruments@gmail.com";
        mail_password = "m5hdmM0I*bSjnHHyZX7f5QGs7PcZfT4j#YP$^i#y5yJ10!5HH@9$n0RI@2o1Dks$1gjxFA9";
        this.to_send = list;
        this.to_send2 = null;
        email_address = email;
        act = new Date();
        log = new ArrayList<>();
        System.out.println("Got email adress: "+email_address);
    }
    MailSender(DictReader list,String email) throws MessagingException, IOException{

        System.out.println("MailSender by ProgramNote "+version);
        mail_email = "main.tes.instruments@gmail.com";
        mail_password = "m5hdmM0I*bSjnHHyZX7f5QGs7PcZfT4j#YP$^i#y5yJ10!5HH@9$n0RI@2o1Dks$1gjxFA9";
        this.to_send2 = list;
        this.to_send = null;
        email_address = email;
        act = new Date();
        log = new ArrayList<>();
        System.out.println("Got email adress: "+email_address);
    }
    
    void run(){
        prepare();
        Session actual_session = ret_session();
        
        try{
            
            compose(actual_session);
            
            if ( to_send2 == null){
                System.out.println("Title of the message: "+"Your shop list from "+act.toString());
            }
            if ( to_send == null){
                System.out.println("Title of the message: "+"Dictionary from SLM");
            }
            
            System.out.println("Content of the message:");
            System.out.println(prepare_content());
            
            Transport.send(message); 
            
            System.out.println("Note sent to : "+email_address);
            
        }catch(MessagingException e){
            System.out.println(e.toString());
        } 
    }

    void prepare(){
        password = mail_password;
        props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");     
        props.setProperty("mail.host", "smtp.gmail.com");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.port", "465");  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.socketFactory.port", "465");  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.socketFactory.fallback", "false"); 
    }
    
    Session ret_session(){
        return Session.getDefaultInstance(props,  
            new javax.mail.Authenticator() {  
              protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(USER,password);  
              }}); 
    }
    String prepare_content(){
        String to_ret="";
        if ( to_send2 == null){
            for (String line : to_send.act_cart.make_list().split("\n")){
                to_ret = to_ret + line +"\n";
            }
        }
        if ( to_send == null ){
            for(String line : to_send2.L_zrozumianeslowa){
                to_ret = to_ret + line +"\n";
            }
        }
        
        return to_ret;
    }
    
    void compose(Session actual) throws AddressException, MessagingException{
        message = new MimeMessage(actual); 
        message.setFrom(new InternetAddress(USER));  
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(email_address));  
        message.setSubject("Your shop list from "+act.toString());  
        message.setText(prepare_content());
    }
}
