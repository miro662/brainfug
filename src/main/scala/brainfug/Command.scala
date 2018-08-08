package brainfug

sealed abstract class Command

case class Add(n: Int = 1) extends Command
case class Sub(n: Int = 1) extends Command

case class Out(n: Int = 1) extends Command
case class In(n: Int = 1) extends Command

case class Left(n: Int = 1) extends Command
case class Right(n: Int = 1) extends Command

case class Begin() extends Command
case class End() extends Command

