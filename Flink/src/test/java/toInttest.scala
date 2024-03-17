import util.PropertiesLoader

object toInttest {


  def main(args: Array[String]): Unit = {
    PropertiesLoader.load("es.properties").getProperty("port").toInt
  }
}
