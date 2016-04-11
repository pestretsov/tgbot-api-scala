package api

/**
  * Created by artemypestretsov on 4/5/16.
  *
  * Message
  *
  * This object represents a message.
  *
  * @param messageId	              Integer. Unique message identifier
  * @param from	                    User. Optional. Sender, can be empty for messages sent to channels
  * @param date	                    Integer. Date the message was sent in Unix time
  * @param chat	                    Chat. Conversation the message belongs to
  * @param forwardFrom	            User. Optional. For forwarded messages, sender of the original message
  * @param forwardDate	            Integer. Optional. For forwarded messages, date the original message was sent in Unix time
  * @param replyToMessage	          Message. Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
  * @param text	                    String. Optional. For text messages, the actual UTF-8 text of the message, 0-4096 characters.
  * @param audio	                  Audio. Optional. Message is an audio file, information about the file
  * @param document                 Document. Optional. Message is a general file, information about the file
  * @param photo	                  Array of PhotoSize. Optional. Message is a photo, available sizes of the photo
  * @param sticker	                Sticker. Optional. Message is a sticker, information about the sticker
  * @param video	                  Video. Optional. Message is a video, information about the video
  * @param voice	                  Voice. Optional. Message is a voice message, information about the file
  * @param caption	                String. Optional. Caption for the document, photo or video, 0-200 characters
  * @param contact	                Contact. Optional. Message is a shared contact, information about the contact
  * @param location	                Location. Optional. Message is a shared location, information about the location
  * @param newChatParticipant	      User. Optional. A new member was added to the group, information about them (this member may be the bot itself)
  * @param leftChatParticipant	    User. Optional. A member was removed from the group, information about them (this member may be the bot itself)
  * @param newChatTitle	            String. Optional. A chat title was changed to this value
  * @param deleteChatPhoto	        True. Optional. Service message: the chat photo was deleted
  * @param groupChatCreated	        True. Optional. Service message: the group has been created
  * @param supergroupChatCreated    True. Optional. Service message: the supergroup has been created
  * @param channelChatCreated	      True. Optional. Service message: the channel has been created
  * @param migrateToChatId	        Integer. Optional. The group has been migrated to a supergroup with the specified identifier, not exceeding 1e13 by absolute value
  * @param migrateFromChatId	      Integer. Optional. The supergroup has been migrated from a group with the specified identifier, not exceeding 1e13 by absolute value
  */

case class Message(messageId: Int,
                   from: Option[User] = None,
                   date: Int,
                   chat: Chat,
                   forwardFrom: Option[User] = None,
                   forwardDate: Option[Int] = None,
                   replyToMessage: Option[Message] = None,
                   text: Option[String] = None,
                   audio: Option[Audio] = None,
                   document: Option[Document] = None,
                   photo: Option[Array[PhotoSize]] = None,
                   sticker: Option[Sticker] = None,
                   video: Option[Video] = None,
                   voice: Option[Voice] = None,
                   caption: Option[String] = None,
                   contact: Option[Contact] = None,
                   location: Option[Location] = None,
                   newChatParticipant: Option[User] = None,
                   leftChatParticipant: Option[User] = None,
                   newChatTitle: Option[String] = None,
                   deleteChatPhoto: Option[Boolean] = None,
                   groupChatCreated: Option[Boolean] = None,
                   supergroupChatCreated: Option[Boolean] = None,
                   channelChatCreated: Option[Boolean] = None,
                   migrateToChatId: Option[Int] = None,
                   migrateFromChatId: Option[Int] = None
                  )
