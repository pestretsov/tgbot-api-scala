package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * Location
  *
  * This object represents a point on the map.
  *
  * @param longitude    Float. Longitude as defined by sender
  * @param latitude	    Float. Latitude as defined by sender
  */

case class Location(
                     longitude: Float,
                     latitude: Float
                   )
