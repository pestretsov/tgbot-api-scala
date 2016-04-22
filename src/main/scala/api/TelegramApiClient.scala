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
    val sendMessage = s"$url/sendMessage"
    val getMe = s"$url/getMe"
  }

  def getMe:Future[User] = proceedRequest[User](MethodsUrls.getMe)
//
  def sendMessage(chatId: Int, text: String,
                  parseMode: Option[String] = None,
                  disableWebPagePreview: Option[Boolean] = None,
                  disableNotification: Option[Boolean] = None,
                  replyToMessageId: Option[Int] = None
                 ):Future[Message] = {

    proceedRequest[Message](MethodsUrls.sendMessage, "chat_id" -> chatId,
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
}
