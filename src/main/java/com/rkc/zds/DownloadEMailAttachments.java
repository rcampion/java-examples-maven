package com.rkc.zds;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

public class DownloadEMailAttachments {
	private String downloadDirectory;

	public void setSaveDirectory(String dir) {
		this.downloadDirectory = dir;
	}

	public void downloadEmailAttachments(String host, String port, String userName, String password)
			throws NoSuchProviderException, MessagingException, IOException {
		Properties properties = setMailServerProperties(host, port);
		
		Store store = setSessionStoreProperties(userName, password, properties);
		
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		Message[] arrayMessages = inbox.getMessages();
		for (int i = 0; i < arrayMessages.length; i++) {
			Message message = arrayMessages[i];
			Address[] fromAddress = message.getFrom();
			String from = fromAddress[0].toString();
			String subject = message.getSubject();
			String sentDate = message.getSentDate().toString();
			List<String> attachments = new ArrayList<String>();
			if (message.getContentType().contains("multipart")) {
				attachments = downloadAttachments(message);
			}

			System.out.println("Message #" + (i + 1) + ":");
			System.out.println(" From: " + from);
			System.out.println(" Subject: " + subject);
			System.out.println(" Sent Date: " + sentDate);
			System.out.println(" Attachments: " + attachments);
		}
		inbox.close(false);
		store.close();
	}

	public List<String> downloadAttachments(Message message) throws IOException, MessagingException {
		List<String> downloadedAttachments = new ArrayList<String>();
		Multipart multiPart = (Multipart) message.getContent();
		int numberOfParts = multiPart.getCount();
		for (int partCount = 0; partCount < numberOfParts; partCount++) {
			MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
			if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
				String file = part.getFileName();
				part.saveFile(downloadDirectory + File.separator + part.getFileName());
				downloadedAttachments.add(file);
			}
		}

		return downloadedAttachments;
	}

	public Store setSessionStoreProperties(String userName, String password, Properties properties)
			throws NoSuchProviderException, MessagingException {
		
		//props.put("mail.pop3.ssl.enable", "true"); // Use SSL
		//properties.put("mail.pop3.ssl.enable", "true");
		//Session session = Session.getDefaultInstance(properties);
		//Session session = Session.getInstance(properties);
		
		Properties props = new Properties();

		props.put("mail.pop3.host", "mail.zdslogic.com");
		props.put("mail.pop3.user", userName);
		props.put("mail.pop3.socketFactory", 110);
		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.port", 110);		
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(userName, "Admin8246+");

		    }
		});
		
		Store store = session.getStore("pop3");
		store.connect(userName, password);
		return store;
	}

	public Properties setMailServerProperties(String host, String port) {
		Properties properties = new Properties();

		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", port);
		
		properties.put("mail.pop3.ssl.enable", "true");

		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));

		properties.setProperty("mail.pop3.ssl.enable", "true");
		
		return properties;
	}

	public static void testEMail() {
		Properties props = new Properties();

		props.put("mail.pop3.host", "mail.zdslogic.com");

		props.put("mail.pop3.user", "richard.campion@zdslogic.com");

		props.put("mail.pop3.socketFactory", 110);

		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		props.put("mail.pop3.port", 110);

		Session session = Session.getDefaultInstance(props, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("richard.campion@zdslogic.com", "Admin8246+");

		    }
		});

		try {
		    Store store = session.getStore("pop3");

		    store.connect("pop.gmail.com", "mymail@gmail.com", "mypaswword");

		    Folder fldr = store.getFolder("INBOX");

		    fldr.open(Folder.READ_ONLY);

		    Message[] msg = fldr.getMessages();

		    Address[] address;

/*
		    for (int i = 0; i < msg.length; i++) {

		        jTextArea1.setText("SentDate : " + msg[i].getSentDate() + "\n" + "From : " + msg[i].getFrom()[0] + "\n" + "Subject : " + msg[i].getSubject() + "\n" + "Message : " + "\n" + msg[i].getContent().toString());

		    }
*/
		    fldr.close(true);

		    store.close();

		} catch (Exception e) {
		    System.out.println(e);
		}		
	}
	
	public static void receiveEmail(String pop3Host, String storeType,final String user, final String password) {  
        try {  
         //1) get the session object  
         Properties props = new Properties();  
         //props.put("mail.pop3.host", "pop.gmail.com");
         
         props.put("mail.pop3.host", "mail.zdslogic.com");   
         props.put("mail.pop3.ssl.enable", "true"); // Use SSL
         props.put("mail.pop3.user", user);
         //props.put("mail.pop3.socketFactory", 995);
         props.put("mail.pop3.socketFactory", 110);         
         props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         //props.put("mail.pop3.port", 995);
         props.put("mail.pop3.port", 110);
         
         Session session = Session.getDefaultInstance(props, new Authenticator() {
             @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(user, password);

              }
          });  
           
         //2) create the POP3 store object and connect with the pop server  
         Store emailStore = (Store) session.getStore(storeType);  
         emailStore.connect("pop.gmail.com",995,user, password);  
        
         //3) create the folder object and open it  
         Folder emailFolder = emailStore.getFolder("INBOX");  
         emailFolder.open(Folder.READ_ONLY);  
        
         //4) retrieve the messages from the folder in an array and print it  
         Message[] messages = emailFolder.getMessages();  
         for (int i = 0; i < messages.length; i++) {  
          Message message = messages[i];  
          System.out.println("---------------------------------");  
          System.out.println("Email Number " + (i + 1));  
          System.out.println("Subject: " + message.getSubject());  
          System.out.println("From: " + message.getFrom()[0]);  
          System.out.println("Text: " + message.getContent().toString());  
         }  
        
         //5) close the store and folder objects  
         emailFolder.close(false);  
         emailStore.close();  
        
        } catch (MessagingException e) {e.printStackTrace();}  
        catch (IOException e) {e.printStackTrace();}  
       }
	
	public static void main(String[] args) {
		// String host = "pop.gmail.com";
		// String port = "995";
		String host = "mail.zdslogic.com";
		String port = "110";
		//String userName = "richard.campion@zdslogic.com";
		String userName = "richard.campion";
		String password = "Admin8246+";

		String saveDirectory = "/home/www/share/nginx/servers/www/www.zdslogic.com/html/data/files/uploads/" + userName;

		DownloadEMailAttachments receiver = new DownloadEMailAttachments();
		receiver.setSaveDirectory(saveDirectory);
		try {
			receiver.downloadEmailAttachments(host, port, userName, password);
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
