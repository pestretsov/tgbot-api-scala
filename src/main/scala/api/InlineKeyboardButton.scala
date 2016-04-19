package api

/**
  * Created by artemypestretsov on 4/12/16.
  *
  * InlineKeyboardButton
  *
  * This object represents one button of an inline keyboard. You must use exactly one of the optional fields.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will display unsupported message.
  *
  * @param text                 String. Label text on the button
  * @param url                  String. Optional. HTTP url to be opened when button is pressed
  * @param callbackData         String. Optional. Data to be sent in a callback query to the bot when button is pressed
  * @param switchInlineQuery    String. Optional. If set, pressing the button will prompt the user to select one of their chats, open that chat and insert the bot‘s username and the specified inline query in the input field. Can be empty, in which case just the bot’s username will be inserted.
  *                                     Note: This offers an easy way for users to start using your bot in inline mode when they are currently in a private chat with it. Especially useful when combined with switch_pm… actions – in this case the user will be automatically returned to the chat they switched from, skipping the chat selection screen.
  */
case class InlineKeyboardButton(
                                 text: String,
                                 url: Option[String] = None,
                                 callbackData: Option[String] = None,
                                 switchInlineQuery: Option[String] = None
                               )