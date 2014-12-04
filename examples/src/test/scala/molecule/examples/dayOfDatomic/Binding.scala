package molecule.examples.dayOfDatomic

import molecule._
import molecule.examples.dayOfDatomic.dsl.socialNews._
import molecule.examples.dayOfDatomic.schema._
import molecule.examples.dayOfDatomic.spec.DayOfAtomicSpec

class Binding extends DayOfAtomicSpec {

  implicit val conn = load(SocialNewsSchema.tx, "Binding")

  // Input molecules returning only the entity id (`e`).
  // (Underscore-suffixed attribute names are not returned in the result set)
  val personFirst = m(User.e.firstName_(?))
  val person      = m(User.e.firstName_(?).lastName_(?))

  // Get inserted entity ids
  val List(stewartBrand, johnStewart, stuartSmalley, stuartHalloway) = User.firstName.lastName insert List(
    ("Stewart", "Brand"),
    ("John", "Stewart"),
    ("Stuart", "Smalley"),
    ("Stuart", "Halloway")
  ) ids


  "Binding queries" >> {

    // Find all the Stewart first names
    personFirst("Stewart").get === List(stewartBrand)

    // Find all the Stewart or Stuart first names
    personFirst("Stewart" or "Stuart").get === List(stewartBrand, stuartSmalley, stuartHalloway)
    personFirst("Stewart", "Stuart").get === List(stewartBrand, stuartSmalley, stuartHalloway)

    // Find all the Stewart/Stuart as either first name or last name
    User.e.a.v_("Stewart").get === List(
      (johnStewart, ":user/lastName"),
      (stewartBrand, ":user/firstName")
    )

    // Find only the Smalley Stuarts
    person("Stuart", "Smalley").get === List(stuartSmalley)
  }


  "Binding (continued..)" >> {

    // Bind vars
    person("John", "Stewart").get === List(johnStewart)

    // Bind tuple
    person(("John", "Stewart")).get === List(johnStewart)

    // Bind collection
    personFirst(List("John", "Stuart")).get === List(stuartSmalley, johnStewart, stuartHalloway)

    // Bind relation
    person(List(("John", "Stewart"), ("Stuart", "Halloway"))).get === List(johnStewart, stuartHalloway)
  }
}