package com.knoldus.twitter.controller

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

class AnalysingTweets(twitterInstance: InstanceOfTwitter) {


  def tweetCount: Future[Int] = {
    twitterInstance.getInstanceOfTwitter.map(status => status.length)
    }.fallbackTo(Future {
    "no tweets"
  })

  def avgLikesPerTweet(countOfTweet: Future[Int]): Future[Double] = {
    val totalLikes = twitterInstance.getInstanceOfTwitter
      .map(statuses => statuses.foldLeft(0)((total, element) => total + element.getFavoriteCount))
    val avgLikes: Future[Double] = totalLikes.flatMap(total => countOfTweet.map(count => total / count))
    avgLikes
    }.fallbackTo(Future {
    "no tweets"
  })

  def avgRetweetsPerTweet(countOfTweet: Future[Int]): Future[Double] = {
    val totalRetweet = twitterInstance.getInstanceOfTwitter
      .map(statuses => statuses.foldLeft(0)((total, element) => total + element.getRetweetCount))
    val avgRetweet: Future[Double] = totalRetweet.flatMap(total => countOfTweet.map(count => total / count))
    avgRetweet
    }.fallbackTo(Future {
    "no tweets"
  })

}
