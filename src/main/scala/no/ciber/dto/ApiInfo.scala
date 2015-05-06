package no.ciber.dto

/**
 * Created by bjerke.
 */
case class ApiInfo(name: String, endpoints: List[Endpoint]) {
    def toJson: String = {
        val sb = new StringBuilder
        sb.append("{")
        sb.append(s""""name": "$name"""")
        if(endpoints != null || endpoints.nonEmpty){
            sb.append(",")
            sb.append("\"endpoints\": [")
            endpoints.foreach(endpoint => {
                    sb.append(endpoint.toJson)
                    if(endpoints.last != endpoint) sb.append(",")
            })
            sb.append("]")
        }
        sb.append("}")
        sb.toString()
    }
}
