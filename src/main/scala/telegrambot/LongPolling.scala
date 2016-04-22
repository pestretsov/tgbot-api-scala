package telegrambot

import java.util.concurrent.TimeoutException

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._
import api.Update
import asynchttp.AsyncHttp._

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scalaj.http.Http
import scala.concurrent.duration._
/**
  * Created by roman on 12.04.2016.
  */
trait LongPolling { this:TelegramBot =>

  implicit class OptionPimp[T](t:T){
    def some: Option[T] = Some(t)
  }

  val pollTimeout = 3 some
  val pollLimit = 100 some
  var pollOffset:Option[Int] = Some(0)

  def startPoll():Unit = {
    val poll = Client.getUpdates(pollOffset, pollLimit, pollTimeout)

    poll.foreach{updates =>
      pollOffset = updates match {
        case List() => pollOffset
        case list => (list.map(_.updateId).max+1).some
      }

      updates.foreach(handleUpdate _ )

      startPoll()
    }

    poll.onFailure{
      case _ => startPoll()
    }
  }

  def run():Unit = {
    startPoll
  }
}
