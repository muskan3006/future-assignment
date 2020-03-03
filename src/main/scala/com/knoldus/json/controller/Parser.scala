package com.knoldus.json.controller

import com.knoldus.json.model._
import net.liftweb.json.DefaultFormats

object Parser {
  implicit val formats: DefaultFormats.type = DefaultFormats

  /**
   * It is used to parse the json file containing comments
   * @param commentData
   * @return
   */
  def parseComments(commentData: String): List[Comments] = {
    val parsedCommentData = net.liftweb.json.parse(commentData)
    parsedCommentData.children map { comments =>
      comments.extract[Comments]
    }
  }


  def parsePosts(postData: String): List[Posts] = {
    val parsedPstData = net.liftweb.json.parse(postData)
    parsedPstData.children map { posts =>
      posts.extract[Posts]
    }
  }

  def parseUsers(userData: String): List[Users] = {
    val parsedUserData = net.liftweb.json.parse(userData)
    parsedUserData.children map { user =>

        user.extract[Users]
    }

  }

}
