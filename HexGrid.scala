import Array._
import scala.reflect.ClassTag

/**
 * @author charlie
 */
// order of Null and ClassTag is important
// need Null so I can return null for non-existent cells
// need ClassTag so I can create the right kind of array
// drawback is now I can't store value-types
// an alternative would be to expect a "NULL" value of type T as a parameter
class HexGrid[T:ClassTag](val width:Int, val height:Int) {
  private var nothing:T = _ // use the types default as the result for out of bounds accesses - needs to be a var, the _ doesn't work with val
  val cells = ofDim[T](height,width)
  // allow for out of bounds references which always result in the null character
  def apply(x:Int, y:Int):T = 
    if (x>=0 && y>=0 && x<height && y < width) cells(x)(y) else nothing
  def init(array:Array[String]) = {
    assert(array.length == height)
    for (i <- 0 until height) {
      assert(array(i).length >= width) // lines can be too long but not too short
      for (j <- 0 until width) {
        cells(i)(j) = nothing
      }
    }
  }
  def neighbors(row:Int, col:Int):Array[T] = {
    val offset = if (row%2 == 0) -1 else 0
    Array(
      this(row-1,col+offset),this(row-1,col+offset+1),
      this(row,col-1), this(row,col+1),
      this(row+1,col+offset),this(row+1,col+offset+1))
  }
  def neighbors2(row:Int, col:Int):Array[T] = {
    val offset = if (row%2 == 0) -1 else 0
    Array(
      this(row-2,col),
      this(row-1,col+offset-1),this(row-1,col+offset+2),
      this(row+1,col+offset-1),this(row+1,col+offset+2),
      this(row+2,col))
  }
  def update(x:Int, y:Int, v:T) ={
    cells(x)(y) = v
  }
  override def toString:String = {
    var result = ""
    for(i <- 0 until height) {
      if (i % 2 == 1) result = result + " " // shift odd rows right by 1 space
      for(j <- 0 until width) {
        result = result + cells(i)(j) + " "
      }
      result = result + "\n"
    }
    result
  }
  
}