import Array._
import Cell._
import scala.reflect.ClassTag
/**
 * @author charlie
 */
class LifeGrid(w:Int,h:Int) extends HexGrid[Cell.Value](w,h) {
  def next(past:LifeGrid) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = aliveCount(past.neighbors(r,c))
        if (past(r,c) == Alive) {
          if (numAlive >= 2 && numAlive <= 3) this(r,c) = Alive
          else this(r,c) = Dead
        }
        else {
          if (numAlive == 3) this(r,c) = Alive
          else this(r,c) = Dead
        }
      }
    }
  } 
  def next2(past:LifeGrid) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = aliveCount(past.neighbors(r,c)) +0.3*aliveCount(past.neighbors2(r,c))
        if (past(r,c) == Alive) {
          if (numAlive >= 2 && numAlive <= 3.3) this(r,c) = Alive
          else this(r,c) = Dead
        }
        else {
          if (numAlive >= 2.3 && numAlive <= 2.9) this(r,c) = Alive
          else this(r,c) = Dead
        }
      }
    }
  }
  def randomize() {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        this(r,c) = if (Math.random() < .3) Alive else Dead
      }
    }
  }
  def aliveCount(vals:Array[Cell.Value]):Int = vals.count( x => x==Alive)
}

