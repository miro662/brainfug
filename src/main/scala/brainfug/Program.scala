package brainfug

class Program(commands:List[Command]) {
  def optimize():Program = {
    val optimized_commands = commands.foldLeft(List[Command]())((commands, command) => (commands.lastOption, command) match {
      case (Some(Add(x)), Add(y)) => commands.dropRight(1) ++ List(Add(x + y))
      case (Some(Sub(x)), Sub(y)) => commands.dropRight(1) ++ List(Sub(x + y))
      case (Some(In(x)), In(y)) => commands.dropRight(1) ++ List(In(x + y))
      case (Some(Out(x)), Out(y)) => commands.dropRight(1) ++ List(Out(x + y))
      case (Some(Left(x)), Left(y)) => commands.dropRight(1) ++ List(Left(x + y))
      case (Some(Right(x)), Right(y)) => commands.dropRight(1) ++ List(Right(x + y))

      case (Some(_), x) => commands ++ List(x)
      case (None, x) => commands ++ List(x)
    })
    new Program(optimized_commands)
  }

  def run(vm: BrainfugVM):Unit = {
    var pc = 0
    while (pc < commands.length) {
      val currentCommand = commands(pc)
      pc = vm.call(currentCommand, pc)
    }
  }
}

object Program {
  val non_comment_characters = "+-<>[].,"

  def compile(from:String):Program = {
    val commands = from.filter(ch => non_comment_characters contains ch) map {
      case '+' => Add()
      case '-' => Sub()
      case '.' => Out()
      case ',' => In()
      case '<' => Left()
      case '>' => Right()
      case '[' => Begin()
      case ']' => End()
    }

    new Program(commands.toList)
  }
}