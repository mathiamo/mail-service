package no.ciber.dto

/**
 * Created by bjerke.
 */
class Mail(val subject: String,
           val body: String,
           val toRecipients: List[String],
           val ccRecipients: List[String],
           val bccRecipients: List[String],
           val attachments: List[Attachment])
