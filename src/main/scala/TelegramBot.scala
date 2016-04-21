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


    val json = Serialization.write(params)
    println(json)
    val response = parse(Http(method).header("content-type", "application/json")
                            .postData(compact(render(json)))
                            .asString.body
                        )
    (response\"result").camelizeKeys.extract[T]
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
                 ): Unit = {
    proceedRequest(ApiMethods.sendMessage, ("chat_id", chatId), ("text", text))
  }
}

object TestBot extends TelegramBot("------put your token here------") with LongPolling {

  def main(args: Array[String]) {
    run()
  }

  override def handleUpdate(update: Update) = Future {
    println("handleUpdate: " + update)
//    println(update)
    if (update.message.get.text.get.equals("656")) {
      sendMessage(update.message.get.chat.id, "Hello!")
//      println(update.message.get.chat.firstName)
    }
    Thread.sleep(2000)
  }
}

