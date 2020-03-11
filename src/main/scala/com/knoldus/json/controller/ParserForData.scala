package com.knoldus.json.controller

import net.liftweb.json.DefaultFormats

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ParserForData(readJsonDataImpl: ReadJsonDataImpl) {
  implicit val formats: DefaultFormats.type = DefaultFormats


  def parser[T](url: String)(implicit m: Manifest[T]): Future[List[T]] = {
    try {
      val futureData = readJsonDataImpl.readAll(url).map(content => net.liftweb.json.parse(content))
      futureData.map(data => data.children.map(values => values.extract[T]))

    } catch {
      case exception: Exception => Future {
        List.empty[T]
      }
    }
  }
}
