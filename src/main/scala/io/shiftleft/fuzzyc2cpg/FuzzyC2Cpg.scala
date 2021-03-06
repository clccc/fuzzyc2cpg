package io.shiftleft.fuzzyc2cpg

import io.shiftleft.fuzzyc2cpg.output.protobuf.OutputModuleFactory
import org.slf4j.LoggerFactory

object FuzzyC2Cpg extends App {

  val DEFAULT_CPG_OUT_FILE = "cpg.bin.zip"

  private val logger = LoggerFactory.getLogger(getClass)

  parseConfig.map{ config =>
    try {
      val fuzzyc2Cpg = new Fuzzyc2Cpg(new OutputModuleFactory(config.outputPath,
        true, false))
      fuzzyc2Cpg.runAndOutput(config.inputPaths.toArray)
    } catch {
      case exception: Exception =>
        logger.error("Failed to generate CPG.", exception)
        System.exit(1)
    }
    System.exit(0)
  }

  case class Config(inputPaths : Seq[String],
                    outputPath : String)
  def parseConfig : Option[Config] =
    new scopt.OptionParser[Config](getClass.getSimpleName) {
      arg[String]("<input-dir>")
        .unbounded()
        .text("source directories containing C/C++ code")
        .action((x,c) => c.copy(inputPaths = c.inputPaths :+ x))
      opt[String]("out")
        .text("output filename")
        .action((x,c) => c.copy(outputPath = x))

    }.parse(args, Config(List(), DEFAULT_CPG_OUT_FILE))

}
