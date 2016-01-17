import scala.swing._
import scala.swing.event._
import scala.Array._
import java.util.Scanner
import LifeGrid._

//object HexLifeGUI extends SimpleSwingApplication {
class HexLifeGUI(current:LifeGrid) extends MainFrame {
  title = "Hex Life"
  private var running:Boolean = false
  var now:LifeGrid = current
  var next:LifeGrid = new LifeGrid(current.w, current.h)

  var generation = 0
      
   val board = new HexDisplay()
   board.update(now)
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
        if (running) {running = false; updater.stop()}
        else {running = true; updater.start()}
    }
    def nextStep = {
      next.next(now, r6, aliveCount6)
      var temp = now
      now = next
      next = temp
      board.update(now)
    }
    val updater = new javax.swing.Timer(100,null)
    updater.addActionListener(Swing.ActionListener(e => {nextStep}))
    updater.start()
    running = true 
}