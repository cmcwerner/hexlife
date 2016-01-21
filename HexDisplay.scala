import scala.swing._

/**
 * A REALLY basic component to display a HexGrid.
 * @author charlie
 */
class HexDisplay extends Component {
  var xsize = 10
  private var grid:HexGrid = null
  private val dotSize = 4
  private val scale = 4
  override def paint(g:Graphics2D) {
   if (grid == null) return
   for { r <- 0 until grid.height; c <- 0 until grid.width; if (grid(r,c) == 'X')  }  
       g.fillOval(c*scale +rowOffset(r), r*scale, dotSize, dotSize)
  }
  // determine how much to shift a row so cells are properly positioned
  def rowOffset(row:Int) = if (row%2 == 0) 0 else scale/2
  minimumSize = new Dimension(500,500)
  preferredSize = minimumSize
  // tell this component to show a new grid
  def update(grid:HexGrid) {
    this.grid = grid
    this.repaint()
  }
}