package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * File
  *
  * This object represents a file ready to be downloaded. The file can be downloaded via
  * the link https://api.telegram.org/file/bot<token>/<file_path>. It is guaranteed that
  * the link will be valid for at least 1 hour. When the link expires, a new one can be
  * requested by calling getFile.
  *
  * Maximum file size to download is 20 MB
  *
  * @param fileId      String. Unique identifier for this file
  * @param fileSize    Integer. Optional. File size, if known
  * @param filePath    String. Optional. File path. Use https://api.telegram.org/file/bot<token>/<file_path> to get the file.
  */

case class File(
                 fileId: String,
                 fileSize: Option[Int] = None,
                 filePath: Option[String] = None
               )
