import scala.swing._
import scala.swing.event._
import scala.Array._
import java.util.Scanner

class HexLifeGUI(current:LifeGrid, other:LifeGrid) extends MainFrame {
  title = "Hex Life"
  private var running:Boolean = false
  var now:LifeGrid = current
  // second grid used in the updating process
  var next:LifeGrid = other

  var generation = 0
      
   val board = new HexDisplay()
   board.update(now)
   // this button can be used to pause the simulation
   val button = new Button {
      text = "start/stop"
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
    // the update method called every time interval in response to the updater timer running out
    def nextStep = {
      next.next(now)
      // swap now and next
      var temp = now
      now = next
      next = temp
      board.update(now)
    }
    
    // start a timer that will periodically call nextStep
    val updater = new javax.swing.Timer(100,null)
    updater.addActionListener(Swing.ActionListener(e => {nextStep}))
    updater.start()
    running = true 
}