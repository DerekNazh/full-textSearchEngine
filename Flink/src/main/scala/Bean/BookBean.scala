package Bean

import scala.beans.BeanProperty

/**
 * /**
 * ID,Movie_Name_EN,Movie_Name_CN,Crawl_Date,Number,Username,Date,Star,Comment,Like
 * 0,Avengers Age of Ultron,复仇者联盟2,2017-01-22,1,然潘,2015-05-13,3, 连奥创都知道整容要去韩国。,2404
 * */
 *
 * @param ID
 * @param Movie_Name_EN
 * @param Movie_Name_CN
 * @param Crawl_Date
 * @param Number
 * @param Username
 * @param Date
 * @param star
 * @param Comment
 * @param Like
 */

 case class BookBean( var ID: String ,
                     var Movie_Name_EN: String ,
                     var Movie_Name_CN: String ,
                     var Crawl_Date: String ,
                     var Number: String,
                     var Username: String,
                     var Date:String,
                     var Star:String,
                     var Comment:String,
                     var Like:String
                   ){



}
