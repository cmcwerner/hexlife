import scala.swing._
import scala.swing.event._
import scala.Array._
import java.util.Scanner
object HexLifeGUI extends SimpleSwingApplication {
  private var myTop:Frame = null
  private var running:Boolean = false
  override def top = myTop
  override def startup(args: Array[String]) {
    myTop = new MainFrame {
      title = "Hex Life"
          
      var gridSize = 100
      var input:Scanner = null
      if (args.length > 0) {
        input = new Scanner(System.in)
        gridSize = args(0).toInt
      }
      var now = new LifeGrid(gridSize,gridSize)
      var next = new LifeGrid(gridSize,gridSize)
      if (args.length > 1) {
        val first:Array[String] = ofDim(gridSize)
        for (i <- 0 until gridSize) first(i) = input.nextLine()
        now.init(first)
      }
      else now.randomize()

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
        next.next2(now)
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
    
    if (myTop.size == new Dimension(0,0)) myTop.pack()
      myTop.visible = true
  }
}