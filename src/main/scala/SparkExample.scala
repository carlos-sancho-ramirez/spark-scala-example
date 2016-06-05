import org.apache.spark.{SparkConf, SparkContext}

object SparkExample {
  def main(args: Array[String]) = {
    // This is here only for debug reasons. It can be removed safely.
    val propNamesJavaIterator = System.getProperties.stringPropertyNames.iterator
    while (propNamesJavaIterator.hasNext) {
      val key = propNamesJavaIterator.next()
      println(s"SystemProperty: $key: ${System.getProperty(key)}")
    }

    // masterURL, appName and used jars comes in the JVM system properties, which are load from
    // SparkConf constructor
    val sparkContext = new SparkContext(new SparkConf())

    val input = sparkContext.textFile("input.txt") //Input file uri should be passed as parameter
    val count = input
        .flatMap(line => line.split(" "))
        .map(word => (word, 1))
        .reduceByKey(_ + _)

    count.saveAsTextFile("outfile")
    println("OK")
  }
}
