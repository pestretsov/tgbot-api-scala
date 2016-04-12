package api

/**
  * Created by artemypestretsov on 4/12/16.
  */

trait InlineQueryResult

/**
  * InlineQueryResultArticle
  *
  * Represents a link to an article or web page.
  *
  * @param type	                  String. Type of the result, must be article
  * @param id	                    String. Unique identifier for this result, 1-64 Bytes
  * @param title	                String. Title of the result
  * @param inputMessageContent    InputMessageContent. Content of the message to be sent
  * @param replyMarkup            InlineKeyboardMarkup. Optional. Inline keyboard attached to the message
  * @param url                    String. Optional. URL of the result
  * @param hideUrl                Boolean. Optional. Pass True, if you don't want the URL to be shown in the message
  * @param description            String. Optional. Short description of the result
  * @param thumbUrl               String. Optional. Url of the thumbnail for the result
  * @param thumbWidth             Integer. Optional. Thumbnail width
  * @param thumbHeight            Integer. Optional. Thumbnail height
  */

case class InlineQueryResultArticle(
                                     `type`               : String,
                                     id                   : String,
                                     title                : String,
                                     inputMessageContent  : InputMessageContent,
                                     replyMarkup          : Option[InlineKeyboardMarkup] = None,
                                     url                  : Option[String] = None,
                                     hideUrl              : Option[Boolean] = None,
                                     description          : Option[String] = None,
                                     thumbUrl             : Option[String] = None,
                                     thumbWidth           : Option[Int] = None,
                                     thumbHeight          : Option[Int] = None
                                   ) extends InlineQueryResult
/**
  * InlineQueryResultPhoto
  *
  * Represents a link to a photo. By default, this photo will be sent by the user with optional caption. Alternatively, you can use input_message_content to send a message with the specified content instead of the photo.*
  *
  * @param type                 	String. Type of the result, must be photo
  * @param id	                    String. Unique identifier for this result, 1-64 bytes
  * @param photoUrl             	String. A valid URL of the photo. Photo must be in jpeg format. Photo size must not exceed 5MB
  * @param thumbUrl	              String. URL of the thumbnail for the photo
  * @param photoWidth	            Integer. Optional. Width of the photo
  * @param photoHeight          	Integer. Optional. Height of the photo
  * @param title	                String. Optional. Title for the result
  * @param description	          String. Optional. Short description of the result
  * @param caption	              String. Optional. Caption of the photo to be sent, 0-200 characters
  * @param replyMarkup            InlineKeyboardMarkup. Optional. Inline keyboard attached to the message
  * @param inputMessageContent    InputMessageContent. Optional. Content of the message to be sent instead of the photo
  */

case class InlineQueryResultPhoto(
                                   `type`               : String,
                                   id                   : String,
                                   photoUrl             : String,
                                   thumbUrl             : String,
                                   photoWidth           : Option[Int] = None,
                                   photoHeight          : Option[Int] = None,
                                   title                : Option[String] = None,
                                   description          : Option[String] = None,
                                   caption              : Option[String] = None,
                                   replyMarkup          : Option[InlineKeyboardMarkup] = None,
                                   inputMessageContent  : Option[InputMessageContent] = None
                                 ) extends InlineQueryResult


case class InlineQueryResultGif(

                                   ) extends InlineQueryResult


case class InlineQueryResultMpeg4Gif(

                                   ) extends InlineQueryResult


case class InlineQueryResultVideo(

                                   ) extends InlineQueryResult


case class InlineQueryResultAudio(

                                   ) extends InlineQueryResult

case class InlineQueryResultVoice(

                                 ) extends InlineQueryResult

case class InlineQueryResultDocument(

                                 ) extends InlineQueryResult

case class InlineQueryResultLocation(

                                 ) extends InlineQueryResult


case class InlineQueryResultVenue(

                                 ) extends InlineQueryResult



case class InlineQueryResultContact(

                                 ) extends InlineQueryResult


case class InlineQueryResultCachedPhoto(

                                 ) extends InlineQueryResult


case class InlineQueryResultCachedGif(

                                 ) extends InlineQueryResult


case class InlineQueryResultCachedMpeg4Gif(

                                 ) extends InlineQueryResult

case class InlineQueryResultCachedSticker(

                                          ) extends InlineQueryResult


case class InlineQueryResultCachedDocument(

                                          ) extends InlineQueryResult


case class InlineQueryResultCachedVideo(

                                          ) extends InlineQueryResult



case class InlineQueryResultCachedVoice(

                                          ) extends InlineQueryResult


case class InlineQueryResultCachedAudio(

                                          ) extends InlineQueryResult



