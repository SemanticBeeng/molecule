package molecule.examples.dayOfDatomic.schema

import molecule.util.dsl.schemaDefinition._

@InOut(0, 3)
trait AggregatesDefinition {

  trait Obj {
    val name       = oneString.indexed.doc("Name of a Solar System object.")
    val meanRadius = oneDouble.indexed.doc("Mean radius of an object.")
  }

  trait Data {
    val source = oneString.indexed.doc("Source of the data in a transaction.")
  }
}