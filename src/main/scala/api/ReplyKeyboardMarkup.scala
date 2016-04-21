package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  *
  */

trait ReplyMarkup

/**
  * InlineKeyboardMarkup
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
                               ) extends ReplyMarkup

/**
  * ReplyKeyboardMarkup
  *
  * This object represents a custom keyboard with reply options (see Introduction to bots for details and examples).
  *
  * Example: A user requests to change the bot‘s language, bot replies to the request with a keyboard to select the new language. Other users in the group don’t see the keyboard.
  *
  * @param keyboard           Array of Array of KeyboardButton. Array of button rows, each represented by an Array of KeyboardButton objects
  * @param resizeKeyboard     Boolean. Optional. Requests clients to resize the keyboard vertically for optimal fit (e.g., make the keyboard smaller if there are just two rows of buttons). Defaults to false, in which case the custom keyboard is always of the same height as the app's standard keyboard.
  * @param oneTimeKeyboard    Boolean. Optional. Requests clients to hide the keyboard as soon as it's been used. Defaults to false.
  * @param selective	        Boolean. Optional. Use this parameter if you want to show the keyboard to specific users only. Targets: 1) users that are @mentioned in the text of the Message object; 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
  */

case class ReplyKeyboardMarkup(
                                keyboard: Array[Array[KeyboardButton]],
                                resizeKeyboard: Option[Boolean] = None,
                                oneTimeKeyboard: Option[Boolean] = None,
                                selective: Option[Boolean] = None
                              ) extends ReplyMarkup


/**
  * ReplyKeyboardHide
  *
  * Upon receiving a message with this object, Telegram clients will hide the current custom keyboard and display the default letter-keyboard. By default, custom keyboards are displayed until a new keyboard is sent by a bot. An exception is made for one-time keyboards that are hidden immediately after the user presses a button (see ReplyKeyboardMarkup).
  *
  * Example: A user votes in a poll, bot returns confirmation message in reply to the vote and hides keyboard for that user, while still showing the keyboard with poll options to users who haven't voted yet.
  *
  * @param hideKeyboard    True. Requests clients to hide the custom keyboard
  * @param selective       Boolean. Optional. Use this parameter if you want to hide keyboard for specific users only. Targets: 1) users that are @mentioned in the text of the Message object; 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
  */

case class ReplyKeyboardHide(
                              hideKeyboard: Boolean,
                              selective: Option[Boolean] = None
                            ) extends ReplyMarkup

