package no.ciber.service

import java.util.Properties
import javax.mail._

import no.ciber.dto.Mail
import no.ciber.util.ConversionUtil
import org.slf4j.LoggerFactory

/**
 * Created by bjerke.
 */
object MailService {

    private val log = LoggerFactory.getLogger(MailService.getClass)

    def sendMail(mail: Mail) = {

        val session: Session = Session.getInstance(getProperties, authenticator)

        try {
            Transport.send(ConversionUtil.convertMailToMessage(mail, session))
            log.info("Sent mail with \"subject\" $mail.subject to recipients: \"$toRecipients\"")
        }
        catch {
            case e: MessagingException => throw new RuntimeException(e)
        }
    }

    private def getProperties: Properties = {
        val properties: Properties = new Properties
        properties put("mail.smtp.host", "smtp.gmail.com")
        properties put("mail.smtp.socketFactory.port", "465")
        properties put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        properties put("mail.smtp.auth", "true")
        properties put("mail.smtp.port", "465")
        properties
    }

    def authenticator: Authenticator = {
        new Authenticator {
            override def getPasswordAuthentication: PasswordAuthentication = {
                new PasswordAuthentication(System.getenv("SMTP_USERNAME"), System.getenv("SMTP_PASSWORD"))
            }
        }
    }
}
