package molecule
package attrMap
import molecule.util.dsl.coreTest._
//import datomic.Peer
//import molecule.util.expectCompileError

class Values extends Base {


  "Type checks" in new Setup {

    // All mapped values
    Ns.int.strMap.get === List(
      (1, Map("en" -> "Hi there", "da" -> "Hejsa")),
      (2, Map("en" -> "Oh, Hi", "da" -> "Hilser", "fr" -> "Bonjour", "it" -> "Bon giorno")),
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )
    Ns.int.intMap.get === List(
      (1, Map("en" -> 10, "da" -> 30)),
      (2, Map("en" -> 10, "da" -> 10, "fr" -> 20, "it" -> 30)),
      (3, Map("en" -> 30, "da" -> 30)),
      (4, Map("da" -> 30))
    )
    Ns.int.longMap.get === List(
      (1, Map("en" -> 10L, "da" -> 30L)),
      (2, Map("en" -> 10L, "da" -> 10L, "fr" -> 20L, "it" -> 30L)),
      (3, Map("en" -> 30L, "da" -> 30L)),
      (4, Map("da" -> 30L))
    )
    Ns.int.floatMap.get === List(
      (1, Map("en" -> 10f, "da" -> 30f)),
      (2, Map("en" -> 10f, "da" -> 10f, "fr" -> 20f, "it" -> 30f)),
      (3, Map("en" -> 30f, "da" -> 30f)),
      (4, Map("da" -> 30f))
    )
    Ns.int.doubleMap.get === List(
      (1, Map("en" -> 10.0, "da" -> 30.0)),
      (2, Map("en" -> 10.0, "da" -> 10.0, "fr" -> 20.0, "it" -> 30.0)),
      (3, Map("en" -> 30.0, "da" -> 30.0)),
      (4, Map("da" -> 30.0))
    )
    Ns.int.boolMap.get === List(
      (1, Map("en" -> true, "da" -> false)),
      (2, Map("en" -> true, "da" -> true, "fr" -> false, "it" -> false)),
      (3, Map("en" -> false, "en" -> false)),
      (4, Map("da" -> false))
    )
    Ns.int.dateMap.get === List(
      (1, Map("en" -> date1, "da" -> date3)),
      (2, Map("en" -> date1, "da" -> date1, "fr" -> date2, "it" -> date3)),
      (3, Map("en" -> date3, "da" -> date3)),
      (4, Map("da" -> date3))
    )
    Ns.int.uuidMap.get === List(
      (1, Map("en" -> uuid1, "da" -> uuid3)),
      (2, Map("en" -> uuid1, "da" -> uuid1, "fr" -> uuid2, "it" -> uuid3)),
      (3, Map("en" -> uuid3, "da" -> uuid3)),
      (4, Map("da" -> uuid3))
    )
    Ns.int.uriMap.get === List(
      (1, Map("en" -> uri1, "da" -> uri3)),
      (2, Map("en" -> uri1, "da" -> uri1, "fr" -> uri2, "it" -> uri3)),
      (3, Map("en" -> uri3, "da" -> uri3)),
      (4, Map("da" -> uri3))
    )
  }


