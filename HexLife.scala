
import java.util.Scanner
import Array._

/**
 * @author charlie
 */
object HexLife {
  def main(args:Array[String]) {
    val input:Scanner = new Scanner(System.in)
    val size:Int = args(0).toInt
    println(size)
    var current = new LifeGrid(size,size)
    var next = new LifeGrid(size,size)
    val first:Array[String] = ofDim(size)
    if (args.length >1) current.randomize()
    else {
      for (i <- 0 until size) first(i) = input.nextLine()
      current.init(first)
    }
    println(current)
    for (generation <- 1 to 100) {
      next.next(current)
      var temp = current
      current = next
      next = temp
      if (generation%10 == 0) {
        println("generation " + generation)
        println(current)
      }
    }
  }
  
}