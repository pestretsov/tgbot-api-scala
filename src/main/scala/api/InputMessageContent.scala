package api

/**
  * Created by artemypestretsov on 4/12/16.
  */

trait InputMessageContent


/**
  * InputTextMessageContent
  *
  * Represents the content of a text message to be sent as the result of an inline query.
  *
  * @param messageText              String. Text of the message to be sent, 1-4096 characters
  * @param parseMode                String. Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
  * @param disableWebPagePreview    Boolean. Optional. Disables link previews for links in the sent message
  */

case class InputTextMessageContent(
                                    messageText            : String,
                                    parseMode              : Option[String] = None,
                                    disableWebPagePreview  : Option[Boolean] = None
                                  ) extends InputMessageContent


/**
  * InputLocationMessageContent
  *
  * Represents the content of a location message to be sent as the result of an inline query.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  *
  * @param latitude     Float. Latitude of the location in degrees
  * @param longitude    Float. Longitude of the location in degrees
  */

case class InputLocationMessageContent(
                                        latitude   : Float,
                                        longitude  : Float
                                      ) extends InputMessageContent


/**
  * InputVenueMessageContent
  *
  * Represents the content of a venue message to be sent as the result of an inline query.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  *
  * @param latitude        Float. Latitude of the venue in degrees
  * @param longitude       Float. Longitude of the venue in degrees
  * @param title           String. Name of the venue
  * @param address         String. Address of the venue
  * @param foursquareId    String. Optional. Foursquare identifier of the venue, if known
  */

case class InputVenueMessageContent(
                                     latitude      : Float,
                                     longitude     : Float,
                                     title         : String,
                                     address       : String,
                                     foursquareId  : Option[String] = None
                                   ) extends InputMessageContent


/**
  * InputContactMessageContent
  *
  * Represents the content of a contact message to be sent as the result of an inline query.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  *s
  * @param phoneNumber    String. Contact's phone number
  * @param firstName      String. Contact's first name
  * @param lastName       String. Optional. Contact's last name
  */

case class InputContactMessageContent(
                                       phoneNumber  : String,
                                       firstName    : String,
                                       lastName     : Option[String] = None
                                     ) extends InputMessageContent