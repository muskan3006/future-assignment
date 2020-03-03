package com.knoldus.twitter.controller

import twitter4j.TwitterFactory
import twitter4j.Twitter
import twitter4j.conf.ConfigurationBuilder
object InstanceOfTwitter {

  def getInstanceOfTwitter: Twitter ={
    val configurationBuilder = new ConfigurationBuilder
    configurationBuilder.setDebugEnabled(true)
      .setOAuthConsumerKey("e6uS4phTxImI68qlA6h4V3zwR")
      .setOAuthConsumerSecret("M8b4Q3sudgU9mNZgJx1onUlqQYi5h5YCK1GVacjAc8yHDAohFc")
      .setOAuthAccessToken("160922224-AKOoOasbqi3huqT7uyq4Og0Oqlucn8rKeD9IcUvU")
      .setOAuthAccessTokenSecret("7HgIJUmjOX2AZThvVp7RPWsZwOrW1ffpvkEpjeBSQynnH")
    val tf = new TwitterFactory(configurationBuilder.build())
    val twitter = tf.getInstance()
    twitter
  }


}
