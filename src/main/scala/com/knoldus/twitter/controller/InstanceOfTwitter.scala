package com.knoldus.twitter.controller

import com.typesafe.config.ConfigFactory
import twitter4j.{Query, Status, Twitter, TwitterFactory}
import twitter4j.auth.AccessToken
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.JavaConverters._
import scala.concurrent.Future

class InstanceOfTwitter(hashtag:Query) {

  def getInstanceOfTwitter: Future[List[Status]] = {
    val config = ConfigFactory.load()
    val twitter: Twitter = new TwitterFactory().getInstance()
    // Authorising with your Twitter Application credentials
    twitter.setOAuthConsumer(config.getString("consumer.key"),
      config.getString("consumer.secret"))
    twitter.setOAuthAccessToken(new AccessToken(
      config.getString("token.key"),
      config.getString("token.secret")))
    Future{ twitter.search(hashtag).getTweets.asScala.toList}


  }

//  def createCaseClass(iterableStatus:List[twitter4j.Status],userPost:List[UserPost]):List[UserPost]={
//    iterableStatus match {
//      case Nil=>userPost
//      case head::Nil=>userPost:+ UserPost(head.getId(),head.getCreatedAt,head.getFavoriteCount,head.getRetweetCount)
//      case head::tail=>createCaseClass(tail,userPost:+ UserPost(head.getId(),head.getCreatedAt,head.getFavoriteCount,head.getRetweetCount))
//    }
//  }


}
