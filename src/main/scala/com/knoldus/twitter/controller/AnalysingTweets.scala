package com.knoldus.twitter.controller

import twitter4j._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

class AnalysingTweets(hashtag:Query,twitterInstance:InstanceOfTwitter){


  def tweetCount: Future[Int] = {
    twitterInstance.getInstanceOfTwitter(hashtag).map(status => status.length)
  }

  def avgLikesPerTweet(countOfTweet: Future[Int]): Future[Double] = {
    val totalLikes = twitterInstance.getInstanceOfTwitter(hashtag)
      .map(statuses => statuses.foldLeft(0)((total, element) => total + element.getFavoriteCount))
    val avgLikes: Future[Double] = totalLikes.flatMap(total => countOfTweet.map(count => total / count))
    avgLikes
  }

  def avgRetweetsPerTweet(countOfTweet: Future[Int]): Future[Double] = {
    val totalRetweet = twitterInstance.getInstanceOfTwitter(hashtag)
      .map(statuses => statuses.foldLeft(0)((total, element) => total + element.getRetweetCount))
    val avgRetweet: Future[Double] = totalRetweet.flatMap(total => countOfTweet.map(count => total / count))
    avgRetweet
  }

}

//object A extends App{
//  val a = new InstanceOfTwitter
//  val query = new Query("Muskan")
//  val b = new AnalysingTweets(query,a)
//  val c = b.tweets
//  Thread.sleep(5000)
//  println(c)
//}

