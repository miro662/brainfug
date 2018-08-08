package brainfug

import java.io._
import java.nio.charset.Charset
import java.nio.file.{Files, NoSuchFileException, Paths}

object Main extends App {
  args.headOption match {
    case None => System.err.println("no source file specified")
    case Some(x) =>
      // Read source code to string
      try {
        val encoded = Files.readAllBytes(Paths.get(x))
        val source_code = new String(encoded, Charset.defaultCharset())

        // Create stdin and stdout readers
        val stdin = new InputStreamReader(System.in)
        val stdout = new OutputStreamWriter(System.out)

        // Create VM
        val vm = new BrainfugVM(stdout, stdin)

        // Compile and optimize program
        val program = Program.compile(source_code).optimize()

        // Run program
        program.run(vm)
      } catch {
        case _: NoSuchFileException => System.err.println(s"cannot load file: $x")
      }
  }
}
