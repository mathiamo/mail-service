package no.ciber.util

import spark.{Response, Request, Filter}

/**
 * Created by bjerke.
 */
object FilterUtil {
    def applicationJsonFilter: Filter = {
        new Filter {
            override def handle(request: Request, response: Response) = response.`type`("application/json")
        }
    }
}
