name := "Spark example"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.1"

lazy val submit = taskKey[Unit]("Submits this application to Spark to be processed in the local machine")

submit := {
  val cmd :List[String] = List("spark-submit", "--class", "SparkExample", "--master", "local", "--name", "My Spark Example", "sparkExample.jar")
  cmd !
}

