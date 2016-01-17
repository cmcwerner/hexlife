import Array._
import LifeGrid._
/**
 * @author charlie
 */
class LifeGrid(val w:Int,val h:Int) extends HexGrid(w,h) {
  def next(past:LifeGrid, rule:(Char,Double) => Char, aliveCount:(HexGrid,Int,Int) => Double) {
    for(r <- 0 until width) {
      for (c <- 0 until height) {
        val numAlive = aliveCount(past,r,c)
	this(r,c) = rule(past(r,c), numAlive)
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
}

object LifeGrid {
  val ALIVE = 'X'
  val DEAD = '.'

  // 6 neighbor rules
  def aliveCount6(grid:HexGrid,r:Int,c:Int):Double = grid.neighbors(r,c).count( x => x==ALIVE)

  def r6 = (current:Char, count:Double) =>
    if (current == ALIVE) 
      if (count >= 2 && count <= 3) ALIVE
      else DEAD
    else
      if (count == 3) ALIVE
      else DEAD

    // 12 neighbor rules
    def r12 = (current:Char, count:Double) =>
      if (current == ALIVE)
        if (count >= 2 && count <= 3.3) ALIVE
        else DEAD
      else
        if (count >= 2.3 && count <= 2.9) ALIVE
        else DEAD

    def aliveCount12(grid:HexGrid,r:Int,c:Int):Double = grid.neighbors(r,c).count( x => x==ALIVE) +
        					   0.3*(grid.neighbors2(r,c).count(_ == ALIVE))
}
