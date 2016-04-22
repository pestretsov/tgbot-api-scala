package asynchttp

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.HttpRequest

/**
  * Created by roman on 12.04.2016.
  */
object AsyncHttp{
  implicit class AsyncHttpRequest(request: HttpRequest) {
    def asFutureString(implicit executionalContext: ExecutionContext) = Future(request.asString)
    def asFutureToken(implicit executionalContext: ExecutionContext) = Future(request.asToken)
    def asFutureParamMap(implicit executionalContext: ExecutionContext) = Future(request.asParamMap)
    def asFutureBytes(implicit executionalContext: ExecutionContext) = Future(request.asBytes)
    def asFutureParams(implicit executionalContext: ExecutionContext) = Future(request.asParams)
  }
}

