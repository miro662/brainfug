package brainfug

import java.io._

object Main extends App {
  val program = Program.compile("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.").optimize()

  val writer = new OutputStreamWriter(System.out)
  val reader = new InputStreamReader(System.in)

  val vm = new BrainfugVM(writer, reader)

  program.run(vm)
}
