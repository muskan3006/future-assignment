package com.knoldus.json.controller

import com.knoldus.json.model.{ReadJsonData, Users}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

object JsonUserData extends ReadJsonData {

  def getAllUserData(url:String): Future[List[Users]] = {

    val data: Future[String] = Future {
      readAll(url)
    }
    val parsedUserData = for {userData <- data
                              parsedUserData <- Future(Parser.parseUsers(userData))
                              } yield parsedUserData

    parsedUserData
  }


}

