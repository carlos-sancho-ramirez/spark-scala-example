import org.apache.spark.SparkContext

object SparkExample {
  def main(args: Array[String]) = {
    val masterUrl = "local"
    val applicationName = "Spark example" //This should be centralised with the name in build.sbt
    val sparkHome = "/usr/local/spark" //This should not be hardcoded in code
    val sparkContext = new SparkContext(masterUrl, applicationName, sparkHome, Nil, Map(), Map())

    val input = sparkContext.textFile("input.txt") //Input file uri should be passed as parameter
    val count = input
        .flatMap(line => line.split(" "))
        .map(word => (word, 1))
        .reduceByKey(_ + _)

    count.saveAsTextFile("outfile")
    println("OK")
  }
}
