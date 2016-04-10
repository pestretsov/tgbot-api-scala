package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * Sticker
  *
  * This object represents a sticker.
  *
  * @param fileId	  String	Unique identifier for this file
  * @param width	  Integer	Sticker width
  * @param height 	Integer	Sticker height
  * @param thumb	  PhotoSize	Optional. Sticker thumbnail in .webp or .jpg format
  * @param fileSize	Integer	Optional. File size
  */

case class Sticker(fileId: String,
                   width: Int,
                   height: Int,
                   thumb: Option[PhotoSize] = None,
                   fileSize: Option[Int] = None
                  )