  "Equality" in new Setup {

    // As with normal attributes we can search for values by applying an argument.
    // All asserted keyed values are searched to find a match and matched
    // key/value pairs are returned
    Ns.int.strMap("Hello").get === List(
      (3, Map("en" -> "Hello"))
    )
    // Note that text searches for attribute maps are case-sensitive
    Ns.int.strMap("hello").get === List()

    Ns.int.intMap(10).get === List(
      (1, Map("en" -> 10)),
      (2, Map("en" -> 10, "da" -> 10))
    )
    Ns.int.longMap(10L).get === List(
      (1, Map("en" -> 10L)),
      (2, Map("en" -> 10L, "da" -> 10L))
    )
    Ns.int.floatMap(10f).get === List(
      (1, Map("en" -> 10f)),
      (2, Map("en" -> 10f, "da" -> 10f))
    )
    Ns.int.doubleMap(10.0).get === List(
      (1, Map("en" -> 10.0)),
      (2, Map("en" -> 10.0, "da" -> 10.0))
    )
    Ns.int.boolMap(true).get === List(
      (1, Map("en" -> true)),
      (2, Map("en" -> true, "da" -> true))
    )
    Ns.int.dateMap(date1).get === List(
      (1, Map("en" -> date1)),
      (2, Map("en" -> date1, "da" -> date1))
    )
    Ns.int.uuidMap(uuid1).get === List(
      (1, Map("en" -> uuid1)),
      (2, Map("en" -> uuid1, "da" -> uuid1))
    )
    Ns.int.uriMap(uri1).get === List(
      (1, Map("en" -> uri1)),
      (2, Map("en" -> uri1, "da" -> uri1))
    )
  }


  "Regex/partial search" in new Setup {

    // We can search text strings with regular expressions
    Ns.int.strMap(".*He.*").get === List(
      (1, Map("da" -> "Hejsa")),
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )

    // The above search is identical to using contains
    Ns.int.strMap.contains("He").get === List(
      (1, Map("da" -> "Hejsa")),
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )

    // Note that text searches for attribute maps are case-sensitive
    Ns.int.strMap(".*he.*").get === List(
      (1, Map("en" -> "Hi there"))
    )
    Ns.int.strMap.contains("he").get === List(
      (1, Map("en" -> "Hi there"))
    )

    // Multiple needles
    Ns.int.strMap.contains("He", "jour").get === List(
      (1, Map("da" -> "Hejsa")),
      (2, Map("fr" -> "Bonjour")),
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )
  }


  "Negation" in new Setup {

    // String

    Ns.int(2).strMap.!=("Bon giorno").get === List(
      (2, Map("en" -> "Oh, Hi", "da" -> "Hilser", "fr" -> "Bonjour"))
    )
    // Omit multiple values
    Ns.int(2).strMap.!=("Bonjour", "Bon giorno").get === List(
      (2, Map("en" -> "Oh, Hi", "da" -> "Hilser"))
    )


    // Int

    Ns.int.intMap.!=(10).get === List(
      (1, Map("da" -> 30)),
      (2, Map("fr" -> 20, "it" -> 30)),
      (3, Map("en" -> 30, "da" -> 30)),
      (4, Map("da" -> 30))
    )
    Ns.int.intMap.!=(10, 30).get === List(
      (2, Map("fr" -> 20))
    )
    // Tacet omitted attribute map values
    Ns.int.intMap_.!=(10, 30).get === List(2)


    // Boolean

    Ns.int.boolMap.!=(true).get === List(
      (1, Map("da" -> false)),
      (2, Map("fr" -> false, "it" -> false)),
      (3, Map("en" -> false, "en" -> false)),
      (4, Map("da" -> false))
    )
    // Should be same as
    Ns.int.boolMap(false).get === List(
      (1, Map("da" -> false)),
      (2, Map("fr" -> false, "it" -> false)),
      (3, Map("en" -> false, "en" -> false)),
      (4, Map("da" -> false))
    )
    Ns.int.boolMap_.!=(true).get === List(1, 2, 3, 4)
    Ns.int.boolMap_(false).get === List(1, 2, 3, 4)

    Ns.int.boolMap.!=(false).get === List(
      (1, Map("en" -> true)),
      (2, Map("en" -> true, "da" -> true))
    )
    // Should be same as
    Ns.int.boolMap(true).get === List(
      (1, Map("en" -> true)),
      (2, Map("en" -> true, "da" -> true))
    )
    Ns.int.boolMap_.!=(false).get === List(1, 2)
    Ns.int.boolMap_(true).get === List(1, 2)

    // Well...
    Ns.int.boolMap_.!=(true, false).get === List()
    Ns.int.boolMap_(true, false).get === List(1, 2, 3, 4)


    // Date

    Ns.int.dateMap.!=(date1).get === List(
      (1, Map("da" -> date3)),
      (2, Map("fr" -> date2, "it" -> date3)),
      (3, Map("en" -> date3, "da" -> date3)),
      (4, Map("da" -> date3))
    )
    Ns.int.dateMap.!=(date1, date3).get === List(
      (2, Map("fr" -> date2))
    )
    // Tacet omitted attribute map values
    Ns.int.dateMap_.!=(date1, date3).get === List(2)


    // UUID

    Ns.int.uuidMap.!=(uuid1).get === List(
      (1, Map("da" -> uuid3)),
      (2, Map("fr" -> uuid2, "it" -> uuid3)),
      (3, Map("en" -> uuid3, "da" -> uuid3)),
      (4, Map("da" -> uuid3))
    )
    Ns.int.uuidMap.!=(uuid1, uuid3).get === List(
      (2, Map("fr" -> uuid2))
    )
    // Tacet omitted attribute map values
    Ns.int.uuidMap_.!=(uuid1, uuid3).get === List(2)


    // URI

    Ns.int.uriMap.!=(uri1).get === List(
      (1, Map("da" -> uri3)),
      (2, Map("fr" -> uri2, "it" -> uri3)),
      (3, Map("en" -> uri3, "da" -> uri3)),
      (4, Map("da" -> uri3))
    )
    Ns.int.uriMap.!=(uri1, uri3).get === List(
      (2, Map("fr" -> uri2))
    )
    // Tacet omitted attribute map values
    Ns.int.uriMap_.!=(uri1, uri3).get === List(2)
  }


