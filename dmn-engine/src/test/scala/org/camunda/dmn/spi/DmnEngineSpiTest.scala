package org.camunda.dmn

import org.camunda.dmn.DmnEngine._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DmnEngineSpiTest extends AnyFlatSpec with Matchers {

  val engine = new DmnEngine

  def decision = getClass.getResourceAsStream("/spi/SpiTests.dmn")

  "A custom value mapper" should "transform the input" in {

    val result = engine.eval(decision, "varInput", Map("in" -> "bar"))

    result.isRight should be (true)
    result.map(_.value should be ("baz"))
  }

  it should "transform the output" in {

    val result = engine.eval(decision, "varOutput", Map[String, Any]())

    result.isRight should be (true)
    result.map(_.value should be ("baz"))
  }

  "A custom function provider" should "provide a function" in {

    val result = engine.eval(decision, "invFunction", Map("x" -> 2))

    result.isRight should be (true)
    result.map(_.value should be (3))
  }

}
