package no.ciber

import no.ciber.controller.Controller
import no.ciber.util.FilterUtil
import org.slf4j.LoggerFactory
import spark.Spark._
import spark.SparkBase._

object Application extends App {

    val log = LoggerFactory.getLogger(Application.getClass)

    try {
        port(System.getenv("PORT").toInt)
        before(FilterUtil.applicationJsonFilter)
        get("/", Controller.index)
        post("/sendMail", Controller.sendMail)
    }
    catch {
        case e: Exception => log.error("Exception during startup:", e)
    }
}