  "Comparison" in new Setup {

    // String

    // Values after 'H'

    // When comparing strings, note that two or more
    // letter starting with 'H' is 'greater than H'
    // (See java.util.String.compareTo)
    Ns.int.strMap.>("H").get === List(
      (1, Map("en" -> "Hi there", "da" -> "Hejsa")),
      (2, Map("en" -> "Oh, Hi", "da" -> "Hilser")),
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )
    // Thus this turns out to be the same as (except for the text string "H")
    Ns.int.strMap.>=("H").get === List(
      (1, Map("en" -> "Hi there", "da" -> "Hejsa")),
      (2, Map("en" -> "Oh, Hi", "da" -> "Hilser")),
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )

    // Values after 'H', again
    // If what we are after is values starting with
    // 'I' or higher, we could say '>= I'
    Ns.int.strMap.>=("I").get === List(
      (2, Map("en" -> "Oh, Hi"))
    )
    // Note that String comparisons are case-sensitive!
    // (lower-case letters come after upper-case letters)
    Ns.int.strMap.>=("i").get === List()

    // Values before or starting with 'H' (that is, _only_ 'H'!)
    Ns.int.strMap.<=("H").get === List(
      (2, Map("fr" -> "Bonjour", "it" -> "Bon giorno"))
    )
    // Again, if what we intended wass to find all values
    // starting with 'H' or before, we should instead say
    Ns.int.strMap.<("I").get === List(
      (1, Map("en" -> "Hi there", "da" -> "Hejsa")),
      (2, Map("da" -> "Hilser", "fr" -> "Bonjour", "it" -> "Bon giorno")),
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )

    // Values before 'H'
    Ns.int.strMap.<("H").get === List(
      (2, Map("fr" -> "Bonjour", "it" -> "Bon giorno"))
    )

    // Entities with str values beginning from 'H'
    Ns.int.strMap_.>=("H").get.sorted === List(1, 2, 3, 4)

    // Entities with str values beginning from 'I'
    Ns.int.strMap_.>=("I").get.sorted === List(2)

    // Entities with str values beginning before 'H'
    Ns.int.strMap_.<("H").get.sorted === List(2)


    // Int

    Ns.int.intMap.>(20).get === List(
      (1, Map("da" -> 30)),
      (2, Map("it" -> 30)),
      (3, Map("en" -> 30, "da" -> 30)),
      (4, Map("da" -> 30))
    )
    Ns.int.intMap.>=(20).get === List(
      (1, Map("da" -> 30)),
      (2, Map("fr" -> 20, "it" -> 30)),
      (3, Map("en" -> 30, "da" -> 30)),
      (4, Map("da" -> 30))
    )
    Ns.int.intMap.<=(20).get === List(
      (1, Map("en" -> 10)),
      (2, Map("en" -> 10, "da" -> 10, "fr" -> 20))
    )
    Ns.int.intMap.<(20).get === List(
      (1, Map("en" -> 10)),
      (2, Map("en" -> 10, "da" -> 10))
    )

    Ns.int.intMap.>(-10).get === List(
      (1, Map("en" -> 10, "da" -> 30)),
      (2, Map("en" -> 10, "da" -> 10, "fr" -> 20, "it" -> 30)),
      (3, Map("en" -> 30, "da" -> 30)),
      (4, Map("da" -> 30))
    )

    Ns.int.intMap_.>(20).get === List(1, 2, 3, 4)
    Ns.int.intMap_.>=(20).get === List(1, 2, 3, 4)
    Ns.int.intMap_.<=(20).get === List(1, 2)
    Ns.int.intMap_.<(20).get === List(1, 2)


    // Float

    Ns.int.floatMap.>(20f).get === List(
      (1, Map("da" -> 30f)),
      (2, Map("it" -> 30f)),
      (3, Map("en" -> 30f, "da" -> 30f)),
      (4, Map("da" -> 30f))
    )
    Ns.int.floatMap.>=(20f).get === List(
      (1, Map("da" -> 30f)),
      (2, Map("fr" -> 20f, "it" -> 30f)),
      (3, Map("en" -> 30f, "da" -> 30f)),
      (4, Map("da" -> 30f))
    )
    Ns.int.floatMap.<=(20f).get === List(
      (1, Map("en" -> 10f)),
      (2, Map("en" -> 10f, "da" -> 10f, "fr" -> 20f))
    )
    Ns.int.floatMap.<(20f).get === List(
      (1, Map("en" -> 10f)),
      (2, Map("en" -> 10f, "da" -> 10f))
    )

    Ns.int.floatMap.>(-10f).get === List(
      (1, Map("en" -> 10f, "da" -> 30f)),
      (2, Map("en" -> 10f, "da" -> 10f, "fr" -> 20f, "it" -> 30f)),
      (3, Map("en" -> 30f, "da" -> 30f)),
      (4, Map("da" -> 30f))
    )

    Ns.int.floatMap_.>(20f).get === List(1, 2, 3, 4)
    Ns.int.floatMap_.>=(20f).get === List(1, 2, 3, 4)
    Ns.int.floatMap_.<=(20f).get === List(1, 2)
    Ns.int.floatMap_.<(20f).get === List(1, 2)


    // Long

    Ns.int.longMap.>(20L).get === List(
      (1, Map("da" -> 30L)),
      (2, Map("it" -> 30L)),
      (3, Map("en" -> 30L, "da" -> 30L)),
      (4, Map("da" -> 30L))
    )
    Ns.int.longMap.>=(20L).get === List(
      (1, Map("da" -> 30L)),
      (2, Map("fr" -> 20L, "it" -> 30L)),
      (3, Map("en" -> 30L, "da" -> 30L)),
      (4, Map("da" -> 30L))
    )
    Ns.int.longMap.<=(20L).get === List(
      (1, Map("en" -> 10L)),
      (2, Map("en" -> 10L, "da" -> 10L, "fr" -> 20L))
    )
    Ns.int.longMap.<(20L).get === List(
      (1, Map("en" -> 10L)),
      (2, Map("en" -> 10L, "da" -> 10L))
    )

    Ns.int.longMap.>(-10L).get === List(
      (1, Map("en" -> 10L, "da" -> 30L)),
      (2, Map("en" -> 10L, "da" -> 10L, "fr" -> 20L, "it" -> 30L)),
      (3, Map("en" -> 30L, "da" -> 30L)),
      (4, Map("da" -> 30L))
    )

    Ns.int.longMap_.>(20L).get === List(1, 2, 3, 4)
    Ns.int.longMap_.>=(20L).get === List(1, 2, 3, 4)
    Ns.int.longMap_.<=(20L).get === List(1, 2)
    Ns.int.longMap_.<(20L).get === List(1, 2)


    // Double

    Ns.int.doubleMap.>(20.0).get === List(
      (1, Map("da" -> 30.0)),
      (2, Map("it" -> 30.0)),
      (3, Map("en" -> 30.0, "da" -> 30.0)),
      (4, Map("da" -> 30.0))
    )
    Ns.int.doubleMap.>=(20.0).get === List(
      (1, Map("da" -> 30.0)),
      (2, Map("fr" -> 20.0, "it" -> 30.0)),
      (3, Map("en" -> 30.0, "da" -> 30.0)),
      (4, Map("da" -> 30.0))
    )
    Ns.int.doubleMap.<=(20.0).get === List(
      (1, Map("en" -> 10.0)),
      (2, Map("en" -> 10.0, "da" -> 10.0, "fr" -> 20.0))
    )
    Ns.int.doubleMap.<(20.0).get === List(
      (1, Map("en" -> 10.0)),
      (2, Map("en" -> 10.0, "da" -> 10.0))
    )

    Ns.int.doubleMap.>(-10.0).get === List(
      (1, Map("en" -> 10.0, "da" -> 30.0)),
      (2, Map("en" -> 10.0, "da" -> 10.0, "fr" -> 20.0, "it" -> 30.0)),
      (3, Map("en" -> 30.0, "da" -> 30.0)),
      (4, Map("da" -> 30.0))
    )

    Ns.int.doubleMap_.>(20.0).get === List(1, 2, 3, 4)
    Ns.int.doubleMap_.>=(20.0).get === List(1, 2, 3, 4)
    Ns.int.doubleMap_.<=(20.0).get === List(1, 2)
    Ns.int.doubleMap_.<(20.0).get === List(1, 2)


    // Date

    Ns.int.dateMap.>(date2).get === List(
      (1, Map("da" -> date3)),
      (2, Map("it" -> date3)),
      (3, Map("en" -> date3, "da" -> date3)),
      (4, Map("da" -> date3))
    )
    Ns.int.dateMap.>=(date2).get === List(
      (1, Map("da" -> date3)),
      (2, Map("fr" -> date2, "it" -> date3)),
      (3, Map("en" -> date3, "da" -> date3)),
      (4, Map("da" -> date3))
    )
    Ns.int.dateMap.<=(date2).get === List(
      (1, Map("en" -> date1)),
      (2, Map("en" -> date1, "da" -> date1, "fr" -> date2))
    )
    Ns.int.dateMap.<(date2).get === List(
      (1, Map("en" -> date1)),
      (2, Map("en" -> date1, "da" -> date1))
    )

    Ns.int.dateMap_.>(date2).get === List(1, 2, 3, 4)
    Ns.int.dateMap_.>=(date2).get === List(1, 2, 3, 4)
    Ns.int.dateMap_.<=(date2).get === List(1, 2)
    Ns.int.dateMap_.<(date2).get === List(1, 2)


    // Comparing Boolean, UUID and URI doesn't make sense
  }


