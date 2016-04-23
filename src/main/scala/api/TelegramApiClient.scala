package api

import org.json4s.DefaultFormats
import org.json4s.JsonAST.{JNothing, JObject, JValue}

import scala.concurrent.{Await, ExecutionContext, Future}
import scalaj.http.{Http, HttpRequest, HttpResponse}
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._
import org.json4s.native.Serialization
import org.slf4j.{Logger, LoggerFactory}
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

class TelegramApiClient(url: String)(implicit context: ExecutionContext) {
  type Updates = List[Update]
  implicit val jsonFormats = DefaultFormats

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  val connTimeout: Int = 1000
  val readTimeout: Int = 10000

  def proceedRequest[T: Manifest](method: String, args: (String, Any)*) = {

    logger.debug(s"$method, $args")
    val response = httpRequest(method, args:_*)

    response.map(parseResponse[T](_))
  }

  def parseResponse[T: Manifest](r: String):T = {
      parse(r).\("result").camelizeKeys.extract[T]
  }

  def httpRequest(requestUrl: String, params: (String, Any)*):Future[String] = Future {

//      .postData(data)
//      .asString.body
    val req = params.foldLeft(Http(requestUrl)
      .header("content-type", "application/json")
      .timeout(connTimeout, readTimeout))((r, p)=> p match {
      case (id, value) => value match {
        case None => r
        case Some(v) => r.param(id, v.toString)
        case v => r.param(id, v.toString)
    }})

    req.asString.body
  }


  private object MethodsUrls {
    val getUpdates = s"$url/getUpdates"
    val setWebHook = s"$url/setWebhook"
    val getMe = s"$url/getMe"
    val sendMessage = s"$url/sendMessage"
    val forwardMessage = s"$url/forwardMessage"
  }

  /**
    * A simple method for testing your bot's auth token. Requires no parameters. Returns basic information about the bot in form of a User object.
    * @return Returns basic information about the bot in form of a User object
    */

  def getMe:Future[User] = proceedRequest[User](MethodsUrls.getMe)

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

    proceedRequest[Message](MethodsUrls.sendMessage, "chat_id" -> chatId.id,
      "text" -> text,
      "parse_mode" -> parseMode,
      "disable_web_page_preview" -> disableWebPagePreview,
      "disable_notification" -> disableNotification,
      "reply_to_message_id" -> replyToMessageId)
  }

  def getUpdates(offset: Option[Int] = None,
                 limit: Option[Int] = None,
                 timeout: Option[Int] = None):Future[Updates] =
    proceedRequest[Updates](MethodsUrls.getUpdates,
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

  def forwardMessage(chatId: ChatId, fromChatId:ChatId, disableNotification:Option[Boolean]=None, messageId:Int) =
    proceedRequest[Message](MethodsUrls.forwardMessage,
      "chat_id"->chatId.id,
      "from_chat_id"->fromChatId.id,
      "disable_notifications"->disableNotification,
      "message_id"->messageId)

  //  def (chatId: ChatId):Future[]
}
