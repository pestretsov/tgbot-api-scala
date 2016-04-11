package api

/**
  * Created by artemypestretsov on 4/6/16.
  *
  * UserProfilePhotos
  *
  * This object represent a user's profile pictures.
  *
  * @param totalCount    Integer. Total number of profile pictures the target user has
  * @param photos        Array of Array of PhotoSize. Requested profile pictures (in up to 4 sizes each)
  */

case class UserProfilePhotos(totalCount: Int,
                             photos: Array[Array[PhotoSize]]
                            )
