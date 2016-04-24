package telegrambot

/**
  * Created by roman on 12.04.2016.
  */
trait LongPolling { this:TelegramBot =>

  val pollTimeout = 3 some
  val pollLimit = 100 some
  var pollOffset:Option[Int] = 0 some

  def startPoll():Unit = {
    val poll = Client.getUpdates(pollOffset, pollLimit, pollTimeout)

    poll.foreach{updates =>
      pollOffset = updates match {
        case List() => pollOffset
        case list => (list.map(_.updateId).max+1) some
      }

      updates.foreach(handleUpdate _ )

      startPoll()
    }

    poll.onFailure{
      case err => logger.debug("Error while polling", err)
        startPoll()
    }
  }

  def run():Unit = {
    startPoll()
  }
}
