
name := "forensis-backend"

organization := "co.com.tricloud"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  specs2 % Test,

   //Database
  "com.typesafe.play"      %%    "play-slick"              %    "1.1.1",
  "com.typesafe.play"      %%    "play-slick-evolutions"   %    "1.1.1",
  "org.postgresql"          %    "postgresql"              %    "9.4-1201-jdbc41",
  "com.h2database"          %    "h2"                      %    "1.4.191",

  //Joda Time
  "com.github.tototoshi"   %%    "slick-joda-mapper"       %    "2.2.0",
  "joda-time"               %    "joda-time"               %    "2.9.3",
  "org.joda"                %    "joda-convert"            %    "1.8.1",

  //Testing
  "org.scalatest"          %%    "scalatest"               %    "2.2.4"   % "test"
)

PlayKeys.devSettings := Seq("play.server.http.port" -> "9010")

javaOptions in Test += "-Dconfig.file=conf/test/application-test.conf"
