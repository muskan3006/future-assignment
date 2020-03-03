package com.knoldus.json.controller

import com.knoldus.json.model.{Comments, ReadJsonData}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

object JsonCommentData extends ReadJsonData{
  def getAllCommentData(url:String): Future[List[Comments]] = {
    val data : Future[String] = Future {
      readAll(url)
    }

    val parsedCommentData = for {commentData <- data
                                 parsedCommentData <- Future(Parser.parseComments(commentData))
                                 } yield parsedCommentData

    parsedCommentData
  }

}
