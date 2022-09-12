package com.solvd.educ8.demo.utils;

import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class EmailManager {
    protected static final Logger LOGGER = LoggerFactory.getLogger(EmailManager.class);
    public final static int DEFAULT_NOTIFICATION_TIMEOUT = 60;
    public final static int DEFAULT_EMAILS_COUNT_TO_RETRIEVE = 1;
    private static Session session;

    private static Properties initProps() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "imap.gmail.com");
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.imap.socketFactory.fallback", "false");
        return props;
    }

    private static synchronized EmailMsg[] getInbox(final String email, final String pwd, int lastEmailsCount) {
        // open email and get inbox
        if (session == null) {
            Properties props = initProps();
            session = Session.getInstance(props, null);
        }
        Store store = null;
        try {
            // open session
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", email, pwd);
            // get inbox folder
            Folder inboxFolder = store.getFolder("inbox");
            inboxFolder.open(Folder.READ_ONLY);
            if (inboxFolder.isOpen()) {
                Message[] messages = inboxFolder.getMessages();
                EmailMsg[] finalMessages = new EmailMsg[0];
                int startIndex = messages.length - 1;
                int endIndex = (messages.length > lastEmailsCount) ? (messages.length - lastEmailsCount) : 0;
                for (int i = startIndex; i >= endIndex; i--) {
                    String content = "";
                    String contentSrc = "";
                    try {
                        content = ((MimeMultipart) messages[i].getContent()).getBodyPart(0).getContent().toString();
                        contentSrc = ((MimeMultipart) messages[i].getContent()).getBodyPart(1).getContent().toString();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        content = ((MimeMultipart) messages[i].getContent()).getBodyPart(0).getContent().toString();
                    } catch (ClassCastException e) {
                        content = messages[i].getContent().toString();
                    }
                    finalMessages = (EmailMsg[]) ArrayUtils.add(finalMessages, new EmailMsg(messages[i].getSubject(),
                            messages[i].getFrom().toString(), messages[i].getReceivedDate(), content, contentSrc));
                }
                inboxFolder.close(false);
                store.close();
                return finalMessages;
            } else {
                store.close();
                throw new RuntimeException("Can't retrieve Inbox emails. Inbox folder isn't opened");
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't retrieve Inbox emails", e);
        } finally {
            if (store != null) {
                try {
                    store.close();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // help method to verify INBOX email contains new mail
    public static synchronized void waitForEmailDelivered(WebDriver driver, final Date startTestTime, final UserData user,
                                                          final String emailTitle, final String emailBodyFragment) {
        Wait<WebDriver> waiter = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(DEFAULT_NOTIFICATION_TIMEOUT));
        try {
            waiter.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    LOGGER.info("Expected email title: " + emailTitle);
                    LOGGER.info("Expected body fragment: " + emailBodyFragment);
                    LOGGER.info("----------------------New round of emails retrieving--------------------------");
                    EmailMsg[] messages = EmailManager.getInbox(user.getEmail(), user.getMailPassword(), DEFAULT_EMAILS_COUNT_TO_RETRIEVE);
                    for (EmailMsg msg : messages) {
                        LOGGER.info(String.format("Retrieved msg title is %s and time is " + msg.getTime(), msg.getSubject()));
                        if ((msg.getTime().compareTo(startTestTime) > 0)
                                && (emailTitle.equals(msg.getSubject()) && (msg.getContent().contains(emailBodyFragment)))) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            LOGGER.info("Email '" + emailTitle + "' with proper delivery date found in Inbox");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    String.format("Email '%s' not delivered after %d sec waiting", emailTitle, DEFAULT_NOTIFICATION_TIMEOUT));
        }
    }

    public static synchronized EmailMsg readEmail(WebDriver driver, final Date startTestTime, final UserData user, final String emailTitle,
                                                  final String emailBodyFragment) {
        waitForEmailDelivered(driver, startTestTime, user, emailTitle, emailBodyFragment);
        EmailMsg[] messages = EmailManager.getInbox(user.getEmail(), user.getMailPassword(), DEFAULT_EMAILS_COUNT_TO_RETRIEVE);
        for (EmailMsg msg : messages) {
            if ((msg.getTime().compareTo(startTestTime) > 0)
                    && (emailTitle.equals(msg.getSubject()) && (msg.getContent().contains(emailBodyFragment)))) {
                return msg;
            }
        }
        throw new RuntimeException("Expected email '" + emailTitle + "' not found in Inbox");
    }
}