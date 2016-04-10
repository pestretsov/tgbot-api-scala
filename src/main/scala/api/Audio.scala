package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * Audio
  *
  * This object represents an audio file to be treated as music by the Telegram clients.
  *
  * @param fileId     String	Unique identifier for this file
  * @param duration	  Integer	Duration of the audio in seconds as defined by sender
  * @param performer	String	Optional. Performer of the audio as defined by sender or by audio tags
  * @param title	    String	Optional. Title of the audio as defined by sender or by audio tags
  * @param mimeType	  String	Optional. MIME type of the file as defined by sender
  * @param fileSize 	Integer	Optional. File size
  */

case class Audio(fileId: String,
                 duration: Int,
                 performer: Option[String] = None,
                 title: Option[String] = None,
                 mimeType: Option[String] = None,
                 fileSize: Option[Int] = None
                )