package api

/**
  * Created by artemypestretsov on 4/12/16.
  */

trait InputMessageContent

case class InputTextMessageContent() extends InputMessageContent
case class InputLocationMessageContent() extends InputMessageContent
case class InputVenueMessageContent() extends InputMessageContent
case class InputContactMessageContent() extends InputMessageContent