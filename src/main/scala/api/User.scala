package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * User
  *
  * This object represents a Telegram user or bot.
  *
  * @param id           Integer. Unique identifier for this user or bot
  * @param firstName    String. User‘s or bot’s first name
  * @param lastName     String. Optional. User‘s or bot’s last name
  * @param username     String. Optional. User‘s or bot’s username
  */

case class User(id: Int,
                firstName: String,
                lastName: Option[String] = None,
                username: Option[String] = None
               )
