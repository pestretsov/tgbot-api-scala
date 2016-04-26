package telegrambot

/**
  * Created by roman on 12.04.2016.
  */
trait LongPolling { this:TelegramBot =>

  val pollTimeout = 2 some
  val pollLimit = 100 some

  def startPoll(pollOffset:Option[Int]=None):Unit = {
    val poll = Client.getUpdates(pollOffset, pollLimit, pollTimeout)

    poll.foreach{updates =>
      val newOffset = updates match {
        case List() => None
        case list => (list.map(_.updateId).max+1) some
      }

      updates.foreach(handleUpdate _ )

      startPoll(newOffset)
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
