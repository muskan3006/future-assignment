package com.knoldus.json.controller

import com.knoldus.json.model.{Posts, ReadJsonData}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

object JsonPostData extends ReadJsonData {

  def getAllPostData(url:String): Future[List[Posts]] = {

    val data: Future[String] = Future {
      readAll(url)
    }

    val parsedPostData = for {postData <- data
                              parsedPostData <- Future(Parser.parsePosts(postData))
                              } yield parsedPostData


    parsedPostData
  }

}
