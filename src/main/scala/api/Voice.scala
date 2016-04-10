package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * Voice
  *
  * This object represents a voice note.
  *
  * @param fileId	String	Unique identifier for this file
  * @param duration	Integer	Duration of the audio in seconds as defined by sender
  * @param mimeType	String	Optional. MIME type of the file as defined by sender
  * @param fileSize	Integer	Optional. File size
  */

case class Voice(fileId: String,
                 duration: Int,
                 mimeType: Option[String] = None,
                 fileSize: Option[Int] = None
                )