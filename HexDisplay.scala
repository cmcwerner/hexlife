import scala.swing._

/**
 * @author charlie
 */
class HexDisplay extends Component {
  var xsize = 10
  private var grid:HexGrid = null
  private val dotSize = 4
  private val scale = 4
  override def paint(g:Graphics2D) {
   if (grid == null) return
    for(r <- 0 until grid.height) {
      for (c <- 0 until grid.width) {
        if (grid(r,c) == 'X') {
          g.fillOval(r*scale, c*scale +rowOffset(r), dotSize, dotSize)
        }
      }
    }
  }
  def rowOffset(row:Int) = if (row%2 == 0) 0 else scale
  minimumSize = new Dimension(500,500)
  preferredSize = minimumSize
  def update(grid:HexGrid) {
    this.grid = grid
    this.repaint()
  }
}