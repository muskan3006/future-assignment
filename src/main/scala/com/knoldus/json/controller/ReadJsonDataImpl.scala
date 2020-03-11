package com.knoldus.json.controller

import com.knoldus.json.model.ReadJsonData
import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ReadJsonDataImpl extends ReadJsonData {
  override def readAll(url: String):Future[String] = {
    val request = new HttpGet(url)
    val client = HttpClientBuilder.create().build()
   Future{ val response = client.execute(request)
    IOUtils.toString(response.getEntity.getContent)
  }
}
}
