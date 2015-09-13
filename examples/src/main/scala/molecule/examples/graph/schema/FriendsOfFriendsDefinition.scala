package molecule.examples.graph.schema

import molecule.dsl.schemaDefinition._


@InOut(0, 4)
object FriendsOfFriendsDefinition {

  trait Person {
    val name    = oneString
    val age     = oneInt
    val friends = many[Person]
  }
}
