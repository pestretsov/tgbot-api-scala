import api.{ReplyMarkup, Update}
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import scalaj.http._
import asynchttp.AsyncHttp._
import org.json4s.JsonAST.{JObject, JValue}
import org.json4s.native.Serialization

import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * Created by artemypestretsov on 4/9/16.
  */

abstract class TelegramBot(token: String) {
  object ApiMethods {
    val url: String = s"https://api.telegram.org/bot$token"

    val getUpdates: String = s"$url/getUpdates"
    val setWebhook: String = s"$url/setWebhook"
    val sendMessage: String = s"$url/sendMessage"
  }

  def handleUpdate(update: Update)


  def proceedRequest[T: Manifest](method: String, params: (String, Any)*): T = {
    implicit val formats = DefaultFormats

    val request = params.foldLeft(Http(method).header("content-type", "application/json")) {
      case (req, (name, value)) => value match {
        case Some(v) => req.param(name, v.toString)
        case None => req
        case _ => req.param(name, value.toString)
      }
    }

    val response = request.asString
    if (response.isSuccess) {
      val json = parse(response.body)
      (json \ "result").camelizeKeys.extract[T]
    } else {
      throw new Exception
    }
  }

  def getUpdates(offset: Option[Int] = None,
                 limit: Option[Int] = None,
                 timeout: Option[Int] = None): Future[Array[Update]] = Future {

    proceedRequest[Array[Update]](ApiMethods.getUpdates, ("offset", offset), ("limit", limit), ("timeout", timeout))
  }

  def sendMessage(
                   chatId:                   Int,
                   text:                     String,
                   parseMode:                Option[String] = None,
                   disableWebPagePreview:    Option[Boolean] = None,
                   disableNotification:      Option[Boolean] = None,
                   replyToMessageId:         Option[Integer] = None,
                   replyMarkup:              Option[ReplyMarkup] = None
                 ): Unit = Future {

    proceedRequest(ApiMethods.sendMessage,
      ("chat_id", chatId), ("text", text), ("parse_mode", parseMode), ("disable_web_page_preview", disableWebPagePreview),
      ("disable_notification", disableNotification), ("reply_to_message_id", replyToMessageId), ("reply_markup", replyMarkup))
  }
}

object TestBot extends TelegramBot("102590032:AAFPwqwxkce-mhNQGzZa_2kSytegVg0m6BQ") with LongPolling {

  def main(args: Array[String]) {
    run()
  }

  override def handleUpdate(update: Update) = Future {
    println("handleUpdate: " + update.updateId)
//    println("handleUpdate: " + update)
////    println(update)
    if (update.message.get.text.get.equals("656")) {
      sendMessage(update.message.get.chat.id, "Hello!")
//      println(update.message.get.chat.firstName)
    }
//    Thread.sleep(2000)
  }
}

