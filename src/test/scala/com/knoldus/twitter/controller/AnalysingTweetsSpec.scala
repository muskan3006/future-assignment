package com.knoldus.twitter.controller

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}
import twitter4j.{Query, Status, TwitterObjectFactory}

import scala.concurrent.Future

class AnalysingTweetsSpec extends AsyncFlatSpec with BeforeAndAfterAll with MockitoSugar {
  val query = new Query("#Muskan")
  val mockInstanceOfTwitter: InstanceOfTwitter = mock[InstanceOfTwitter]
  val analysingTwitter = new AnalysingTweets(query, mockInstanceOfTwitter)
  val statusOne: Status = TwitterObjectFactory.createStatus("created_at\": \"Thu Apr 06 15:24:15 +0000 2017\",\n  \"id_str\": \"850006245121695744\",\n  \"text\": \"1\\/ Today we\\u2019re sharing our vision for the future of the Twitter API platform!\\nhttps:\\/\\/t.co\\/XweGngmxlP\",\n  \"user\": {\n    \"id\": 2244994945,\n    \"name\": \"Twitter Dev\",\n    \"screen_name\": \"TwitterDev\",\n    \"location\": \"Internet\",\n    \"url\": \"https:\\/\\/dev.twitter.com\\/\",\n    \"description\": \"Your official source for Twitter Platform news, updates & events. Need technical help? Visit https:\\/\\/twittercommunity.com\\/ \\u2328\\ufe0f #TapIntoTwitter\"\n  },\n  \"place\": {   \n  },\n  \"entities\": {\n    \"hashtags\": [      \n    ],\n    \"urls\": [\n      {\n        \"url\": \"https:\\/\\/t.co\\/XweGngmxlP\",\n        \"unwound\": {\n          \"url\": \"https:\\/\\/cards.twitter.com\\/cards\\/18ce53wgo4h\\/3xo1c\",\n          \"title\": \"Building the Future of the Twitter API Platform\"\n        }\n      }\n    ],\n    \"user_mentions\": [  ]}")
//  val statusTwo: Status = TwitterObjectFactory.createStatus("second Tweet #Muskan")
//  val statusThree: Status = TwitterObjectFactory.createStatus("third Tweet #Muskan")
  val demoListOfStatus: Future[List[Status]] = Future {
    List(statusOne)
  }

  when(mockInstanceOfTwitter.getInstanceOfTwitter).thenReturn(demoListOfStatus)
  "get tweets on hashtag" should "return a list of Status" in {
    val actualResult = analysingTwitter.tweetCount
    val expectedResult = 1
   actualResult.map(value => assert(value == expectedResult))

  }

}

