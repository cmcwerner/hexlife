import Array._

/**
 * @author charlie
 */
class LifeGrid(w:Int,h:Int) extends HexGrid(w,h) {
  val ALIVE = 'X'
  val ALIVE2 = 'O'
  val DEAD = '.'
  def next(past:LifeGrid) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = aliveCount(past.neighbors(r,c))
        if (past(r,c) == ALIVE) {
          if (numAlive >= 2 && numAlive <= 3) this(r,c) = ALIVE
          else this(r,c) = DEAD
        }
        else {
          if (numAlive == 3) this(r,c) = ALIVE
          else this(r,c) = DEAD
        }
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
  def aliveCount(vals:Array[Char]):Int = vals.count( x => x!=DEAD)
}

