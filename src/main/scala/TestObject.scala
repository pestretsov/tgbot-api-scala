import api.Update
import telegrambot.{LongPolling, TelegramBot}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by artemypestretsov on 4/9/16.
  */

object TestObject {
  val TOKEN = "202940406:AAEHH9lxiHD9ezWESjz8DWuNrNt7zkfJjT8"
  object MyBot extends TelegramBot(TOKEN) with LongPolling {
    override def handleUpdate(u: Update) = Future {
      u.message.foreach{
        case m=> Client.sendMessage(m.chat.id, "Hello!")
      }
    }
  }


  def main(args: Array[String]) {
    MyBot.run()
    println("Bot is up and Running!")
    while (true) {
      Thread.sleep(1000)
    }
  }
}