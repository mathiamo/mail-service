package no.ciber.controller

import no.ciber.service.MailService
import no.ciber.util.ConversionUtil
import spark.{Route, Response, Request}

/**
 * Created by bjerke.
 */
object Controller {
    def test = (request: Request, response: Response) => ""


    def sendMail: Route = {
        new Route {
            override def handle(request: Request, response: Response): AnyRef = {
                try {
                    MailService sendMail ConversionUtil.convertJsonToMail(request.body)
                    response status 200
                    "{\"status\": \"success\"}"
                }
                catch {
                    case e: Exception =>
                        response status 500
                        "{\"error\": \"$e.getMessage\"}"
                }
            }
        }
    }

    def index: Route = {
        new Route {
            override def handle(request: Request, response: Response): AnyRef = {
                s"""
                   |{
                   |  "api-name": "mail-service",
                   |  "endpoints": [
                   |      {
                   |           "name": "sendMail",
                   |           "endpoint": "/sendMail",
                   |           "method": "POST",
                   |           "description": "Accepts a json payload with a mail object and uses an SMTP server to send it"
                   |      }
                   |  ]
                   |}
                """.stripMargin
            }
        }
    }
}
