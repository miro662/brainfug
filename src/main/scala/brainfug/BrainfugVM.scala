package brainfug

import java.io.Writer
import java.io.Reader

import scala.collection.mutable
import scala.collection.mutable.Stack

class BrainfugVM(writer: Writer, reader: Reader, val array_size: Int = 65536) {
  private var pointer:Int = 0
  private var data_array:Array[Int] = Array.fill(array_size){0}
  private val ret_stack = mutable.Stack[Int]()

  def call(command: Command, pc:Int):Int = command match {
    case Left() =>
      pointer = if (pointer == 0) array_size - 1 else pointer - 1
      pc + 1

    case Right() =>
      pointer = if (pointer == (array_size - 1)) 0 else pointer + 1
      pc + 1

    case Add() =>
      data_array(pointer) = data_array(pointer) + 1
      pc + 1

    case Sub() =>
      data_array(pointer) = data_array(pointer) - 1
      pc + 1

    case Begin() =>
      ret_stack.push(pc)
      pc + 1

    case End() =>
      val r = ret_stack.pop()
      if (data_array(pointer) != 0) r else pc + 1

    case In() =>
      val in = reader.read()
      data_array(pointer) = if (in != -1) in else 0
      pc + 1

    case Out() =>
      writer.write(data_array(pointer))
      writer.flush()
      pc + 1
  }
}
