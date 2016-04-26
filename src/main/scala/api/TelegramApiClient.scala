package api

import java.io.FileInputStream
import java.nio.file.Files

import org.slf4j.LoggerFactory

import org.json4s._
import org.json4s.native.JsonMethods._

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.{Http, MultiPart}
/**
  * Created by roman on 15.04.2016.
  */

case class ChatId(id: Any) {
  override def toString = {id.toString}
}
object ChatId {
  implicit def int2chatId(id: Int):ChatId = ChatId(id)
  implicit def string2chatId(id: String):ChatId = ChatId(id)
}

case class ClientFile(file: Any)
case object ClientFile {
  implicit def string2chatId(id: String): ClientFile = ClientFile(id)
  implicit def inputFile2chatId(file: InputFile): ClientFile = ClientFile(file)
}

object JsonUtils {
  import org.json4s.NoTypeHints
  import org.json4s.native.Serialization
  import org.json4s.native.Serialization.write

  implicit val formats = Serialization.formats(NoTypeHints)
  def serialize[T<:AnyRef](t: T):String = {
    write(t)
  }
}

class TelegramApiClient(url: String)(implicit context: ExecutionContext) {
  type Updates = List[Update]

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  implicit val formats = DefaultFormats

  val connTimeout: Int = 1000
  val readTimeout: Int = 10000

  def proceedRequest[T: Manifest](method: String, args: (String, Any)*) = {

    logger.debug(s"$method, $args")
    val response = httpRequest(method, args:_*)

    response.map(parseResponse[T](_))
  }

  class ApiCallError(s: String) extends Exception(s)
  def parseResponse[T: Manifest](r: String):T = {
    if (parse(r).\("ok").extract[Boolean]) {
      parse(r).\("result").camelizeKeys.extract[T]
    } else throw new ApiCallError(s"Bad request: $r")
  }

  def httpRequest(method: String, params: (String, Any)*):Future[String] = Future {

//      .postData(data)
//      .asString.body
    val req = params.foldLeft(Http(s"$url/$method")
      .timeout(connTimeout, readTimeout))((r, p)=> p match {
      case (id, value) => value match {
        case f:InputFile =>
          val stream = new FileInputStream(f.file)
          val bytes = Files.readAllBytes(f.file.toPath)

          r.postMulti(MultiPart(id, f.filename, "image/jpeg", bytes))
        case None => r
        case Some(v) => r.param(id, v.toString)
        case v => r.param(id, v.toString)
    }})
    req.asString.body
  }


  private object Methods {
    val getUpdates = "getUpdates"

    val setWebHook = "setWebhook"

    val getMe = "getMe"

    val sendMessage = "sendMessage"
    val forwardMessage = "forwardMessage"

    val sendPhoto = "sendPhoto"
    val sendAudio = "sendAudio"
    val sendDocument = "sendDocument"
    val sendSticker = "sendSticker"
    val sendVideo = "sendVideo"
    val sendVoice = "sendVoice"
  }

  /**
    * A simple method for testing your bot's auth token. Requires no parameters. Returns basic information about the bot in form of a User object.
    *
    * @return Returns basic information about the bot in form of a User object
    */

  def getMe:Future[User] = proceedRequest[User](Methods.getMe)

  /**
    * Use this method to send text messages. On success, the sent Message is returned.
    *
    * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param text Text of the message to be sentSend Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
    * @param parseMode Disables link previews for links in this message
    * @param disableWebPagePreview Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
    * @param disableNotification If the message is a reply, ID of the original message.
    * @param replyToMessageId Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
    * @return On success, the sent Message is returned
    */

  def sendMessage(chatId: ChatId, text: String,
                  parseMode: Option[String] = None,
                  disableWebPagePreview: Option[Boolean] = None,
                  disableNotification: Option[Boolean] = None,
                  replyToMessageId: Option[Int] = None
                 ):Future[Message] = {

    proceedRequest[Message](Methods.sendMessage, "chat_id" -> chatId.id,
      "text" -> text,
      "parse_mode" -> parseMode,
      "disable_web_page_preview" -> disableWebPagePreview,
      "disable_notification" -> disableNotification,
      "reply_to_message_id" -> replyToMessageId)
  }

  def getUpdates(offset: Option[Int] = None,
                 limit: Option[Int] = None,
                 timeout: Option[Int] = None):Future[Updates] =
    proceedRequest[Updates](Methods.getUpdates,
      "offset" -> offset,
      "timeout" -> timeout,
      "limit" -> limit)

  /**
    * Use this method to forward messages of any kind. On success, the sent Message is returned.
    *
    * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param fromChatId 	Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
    * @param disableNotification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
    * @param messageId Unique message identifier
    * @return On success, the sent Message is returned.
    */

  def forwardMessage(chatId: ChatId,
                     fromChatId:ChatId,
                     disableNotification:Option[Boolean]=None,
                     messageId:Int) =
    proceedRequest[Message](Methods.forwardMessage,
      "chat_id"->chatId.id,
      "from_chat_id"->fromChatId.id,
      "disable_notifications"->disableNotification,
      "message_id"->messageId)

  /**
    * Use this method to send photos. On success, the sent Message is returned.
    *
    * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
    * @param caption Photo caption (may also be used when resending photos by file_id), 0-200 characters.
    * @param disableNotification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
    * @param replyToMessageId If the message is a reply, ID of the original message
    * @param replyMarkup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
    * @return On success, the sent Message is returned.
    */

