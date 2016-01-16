
import scala.io.Source
import Array._
import LifeGrid._

/**
 * @author charlie
 */
object HexLife {
  def main(args:Array[String]) {
    val size:Int = if (args.contains("-size")) args(args.indexOf("-size")+1).toInt else 100
    val generations:Int = if (args.contains("-g")) args(args.indexOf("-g")+1).toInt else 10
    val printFreq:Int = if (args.contains("-p")) args(args.indexOf("-p")+1).toInt else 1
    val fileName = if (args.contains("-f")) args(args.indexOf("-f")+1) else null
    var current = new LifeGrid(size,size)
    var next = new LifeGrid(size,size)
    val first:Array[String] = ofDim(size)
    if (fileName == null) current.randomize()
    else {
      var i = 0
      for (line <-Source.fromFile(fileName).getLines(); if i < size) {
      	  first(i) = line
	  i+=1
      }
      current.init(first)
    }
    println(current)

    def r6 = (current:Char, count:Int) =>
        if (current == ALIVE) {
          if (count >= 2 && count <= 3) ALIVE
          else DEAD
        }
        else {
          if (count == 3) ALIVE
          else DEAD
        }


    for (generation <- 1 to generations) {
      next.next(current, r6)
      var temp = current
      current = next
      next = temp
      if (generation%printFreq == 0) {
        println("generation " + generation)
        println(current)
      }
    }
  }
  
}