package com.knoldus.json.controller

import com.knoldus.json.model._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserPostAndComments(parserForData: ParserForData){
val users:Future[List[Users]]= parserForData.parser("https://jsonplaceholder.typicode.com/users")

  val posts: Future[List[Posts]] = parserForData.parser("https://jsonplaceholder.typicode.com/posts")
  val comments: Future[List[Comments]] = parserForData.parser("https://jsonplaceholder.typicode.com/comments")
  val postWithAllComments: Future[List[CommentsWithPosts]] = posts.map(listPost =>
    comments.map(comment =>
      listPost.map(post =>
        CommentsWithPosts(post, comment.filter(_.postId == post.id))))).flatten

  val userAndItsPosts: Future[List[UserAndPosts]] = users.map(listUser =>
    posts.map(listPosts =>
      listUser.map(user =>
        UserAndPosts(user, listPosts.filter(_.userId == user.id))))).flatten
  val postCount: Future[List[PostCount]] = userAndItsPosts.map(userAndPost =>
    userAndPost.map(user =>
      PostCount(user.user, user.posts.length)))
  val commentCount: Future[List[CommentCount]] = postWithAllComments.map(listPostComment =>
    listPostComment.map(postAndComment =>
      CommentCount(postAndComment.post, postAndComment.comments.length)))
  val maxCommentedPost: Future[CommentCount] = commentCount.map(listPostComments =>
    listPostComments.sortBy(_.countOfComment).reverse.head)

  def userWithMaxCommentsOnPost: Future[String] = maxCommentedPost.map(post =>
    users.map(listUser =>
      listUser.filter(_.id == post.post.userId).head.name)).flatten


  def userWithMaxPosts: Future[String] = postCount.map(listPostCount =>
    listPostCount.sortBy(_.countOfPosts).reverse.head.user.name)

}

//object A extends App {
//  val a = new UserPostAndComments
//  val b = a.userWithMaxCommentsOnPost
//  val c = a.userWithMaxPosts
//  Thread.sleep(5000)
//  println(b, c)
//}
