import api.Update
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import scalaj.http._
import asynchttp.AsyncHttp._
import org.json4s.JsonAST.JValue

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

  }

  def handleUpdate(update: Update)

  def proceedRequest[T: Manifest](method: String, vals: Int): T = {
    implicit val formats = DefaultFormats
    val response = parse(Http(method).header("content-type", "application/json")
                            .postData(compact(render(("offset" -> vals))))
                            .asString.body
                        )
    (response\"result").camelizeKeys.extract[T]
  }

  def getUpdates(offset: Option[Int] = None,
                 limit: Option[Int] = None,
                 timeout: Option[Int] = None): Future[Array[Update]] = Future {
    proceedRequest[Array[Update]](ApiMethods.getUpdates, offset.get)
  }
}

object TestBot extends TelegramBot("------put your token here------") with LongPolling {

  def main(args: Array[String]) {
    run()
  }

  override def handleUpdate(update: Update) = {
    println(update)
  }
}

