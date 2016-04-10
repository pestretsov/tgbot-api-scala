package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * Contact
  *
  * This object represents a phone contact.
  *
  * @param phoneNumber	String	Contact's phone number
  * @param firstName	  String	Contact's first name
  * @param lastName	    String	Optional. Contact's last name
  * @param userId	      Integer	Optional. Contact's user identifier in Telegram
  */

case class Contact(phoneNumber: String,
                   firstName: String,
                   lastName: Option[String] = None,
                   userId: Option[Int] = None
                  )
