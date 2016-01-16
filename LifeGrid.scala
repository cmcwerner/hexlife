import Array._
import LifeGrid._
/**
 * @author charlie
 */
class LifeGrid(w:Int,h:Int) extends HexGrid(w,h) {
  def next(past:LifeGrid, rule:(Char,Int) => Char) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = aliveCount(past.neighbors(r,c))
	this(r,c) = rule(past(r,c), numAlive)
      }
    }
  } 
  def next2(past:LifeGrid) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = aliveCount(past.neighbors(r,c)) +0.3*aliveCount(past.neighbors2(r,c))
        if (past(r,c) == ALIVE) {
          if (numAlive >= 2 && numAlive <= 3.3) this(r,c) = ALIVE
          else this(r,c) = DEAD
        }
        else {
          if (numAlive >= 2.3 && numAlive <= 2.9) this(r,c) = ALIVE
          else this(r,c) = DEAD
        }
      }
    }
  }
  def randomize() {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        this(r,c) = if (Math.random() < .3) ALIVE else DEAD
      }
    }
  }
  def aliveCount(vals:Array[Char]):Int = vals.count( x => x==ALIVE)

  def r6 = (current:Char, count:Int) =>
        if (current == ALIVE) {
          if (count >= 2 && count <= 3) ALIVE
          else DEAD
        }
        else {
          if (count == 3) ALIVE
          else DEAD
        }

}

object LifeGrid {
  val ALIVE = 'X'
  val DEAD = '.'
}
