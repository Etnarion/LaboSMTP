package protocol;

/**
 * Protocol used when communicating with a SMTP server
 */
public class SMTPProtocol {
    public static final String SMTP_MAIL = "MAIL FROM:";
    public static final String SMTP_EHLO = "EHLO";
    public static final String SMTP_RCPT = "RCPT TO:";
    public static final String SMTP_DATA = "DATA";
    public static final String SMTP_DATA_FROM = "From:";
    public static final String SMTP_DATA_TO = "To:";
    public static final String SMTP_DATA_CC = "Cc:";
    public static final String SMTP_DATA_SUBJECT = "Subject:";
    public static final String SMTP_DATA_END = "\r\n.\r\n";
    public static final String SMTP_QUIT = "quit";
    public static final String SMTP_OK = "250 Ok";
}
