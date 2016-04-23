package telegrambot

import api.{TelegramApiClient, Update, User}
import com.typesafe.scalalogging.Logger
import org.json4s.DefaultFormats
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by roman on 12.04.2016.
  */
abstract class TelegramBot(TOKEN : String) (implicit val context: ExecutionContext) extends Runnable {
  lazy val logger = Logger(LoggerFactory.getLogger(this.getClass.getSimpleName))

  val apiUrl = s"https://api.telegram.org/bot$TOKEN"

  object Client extends TelegramApiClient(apiUrl)

  implicit val jsonFormats = DefaultFormats

  def handleUpdate(u: Update): Future[Any]
}
