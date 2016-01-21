import Array._

/**
 * @author charlie
 */
class HexGrid(val width:Int, val height:Int) {
  val cells = ofDim[Char](height,width)
  // called on grid(r,c) accesses
  // allow for out of bounds references which always result in the null character
  def apply(x:Int, y:Int):Char = 
    if (x>=0 && y>=0 && x<height && y < width) cells(x)(y) else '\u0000'
  // called on grid(r,c) = expr assignments   
  def update(x:Int, y:Int, v:Char) ={
    cells(x)(y) = v
  }
  // fill the cells from an Array of String - each line is one row of the grid
  def init(array:Array[String]) = {
    assert(array.length == height)
    for (i <- 0 until height) {
      assert(array(i).length >= width) // lines can be too long but not too short
      for (j <- 0 until width) {
        cells(i)(j) = array(i).charAt(j)
      }
    }
  }
  // the standard 6 neighbors returned as an array of char
  def neighbors(row:Int, col:Int):Array[Char] = {
    val offset = if (row%2 == 0) -1 else 0
    Array(
      this(row-1,col+offset),this(row-1,col+offset+1),
      this(row,col-1), this(row,col+1),
      this(row+1,col+offset),this(row+1,col+offset+1))
  }
  // the second tier more distant 6 neighbors returned as an array of char
  def neighbors2(row:Int, col:Int):Array[Char] = {
    val offset = if (row%2 == 0) -1 else 0
    Array(
      this(row-2,col),
      this(row-1,col+offset-1),this(row-1,col+offset+2),
      this(row+1,col+offset-1),this(row+1,col+offset+2),
      this(row+2,col))
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