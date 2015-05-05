package no.ciber.util

import java.util.Base64
import javax.activation.DataHandler
import javax.mail.internet.{MimeBodyPart, MimeMultipart, InternetAddress, MimeMessage}
import javax.mail.util.ByteArrayDataSource
import javax.mail.{MessagingException, Address, Session, Message}

import com.google.gson.Gson
import no.ciber.dto.{Attachment, Mail}

/**
 * Created by bjerke.
 */
object ConversionUtil {
    def convertJsonToMail(json: String): Mail = {
        new Gson().fromJson(json, classOf[Mail])
    }

    @throws(classOf[MessagingException])
    def convertMailToMessage(mail: Mail, session: Session): Message = {
        val message: Message = new MimeMessage(session)
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.toRecipients mkString ",").asInstanceOf[Array[Address]])
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mail.ccRecipients mkString ",").asInstanceOf[Array[Address]])
        message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mail.bccRecipients mkString ",").asInstanceOf[Array[Address]])
        message.setSubject(mail.subject)
        message.setText(mail.body)
        message.setContent(convertAttachments(mail.attachments))
        message
    }

    @throws(classOf[MessagingException])
    private def convertAttachments(attachments: List[Attachment]) = {
        val multipart = new MimeMultipart
        val decoder = Base64.getDecoder

        attachments.foreach(attachment => {
            val data = decoder.decode(attachment.encodedFile)
            val messageBodyPart = new MimeBodyPart()
            val source = new ByteArrayDataSource(data, attachment.mimeType)
            messageBodyPart.setDataHandler(new DataHandler(source))
            messageBodyPart.setFileName(attachment.filename)
            multipart.addBodyPart(messageBodyPart)
        })
        multipart
    }
}
