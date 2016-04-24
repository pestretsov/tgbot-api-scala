package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * Document
  *
  * This object represents a general file (as opposed to photos, voice messages and audio files).
  *
  * @param fileId      String. Unique file identifier
  * @param thumb       PhotoSize. Optional. Document thumbnail as defined by sender
  * @param fileName    String. Optional. Original filename as defined by sender
  * @param mimeType    String. Optional. MIME type of the file as defined by sender
  * @param fileSize    Integer. Optional. File size
  *
  */

case class Document(
                     fileId: String,
                     thumb: Option[PhotoSize] = None,
                     fileName: Option[String] = None,
                     mimeType: Option[String] = None,
                     fileSize: Option[Int] = None
                   )
