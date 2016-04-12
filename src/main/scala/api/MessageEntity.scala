package api

/**
  * Created by artemypestretsov on 4/12/16.
  *
  * MessageEntity
  *
  * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
  *
  * @param type	     String. Type of the entity. One of mention (@username), hashtag, bot_command, url, email, bold (bold text), italic (italic text), code (monowidth string), pre (monowidth block), text_link (for clickable text URLs)
  * @param offset    Integer. Offset in UTF-16 code units to the start of the entity
  * @param length 	 Integer. Length of the entity in UTF-16 code units
  * @param url       String. Optional. For “text_link” only, url that will be opened after user taps on the text
  */

case class MessageEntity(`type`: String,
                         offset: Int,
                         length: Int,
                         url: Option[String] = None
                        )
