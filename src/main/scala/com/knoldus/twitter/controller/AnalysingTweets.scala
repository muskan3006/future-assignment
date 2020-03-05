package com.knoldus.twitter.controller

import twitter4j._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.jdk.CollectionConverters._
import scala.language.implicitConversions


class AnalysingTweets(twitterInstance:InstanceOfTwitter){
  val twitter: Twitter = twitterInstance.getInstanceOfTwitter

  def getTweetsOnHashtag(hashtag: Query): Future[List[Status]] = Future {
    twitter.search(hashtag).getTweets.asScala.toList
  }

  def tweetCount(listOfStatus: Future[List[Status]]): Future[Int] = {
    listOfStatus.map(status => status.length)
  }

  def avgLikesPerTweet(listOfStatus: Future[List[Status]], countOfTweet: Future[Int]): Future[Double] = {
    val totalLikes = listOfStatus.map(statuses => statuses.foldLeft(0)((total, element) => total + element.getFavoriteCount))
    val avgLikes: Future[Double] = totalLikes.flatMap(total => countOfTweet.map(count => total / count))
    avgLikes
  }

  def avgRetweetsPerTweet(listOfStatus: Future[List[Status]], countOfTweet: Future[Int]): Future[Double] = {
    val totalRetweet = listOfStatus.map(statuses => statuses.foldLeft(0)((total, element) => total + element.getRetweetCount))
    val avgRetweet: Future[Double] = totalRetweet.flatMap(total => countOfTweet.map(count => total / count))
    avgRetweet
  }

}

