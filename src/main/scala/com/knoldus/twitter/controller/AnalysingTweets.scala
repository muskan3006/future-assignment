package com.knoldus.twitter.controller

import twitter4j._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.jdk.CollectionConverters._
import scala.language.implicitConversions


class AnalysingTweets {
  val twitter: Twitter = InstanceOfTwitter.getInstanceOfTwitter

  def getTweetsOnHashtag(hashtag: Query): Future[List[Status]] = Future {
    twitter.search(hashtag).getTweets.asScala.toList
  }

  def tweetCount(listOfStatus: Future[List[Status]]): Future[Int] = {
    listOfStatus.map(status => status.length)
  }

  def avgLikesPerTweet(listOfStatus: Future[List[Status]], countOfTweet: Future[Int]): Future[Double] = {
    val totalLikes = listOfStatus.map(statuses => statuses.foldLeft(0)((total, element) => total + element.getFavoriteCount))
    val avg: Future[Double] = totalLikes.flatMap(total => countOfTweet.map(count => total / count))
    avg
  }

}
