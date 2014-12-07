package molecule.examples.dayOfDatomic

import molecule._
import molecule.examples.dayOfDatomic.spec.DayOfAtomicSpec
import molecule.schemas.Db


class AttributeGroups extends DayOfAtomicSpec {

  "Attribute groups" in new SocialNewsSetup {

    // Find all attributes in the story namespace
    Db.a.ns_("story").get === List(":story/title", ":story/url")

    // Create a reusable rule
    val attrInNs = m(Db.a.ns_(?))

    // Find all attributes in story namespace, using the rule
    attrInNs("story").get === List(":story/title", ":story/url")

    // Find all entities possessing *any* story attribute (the 3 stories)
    Db.e.ns_("story").get.sorted === List(s1, s2, s3)
  }
}