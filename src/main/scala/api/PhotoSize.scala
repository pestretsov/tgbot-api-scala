package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * PhotoSize
  *
  * This object represents one size of a photo or a file / sticker thumbnail.
  *
  * @param fileId      String. Unique identifier for this file
  * @param width       Integer. Photo width
  * @param height      Integer. Photo height
  * @param fileSize    Integer. Optional. File size
  */

case class PhotoSize(
                      fileId: String,
                      width: Int,
                      height: Int,
                      fileSize: Option[Int] = None
                    )
