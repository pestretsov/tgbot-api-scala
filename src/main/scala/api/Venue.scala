package api

/**
  * Created by artemypestretsov on 4/12/16.
  *
  * Venue
  *
  * This object represents a venue.
  *
  * @param location            Location. Venue location
  * @param title               String. Name of the venue
  * @param address             String. Address of the venue
  * @param foursquareId        String. Optional. Foursquare identifier of the venue
  */

case class Venue(location: Location,
                 title: String,
                 address: String,
                 foursquareId: Option[String] = None
                )
