package com.knoldus.json.controller

import com.knoldus.json.model._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

import scala.concurrent.Future

class ParserForDataSpec extends AsyncFlatSpec with BeforeAndAfterAll with MockitoSugar {
  val mockUserList = List(Users(1, "1", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15"))
    , Users(2, "2", "3", "4", Address("5", "6", "7", "8", Geo("9", "10")), "11", "12", Company("13", "14", "15")))
  val mockPostList = List(Posts(1, 2, "3", "4"), Posts(2, 2, "3", "4"))
  val mockCommentsList = List(Comments(1, 2, "3", "4", "5"), Comments(2, 2, "3", "4", "5"))
  val mockReadJsonDataImpl: ReadJsonDataImpl = mock[ReadJsonDataImpl]
  var parserForData:ParserForData = _
  implicit val formats: DefaultFormats.type = DefaultFormats
  val mockJson: String = write(mockCommentsList)
  val urlForComment = "https://jsonplaceholder.typicode.com/comments"

  override def beforeAll(): Unit = {
    parserForData = new ParserForData(mockReadJsonDataImpl)
  }
  
  "Parser method" should "parse the json and get a list of it's corresponding case class" in {
    when(mockReadJsonDataImpl readAll urlForComment) thenReturn Future{mockJson}
    val actualResult: Future[List[Comments]] = parserForData.parser[Comments](urlForComment)
    actualResult.map(value => assert(value == mockCommentsList))
  }

  "Parser method" should "throw exception with an empty string" in {
    when(mockReadJsonDataImpl readAll urlForComment) thenReturn Future{" "}
    val actualResult: Future[List[Comments]] = parserForData.parser[Comments](urlForComment)
    val expectedResult = List.empty[Comments]
    actualResult.map(value => assert(value == expectedResult))
  }

}
