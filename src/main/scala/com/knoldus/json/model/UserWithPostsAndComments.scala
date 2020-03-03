package com.knoldus.json.model

case class UserWithPostsAndComments(user: Users, post: List[Posts], comment: List[Comments])