  def sendPhoto(chatId: ChatId,
                photo: ClientFile,
                caption: Option[String]=None,
                disableNotification:Option[Boolean]=None,
                replyToMessageId:Option[Int]=None,
                replyMarkup:Option[ReplyMarkup]=None) = {
    proceedRequest[Message](Methods.sendPhoto,
      "chat_id"->chatId.id,
      "photo"->photo.file,
      "caption"->caption,
      "disable_notification"->disableNotification,
      "reply_to_message_id"->replyToMessageId,
      "reply_markup"->JsonUtils.serialize(replyMarkup))

  }

  /**
    * Use this method to send audio files, if you want Telegram clients to display them in the music player.
    * Your audio must be in the .mp3 format. On success, the sent Message is returned.
    * Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
    *
    * For sending voice messages, use the sendVoice method instead.
    *
    * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param audio Audio file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
    * @param duration Duration of the audio in seconds
    * @param performer Performer
    * @param title Track name
    * @param disableNotification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
    * @param replyToMessageId If the message is a reply, ID of the original message
    * @param replyMarkup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
    * @return On success, the sent Message is returned.
    */
  def sendAudio(chatId: ChatId,
                audio: ClientFile,
                duration: Option[Int]=None,
                performer: Option[String]=None,
                title: Option[String]=None,
                disableNotification:Option[Boolean]=None,
                replyToMessageId:Option[Int]=None,
                replyMarkup:Option[ReplyMarkup]=None
               ) =
    proceedRequest[Message](Methods.sendAudio,
      "chat_id"->chatId.id,
      "audio"->audio.file,
      "duration"->duration,
      "performer"->performer,
      "title"->title,
      "disable_notification"->disableNotification,
      "reply_to_message_id"->replyToMessageId,
      "reply_markup"->JsonUtils.serialize(replyMarkup))

  /**
    * Use this method to send general files. On success, the sent Message is returned.
    * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
    *
    * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param document File to send. You can either pass a file_id as String to resend a file that is already on the Telegram servers, or upload a new file using multipart/form-data.
    * @param caption Document caption (may also be used when resending documents by file_id), 0-200 characters
    * @param disableNotification 	Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
    * @param replyToMessageId If the message is a reply, ID of the original message
    * @param replyMarkup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
    * @return On success, the sent Message is returned.
    */

  def sendDocument(chatId: ChatId,
                   document: ClientFile,
                   caption: Option[String] = None,
                   disableNotification:Option[Boolean]=None,
                   replyToMessageId:Option[Int]=None,
                   replyMarkup:Option[ReplyMarkup]=None
                  ) =
    proceedRequest[Message](Methods.sendDocument,
      "chat_id"->chatId.id,
      "document"->document.file,
      "caption"->caption,
      "disable_notification"->disableNotification,
      "reply_to_message_id"->replyToMessageId,
      "reply_markup"->JsonUtils.serialize(replyMarkup)
    )

  /**
    * Use this method to send .webp stickers. On success, the sent Message is returned.
    *
    * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers, or upload a new sticker using multipart/form-data.
    * @param disableNotification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
    * @param replyToMessageId If the message is a reply, ID of the original message
    * @param replyMarkup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to hide reply keyboard or to force a reply from the user.
    * @return On success, the sent Message is returned.
    */

  def sendSticker(chatId: ChatId,
                  sticker: ClientFile,
                  disableNotification:Option[Boolean]=None,
                  replyToMessageId:Option[Int]=None,
                  replyMarkup:Option[ReplyMarkup]=None
                 ) = {

    proceedRequest[Message](Methods.sendSticker,
      "chat_id" -> chatId.id,
      "sticker" -> sticker.file,
      "disable_notification" -> disableNotification,
      "reply_to_message_id" -> replyToMessageId,
      "reply_markup" -> JsonUtils.serialize(replyMarkup)
    )
  }

  def sendVideo(chatId: ChatId,
                video: ClientFile,
                duration: Option[Int] = None,
                width: Option[Int] = None,
                height: Option[Int] = None,
                caption: Option[String],
                disableNotification:Option[Boolean]=None,
                replyToMessageId:Option[Int]=None,
                replyMarkup:Option[ReplyMarkup]=None
                ) =
    proceedRequest[Message](Methods.sendVideo,
      "chat_id"->chatId.id,
      "video"->video.file,
      "duration"->duration,
      "width"->width,
      "height"->height,
      "caption"->caption,
      "disable_notification" -> disableNotification,
      "reply_to_message_id" -> replyToMessageId,
      "reply_markup" -> JsonUtils.serialize(replyMarkup)
    )

  def sendVoice(chatId: ChatId,
                voice: ClientFile,
                duration: Option[Int]=None,
                disableNotification:Option[Boolean]=None,
                replyToMessageId:Option[Int]=None,
                replyMarkup:Option[ReplyMarkup]=None) =
    proceedRequest[Message](Methods.sendVoice,
      "chat_id"->chatId.id,
      "voice"->voice.file,
      "duration"->duration,
      "disable_notification" -> disableNotification,
      "reply_to_message_id" -> replyToMessageId,
      "reply_markup" -> JsonUtils.serialize(replyMarkup)
    )
  //  def (chatId: ChatId):Future[]
}
