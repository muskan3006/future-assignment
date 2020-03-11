package com.knoldus.json.model

import scala.concurrent.Future

trait ReadJsonData {
  def readAll(url: String): Future[String]
}
