import scala.swing._
import scala.swing.event._
import scala.Array._
object HexLifeGUI extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Hex Life"
    val gridSize = 100
    var now = new LifeGrid(gridSize,gridSize)
    var next = new LifeGrid(gridSize,gridSize)
    val first:Array[String] = ofDim(gridSize)
    now.randomize()
   var generation = 0
  
    
    
    val board = new HexDisplay()
    val button = new Button {
      text = "next"
    }

    contents = new BoxPanel(Orientation.Vertical) {
      contents += board
      contents += button
      border = Swing.EmptyBorder(30, 30, 10, 30)
    }
    listenTo(button)
    var nClicks = 0
    reactions += {
      case ButtonClicked(b) =>
        nextStep
    }
    def nextStep = {
      next.next(now)
        var temp = now
        now = next
        next = temp
        board.update(now)
    }
     val updater = new javax.swing.Timer(100,null)
  updater.addActionListener(Swing.ActionListener(e => {nextStep}))
  updater.start()
  }
}