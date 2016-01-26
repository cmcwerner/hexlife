import scala.io.Source
import Array._

/**
 * @author charlie
 */
object HexLife {
  def main(args:Array[String]) {

    // evaluate command line parameters
    val size:Int = if (args.contains("-size")) args(args.indexOf("-size")+1).toInt else 100
    val generations:Int = if (args.contains("-g")) args(args.indexOf("-g")+1).toInt else 10
    val printFreq:Int = if (args.contains("-p")) args(args.indexOf("-p")+1).toInt else 1
    val fileName = if (args.contains("-f")) args(args.indexOf("-f")+1) else null
    def mkGrid = if (args.contains(".12")) new LifeGrid(size,size) with LifeRules12
    else new LifeGrid(size,size) with LifeRules6

    // the initial world setup - may get intialize below - needs to be here so
    // the following two functions can reference it
    var current = mkGrid
    // the main loop for the command line version
    def runCmdLine() {
      var next = mkGrid
      println(current)

      for (generation <- 1 to generations) {
        next.next(current)
        var temp = current
        current = next
        next = temp
        if (generation%printFreq == 0) {
          println("generation " + generation)
          println(current)
        }
      }
    }
 
    // starts off the GUI version
    def runGUI() {
      val other = mkGrid
      val myTop = new HexLifeGUI(current, other)
      myTop.pack()
      myTop.visible = true
    }

    // process the input file if there is one or set a random grid
    val first:Array[String] = ofDim(size) // array that will contain the intial grid values
    if (fileName == null) current.randomize()
    else {
      var i = 0
      for (line <-Source.fromFile(fileName).getLines(); if i < size) {
      	  first(i) = line
	        i+=1
      }
      current.init(first)
    }
    
    if (args.contains("-G")) runGUI()
    else runCmdLine()
  }
 
}