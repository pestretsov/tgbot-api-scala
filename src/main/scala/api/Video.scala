package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * Video
  *
  * This object represents a video file.
  *
  * @param fileId	     String. Unique identifier for this file
  * @param width       Integer. Video width as defined by sender
  * @param height      Integer. Video height as defined by sender
  * @param duration    Integer. Duration of the video in seconds as defined by sender
  * @param thumb	     PhotoSize. Optional. Video thumbnail
  * @param mimeType    String. Optional. Mime type of a file as defined by sender
  * @param fileSize    Integer. Optional. File size
  */

case class Video(
                  fileId: String,
                  width: Int,
                  height: Int,
                  duration: Int,
                  thumb: Option[PhotoSize] = None,
                  mimeType: Option[String] = None,
                  fileSize: Option[Int] = None
                )
