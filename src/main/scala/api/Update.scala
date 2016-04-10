package api

/**
  * Update
  *
  * This object represents an incoming update.
  * Only one of the optional parameters can be present in any given update.
  **
  *
  * @param updateId             Integer
  * @param message              API.Message           	Optional
  * @param inlineQuery          API.InlineQuery	        Optional
  * @param chosenInlineResult   API.ChosenInlineResult	Optional
  *
 */

case class Update(updateId: Int,
                  message: Option[Message] = None,
                  inlineQuery: Option[InlineQuery] = None,
                  chosenInlineResult: Option[ChosenInlineResult] = None
                 )
{
  override def toString: String = {
    return updateId.toString + " " + message.toString
  }
}
