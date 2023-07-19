package com.rkc.zds;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import com.sun.mail.util.BASE64DecoderStream;

public class ReadMultipartMail {
	
	static String protocol;
	static String host = null;
	static String user = null;
	static String password = null;
	static String mbox = null;
	static String url = null;
	static int port = -1;
	static boolean verbose = false;
	static boolean debug = false;
	static boolean showStructure = false;
	static boolean showMessage = false;
	static boolean showAlert = false;
	static boolean saveAttachments = false;
	static int attnum = 1;

	public static void main(String args[]) throws Exception {

		String host = "mail.zdslogic.com";
		String username = "richard.campion";
		String password = "Admin8246+";

		System.setProperty("mail.mime.decodeparameters", "true");

		Properties properties = System.getProperties();
		Session session = Session.getDefaultInstance(properties);

		Store store = session.getStore("pop3");
		store.connect(host, username, password);

		Folder folder = store.getFolder("inbox");

		if (!folder.exists()) {
			System.out.println("No INBOX...");
			System.exit(0);
		}
		folder.open(Folder.READ_WRITE);
		Message[] messages = folder.getMessages();

		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];

			for (@SuppressWarnings("unchecked")
			Enumeration<Header> headersEnum = message.getAllHeaders(); headersEnum.hasMoreElements();) {
				Header header = headersEnum.nextElement();
				System.out.println("Header Name");
				System.out.println(header.getName());
				System.out.println("Header Value");
				System.out.println(header.getValue());
				// headers.put(header.getName(), header.getValue());
			}

			// message.setFlag(Flags.Flag.DELETED, true);

			System.out.println("------------ Message " + (i + 1) + " ------------");
			String from = InternetAddress.toString(messages[i].getFrom());
			if (from != null) {
				System.out.println("From: " + from);
			}

			String replyTo = InternetAddress.toString(messages[i].getReplyTo());
			if (replyTo != null) {
				System.out.println("Reply-to: " + replyTo);
			}
			String to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
			if (to != null) {
				System.out.println("To: " + to);
			}

			String subject = messages[i].getSubject();
			if (subject != null) {
				System.out.println("Subject: " + subject);
			}
			Date sent = messages[i].getSentDate();
			if (sent != null) {
				System.out.println("Sent: " + sent);
			}

			System.out.println();
			System.out.println("Message : ");

