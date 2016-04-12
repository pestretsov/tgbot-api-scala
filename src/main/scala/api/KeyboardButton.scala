package api

/**
  * Created by artemypestretsov on 4/12/16.
  *
  * KeyboardButton
  *
  * This object represents one button of the reply keyboard. For simple text buttons String can be used instead of this object to specify text of the button. Optional fields are mutually exclusive.
  *
  * Note: request_contact and request_location options will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  *
  * @param text                String. Text of the button. If none of the optional fields are used, it will be sent to the bot as a message when the button is pressed
  * @param requestContact     Boolean. Optional. If True, the user's phone number will be sent as a contact when the button is pressed. Available in private chats only
  * @param requestLocation    Boolean. Optional. If True, the user's current location will be sent when the button is pressed. Available in private chats only
  */

case class KeyboardButton(text: String,
                          requestContact: Option[Boolean] = None,
                          requestLocation: Option[Boolean] = None
                         )