  "OR semantics" in new Setup {

    // String

    // We can look for multiple strings in various ways

    // 1. Comma-separated list
    Ns.int.strMap("Hello", "Hej").get === List(
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )

    // 'or'-separated list not allowed (reserved for key/value pairs)
    // Ns.int.strMap("Hello" or "Hej")

    // 2. Set
    Ns.int.strMap(Set("Hello", "Hej")).get === List(
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )
    // Supplied as variable
    val set = Set("Hello", "Hej")
    Ns.int.strMap(set).get === List(
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )

    // 3. Regex
    Ns.int.strMap("Hello|Hej").get === List(
      (3, Map("en" -> "Hello", "da" -> "Hej")),
      (4, Map("da" -> "Hej"))
    )


    // Int

    Ns.int.intMap(10, 20).get === List(
      (1, Map("en" -> 10)),
      (2, Map("en" -> 10, "da" -> 10, "fr" -> 20))
    )
    Ns.int.intMap(Set(10, 20)).get === List(
      (1, Map("en" -> 10)),
      (2, Map("en" -> 10, "da" -> 10, "fr" -> 20))
    )
    // We can even apply multiple sets
    Ns.int.intMap(Set(10), Set(20)).get === List(
      (1, Map("en" -> 10)),
      (2, Map("en" -> 10, "da" -> 10, "fr" -> 20))
    )
    val intSet = Set(10, 20)
    Ns.int.intMap(intSet).get === List(
      (1, Map("en" -> 10)),
      (2, Map("en" -> 10, "da" -> 10, "fr" -> 20))
    )


    // Long

    Ns.int.longMap(10L, 20L).get === List(
      (1, Map("en" -> 10L)),
      (2, Map("en" -> 10L, "da" -> 10L, "fr" -> 20L))
    )
    Ns.int.longMap(Set(10L, 20L)).get === List(
      (1, Map("en" -> 10L)),
      (2, Map("en" -> 10L, "da" -> 10L, "fr" -> 20L))
    )


    // Float

    Ns.int.floatMap(10f, 20f).get === List(
      (1, Map("en" -> 10f)),
      (2, Map("en" -> 10f, "da" -> 10f, "fr" -> 20f))
    )
    Ns.int.floatMap(Set(10f, 20f)).get === List(
      (1, Map("en" -> 10f)),
      (2, Map("en" -> 10f, "da" -> 10f, "fr" -> 20f))
    )


    // Double

    Ns.int.doubleMap(10.0, 20.0).get === List(
      (1, Map("en" -> 10.0)),
      (2, Map("en" -> 10.0, "da" -> 10.0, "fr" -> 20.0))
    )
    Ns.int.doubleMap(Set(10.0, 20.0)).get === List(
      (1, Map("en" -> 10.0)),
      (2, Map("en" -> 10.0, "da" -> 10.0, "fr" -> 20.0))
    )


    // Date

    Ns.int.dateMap(date1, date2).get === List(
      (1, Map("en" -> date1)),
      (2, Map("en" -> date1, "da" -> date1, "fr" -> date2))
    )
    Ns.int.dateMap(Set(date1, date2)).get === List(
      (1, Map("en" -> date1)),
      (2, Map("en" -> date1, "da" -> date1, "fr" -> date2))
    )


    // UUID

    Ns.int.uuidMap(uuid1, uuid2).get === List(
      (1, Map("en" -> uuid1)),
      (2, Map("en" -> uuid1, "da" -> uuid1, "fr" -> uuid2))
    )
    Ns.int.uuidMap(Set(uuid1, uuid2)).get === List(
      (1, Map("en" -> uuid1)),
      (2, Map("en" -> uuid1, "da" -> uuid1, "fr" -> uuid2))
    )


    // URI

    Ns.int.uriMap(uri1, uri2).get === List(
      (1, Map("en" -> uri1)),
      (2, Map("en" -> uri1, "da" -> uri1, "fr" -> uri2))
    )
    Ns.int.uriMap(Set(uri1, uri2)).get === List(
      (1, Map("en" -> uri1)),
      (2, Map("en" -> uri1, "da" -> uri1, "fr" -> uri2))
    )

    // Searching for multiple Boolean values doesn't make sense...
  }
}