			if (message.getContentType().contains("multipart")) {
//				attachments = downloadAttachments(message);

				Multipart multipart = (Multipart) messages[i].getContent();
				
				extractAttachment(multipart);

				for (int x = 0; x < multipart.getCount(); x++) {
					BodyPart bodyPart = multipart.getBodyPart(x);

					System.out.println("Body Part:");
					System.out.println(bodyPart.getContent().toString());

					// BodyPart bodyPart = multipart.getBodyPart(k);

					if (bodyPart.isMimeType(("text/*"))) {
						System.out.println(bodyPart.getContent());

						String text = (String) bodyPart.getContent();

						// Part part = (Part) bodyPart.getContent();
						// String text = getText(bodyPart);
						System.out.println("Multipart text:");
						// System.out.println(bodyPart.getContent());
						System.out.println(text);

					}

					String disposition = bodyPart.getDisposition();
					System.out.println(bodyPart.getContent().toString());
					System.out.println(bodyPart.getFileName());
					
					
		            
					// seperateBodyAndAttachments(bodyPart);
					// bodyPart.getContentID();
					// disposition.g
					if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
						System.out.println(disposition.toString());
						//extractAttachment(bodyPart);
						BASE64DecoderStream base64DecoderStream = (BASE64DecoderStream) bodyPart.getContent();
						base64DecoderStream.toString();
						// byte[] byteArray = IOUtils.toByteArray(base64DecoderStream);
						// byte[] encodedBase64 = Base64.encodeBase64(byteArray);
						// base64Content[0] = new String(encodedBase64, "UTF-8");

						InputStream stream = (InputStream) bodyPart.getInputStream();
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

						while (bufferedReader.ready()) {
							System.out.println(bufferedReader.readLine());
						}
						System.out.println();

						System.out.println(bodyPart.getDescription());
						System.out.println(bodyPart.getContent());

						System.out.println("Mail has some attachment : ");
						String test = bodyPart.getFileName();
						ByteBuffer buffer = StandardCharsets.UTF_8.encode(test);
						String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();

						System.out.println("file name : " + test);
						DataHandler handler = bodyPart.getDataHandler();
						System.out.println("file name : " + handler.getName());
					} else {
						String text = getText(bodyPart);
						// String text = (String) bodyPart.getContent();
						System.out.println(bodyPart.getContent());
						System.out.println(text);
					}
				}
			}
			System.out.println();
		}
		folder.close(false);
		store.close();
	}

	private static boolean textIsHtml = false;

	/**
	 * Return the primary text content of the message.
	 */
	private static String getText(BodyPart p) throws MessagingException, IOException {
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			textIsHtml = p.isMimeType("text/html");
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s;
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}

	public void seperateBodyAndAttachments(MimeMessage mm) throws MessagingException, IOException {
		String mimeType = mm.getContentType();
		System.out.println("Message is a " + mimeType);
		Object content = mm.getContent();
		if (content instanceof String) {
			System.out.println("Body: " + content);
		} else if (content instanceof MimeMultipart) {
			MimeMultipart multi = (MimeMultipart) content;
			System.out.println("We have a " + multi.getContentType());
			for (int i = 0; i < multi.getCount(); ++i) {
				BodyPart bo = multi.getBodyPart(i);
				System.out.println("Content " + i + " is a " + bo.getContentType());
				// Now that body part could again be a MimeMultipart...
				Object bodyContent = bo.getContent();
				// possibly build a recurion here -> the logic is the same as for
				// mm.getContent() above
			}
		} else {
			System.out.println("Some other content: " + content.getClass().getName());
		}
	}
	
	private static List<DataSource> extractAttachment(Multipart multipart) {
	    List<DataSource> attachments = new ArrayList<>();
	    try {

	        for (int i = 0; i < multipart.getCount(); i++) {
	            BodyPart bodyPart = multipart.getBodyPart(i);

	            if (bodyPart.getContent() instanceof Multipart) {
	                // part-within-a-part, do some recursion...
	                extractAttachment((Multipart) bodyPart.getContent());
	            }
	            String fileName = bodyPart.getFileName();
	            System.out.println("fileName: " + fileName);
	            
	            System.out.println("bodyPart.getDisposition(): " + bodyPart.getDisposition());
	            if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
	                continue; // dealing with attachments only
	            }

	            InputStream is = bodyPart.getInputStream();
	            fileName = bodyPart.getFileName();
	            String contentType = bodyPart.getContentType();
	            ByteArrayDataSource dataSource = new ByteArrayDataSource(is, contentType);
	            dataSource.setName(fileName);
	            attachments.add(dataSource);
	        }
	    } catch (IOException | MessagingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return attachments;
	}
	
	public static void dumpPart(Part p) throws Exception {
		if (p instanceof Message)
			dumpEnvelope((Message) p);

		/**
		 * Dump input stream ..
		 * 
		 * InputStream is = p.getInputStream(); // If "is" is not already buffered, wrap
		 * a BufferedInputStream // around it. if (!(is instanceof BufferedInputStream))
		 * is = new BufferedInputStream(is); int c; while ((c = is.read()) != -1)
		 * System.out.write(c);
		 * 
		 **/

		String ct = p.getContentType();
		try {
			pr("CONTENT-TYPE: " + (new ContentType(ct)).toString());
		} catch (ParseException pex) {
			pr("BAD CONTENT-TYPE: " + ct);
		}
		String filename = p.getFileName();
		if (filename != null)
			pr("FILENAME: " + filename);

		/*
		 * Using isMimeType to determine the content type avoids fetching the actual
		 * content data until we need it.
		 */
		if (p.isMimeType("text/plain")) {
			pr("This is plain text");
			pr("---------------------------");
			if (!showStructure && !saveAttachments)
				System.out.println((String) p.getContent());
		} else if (p.isMimeType("multipart/*")) {
			pr("This is a Multipart");
			pr("---------------------------");
			Multipart mp = (Multipart) p.getContent();
			level++;
			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				dumpPart(mp.getBodyPart(i));
			level--;
		} else if (p.isMimeType("message/rfc822")) {
			pr("This is a Nested Message");
			pr("---------------------------");
			level++;
			dumpPart((Part) p.getContent());
			level--;
		} else {
			if (!showStructure && !saveAttachments) {
				/*
				 * If we actually want to see the data, and it's not a MIME type we know, fetch
				 * it and check its Java type.
				 */
				Object o = p.getContent();
				if (o instanceof String) {
					pr("This is a string");
					pr("---------------------------");
					System.out.println((String) o);
				} else if (o instanceof InputStream) {
					pr("This is just an input stream");
					pr("---------------------------");
					InputStream is = (InputStream) o;
					int c;
					while ((c = is.read()) != -1)
						System.out.write(c);
				} else {
					pr("This is an unknown type");
					pr("---------------------------");
					pr(o.toString());
				}
			} else {
				// just a separator
				pr("---------------------------");
			}
		}

		/*
		 * If we're saving attachments, write out anything that looks like an attachment
		 * into an appropriately named file. Don't overwrite existing files to prevent
		 * mistakes.
		 */
		if (saveAttachments && level != 0 && p instanceof MimeBodyPart && !p.isMimeType("multipart/*")) {
			String disp = p.getDisposition();
			// many mailers don't include a Content-Disposition
			if (disp == null || disp.equalsIgnoreCase(Part.ATTACHMENT)) {
				if (filename == null)
					filename = "Attachment" + attnum++;
				pr("Saving attachment to file " + filename);
				try {
					File f = new File(filename);
					if (f.exists())
						// XXX - could try a series of names
						throw new IOException("file exists");
					((MimeBodyPart) p).saveFile(f);
				} catch (IOException ex) {
					pr("Failed to save attachment: " + ex);
				}
				pr("---------------------------");
			}
		}
	}
	
	public static void dumpEnvelope(Message m) throws Exception {
		pr("This is the message envelope");
		pr("---------------------------");
		Address[] a;
		// FROM
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				pr("FROM: " + a[j].toString());
		}

		// REPLY TO
		if ((a = m.getReplyTo()) != null) {
			for (int j = 0; j < a.length; j++)
				pr("REPLY TO: " + a[j].toString());
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				pr("TO: " + a[j].toString());
				InternetAddress ia = (InternetAddress) a[j];
				if (ia.isGroup()) {
					InternetAddress[] aa = ia.getGroup(false);
					for (int k = 0; k < aa.length; k++)
						pr("  GROUP: " + aa[k].toString());
				}
			}
		}

		// SUBJECT
		pr("SUBJECT: " + m.getSubject());

		// DATE
		Date d = m.getSentDate();
		pr("SendDate: " + (d != null ? d.toString() : "UNKNOWN"));

		// FLAGS
		Flags flags = m.getFlags();
		StringBuffer sb = new StringBuffer();
		Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags

		boolean first = true;
		for (int i = 0; i < sf.length; i++) {
			String s;
			Flags.Flag f = sf[i];
			if (f == Flags.Flag.ANSWERED)
				s = "\\Answered";
			else if (f == Flags.Flag.DELETED)
				s = "\\Deleted";
			else if (f == Flags.Flag.DRAFT)
				s = "\\Draft";
			else if (f == Flags.Flag.FLAGGED)
				s = "\\Flagged";
			else if (f == Flags.Flag.RECENT)
				s = "\\Recent";
			else if (f == Flags.Flag.SEEN)
				s = "\\Seen";
			else
				continue; // skip it
			if (first)
				first = false;
			else
				sb.append(' ');
			sb.append(s);
		}

		String[] uf = flags.getUserFlags(); // get the user flag strings
		for (int i = 0; i < uf.length; i++) {
			if (first)
				first = false;
			else
				sb.append(' ');
			sb.append(uf[i]);
		}
		pr("FLAGS: " + sb.toString());

		// X-MAILER
		String[] hdrs = m.getHeader("X-Mailer");
		if (hdrs != null)
			pr("X-Mailer: " + hdrs[0]);
		else
			pr("X-Mailer NOT available");
	}

	static String indentStr = "                                               ";
	static int level = 0;

	/**
	 * Print a, possibly indented, string.
	 */
	public static void pr(String s) {
		if (showStructure)
			System.out.print(indentStr.substring(0, level * 2));
		System.out.println(s);
	}
}
