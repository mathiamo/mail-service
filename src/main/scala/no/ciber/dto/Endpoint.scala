package no.ciber.dto

/**
 * Created by bjerke.
 */
case class Endpoint(name: String, endpoint: String, method: String, description: String){
    def toJson: String = {
        s"""
           |{
           |    "name": "$name",
           |    "endpoint": "$endpoint",
           |    "method": "$method",
           |    "description": "$description"
           |}
         """.stripMargin
    }
}
