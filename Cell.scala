object Cell extends Enumeration {
  val Dead = Value(".") // this needs to be first so it corresponds to zero, the default value
  val Alive = Value("X")
}
