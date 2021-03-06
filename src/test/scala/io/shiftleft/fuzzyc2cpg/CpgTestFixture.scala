package io.shiftleft.fuzzyc2cpg

import io.shiftleft.fuzzyc2cpg.output.inmemory.OutputModuleFactory
import io.shiftleft.queryprimitives.steps.starters.Cpg

case class CpgTestFixture(projectName: String) {

  val cpg: Cpg = {
    val dirName = String.format("src/test/resources/testcode/%s", projectName)
    val inmemoryOutputFactory = new OutputModuleFactory()
    val fuzzyc2Cpg = new Fuzzyc2Cpg(inmemoryOutputFactory)
    fuzzyc2Cpg.runAndOutput(List(dirName).toArray)
    inmemoryOutputFactory.getInternalGraph
  }

  def V = cpg.scalaGraph.V

}
