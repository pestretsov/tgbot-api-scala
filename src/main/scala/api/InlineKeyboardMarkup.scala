package api

/**
  * Created by artemypestretsov on 4/12/16.
  *
  * This object represents an inline keyboard that appears right next to the message it belongs to.
  *
  * Warning: Inline keyboards are currently being tested and are only available in one-on-one chats (i.e., user-bot or user-user in the case of inline bots).
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will display unsupported message.
  *
  * @param inlineKeyboard    Array of Array of InlineKeyboardButton. Array of button rows, each represented by an Array of InlineKeyboardButton objects
  */

case class InlineKeyboardMarkup(
                                 inlineKeyboard: Array[Array[InlineKeyboardButton]]
                               )
