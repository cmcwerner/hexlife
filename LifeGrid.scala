import Array._

/**
 * Turn a HexGrid into one that can update itself to a new generation based on some previous grid
 * as the prevoius generation in the Hex game of life.
 * @author charlie
 */
abstract class LifeGrid(val w:Int,val h:Int) extends HexGrid(w,h) with LifeRules {
  val grid = this
  // update this grid based on some previous grid using the specified rule and aliveCount functions
  def next(past:LifeGrid) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = past.aliveCount(r,c)
	      this(r,c) =  next(past(r,c), numAlive)
      }
    }
  } 
  // fill this grid with random aliveness
  // ToDo - allow user specification of probability of life
  def randomize() {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        this(r,c) = if (Math.random() < .3) ALIVE else DEAD
      }
    }
  }
}