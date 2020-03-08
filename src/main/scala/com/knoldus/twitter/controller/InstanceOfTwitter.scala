package com.knoldus.twitter.controller

import com.typesafe.config.ConfigFactory
import twitter4j.auth.AccessToken
import twitter4j.{Query, Status, Twitter, TwitterFactory}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstanceOfTwitter(hashtag: Query) {

  def getInstanceOfTwitter: Future[List[Status]] = {
    val config = ConfigFactory.load()
    val twitter: Twitter = new TwitterFactory().getInstance
    twitter.setOAuthConsumer(config.getString("consumer.key"),
      config.getString("consumer.secret"))
    twitter.setOAuthAccessToken(new AccessToken(
      config.getString("token.key"),
      config.getString("token.secret")))
    Future {
      twitter.search(hashtag).getTweets.asScala.toList
    }

  }.fallbackTo(Future{List.empty[Status]})

}
