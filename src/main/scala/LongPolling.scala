import api.Update

import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits._

/**
  * Created by artemypestretsov on 4/11/16.
  */
trait LongPolling extends Runnable { this : TelegramBot =>

  override def run {

    var updateOffset = 0

    while (true) {

      println("loop: " + Thread.currentThread().getName)

      val updatesFuture = getUpdates(offset = Some(updateOffset))

      for (u <- updatesFuture) {
        for (update <- u) {
          updateOffset = updateOffset max update.updateId+1
          handleUpdate(update)
        }
      }
      println(updateOffset)

//      updatesFuture onSuccess {
//        case Array() =>
//        case updates =>
//          for (u <- updates) {
//            updateOffset = updateOffset max u.updateId+1
//            println("updatesLoop: " + Thread.currentThread().getName)
//            handleUpdate(u)
//          }
//      }
      updatesFuture onFailure {
        case e => println("Error" + e.getMessage)
      }

      Thread.sleep(1500)
    }
  }
}
