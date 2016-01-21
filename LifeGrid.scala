import Array._
import LifeGrid._
/**
 * Turn a HexGrid into one that can update itself to a new generation based on some previous grid
 * as the prevoius generation in the Hex game of life.
 * @author charlie
 */
class LifeGrid(val w:Int,val h:Int) extends HexGrid(w,h) {
  // update this grid based on some previous grid using the specified rule and aliveCount functions
  def next(past:LifeGrid, rule:(Char,Double) => Char, aliveCount:(HexGrid,Int,Int) => Double) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = aliveCount(past,r,c)
	      this(r,c) = rule(past(r,c), numAlive)
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

object LifeGrid {
  val ALIVE = 'X'
  val DEAD = '.'

  // 6 neighbor rules
  // given a grid and a location return count of live neighbors
  def aliveCount6(grid:HexGrid,r:Int,c:Int):Double = grid.neighbors(r,c).count( x => x==ALIVE)

  // given a count of neighbors that are alive and a current state (ALIVE or DEAD)
  // return the next state
  // these are the same as original Conway rules
  def r6 = (current:Char, count:Double) =>
    if (current == ALIVE) 
      if (count >= 2 && count <= 3) ALIVE
      else DEAD
    else
      if (count == 3) ALIVE
      else DEAD

  // 12 neighbor rules
  // computes a weighted sum of alive cells in two layers of neighbors
  def aliveCount12(grid:HexGrid,r:Int,c:Int):Double = grid.neighbors(r,c).count( x => x==ALIVE) +
                     0.3*(grid.neighbors2(r,c).count(_ == ALIVE))
  // determine the next state for a given neighbor weighted count and the current cell value                  
  def r12 = (current:Char, count:Double) =>
    if (current == ALIVE)
      if (count >= 2 && count <= 3.3) ALIVE
      else DEAD
    else
      if (count >= 2.3 && count <= 2.9) ALIVE
      else DEAD 
}
