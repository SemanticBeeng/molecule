package molecule

import java.net.URI
import java.util.{Date, UUID}

import molecule.util.dsl.coreTest._
import molecule.util.{CoreSetup, CoreSpec}
import shapeless._

class InsertMany extends CoreSpec {


  "1 attribute" in new CoreSetup {

    // The `insert` method performs the compile time analysis of the molecule
    // The `apply` method inserts the type-inferred data at runtime
    Ns.strs.insert.apply(Set("a"))

    // We can enter data for one attribute in 4 different ways:

    // 1. Set of values
    Ns.strs insert Set("b")
    Ns.strs insert Set("c", "d")

    // 2. Comma-separated list of sets of values
    Ns.strs.insert(Set("e"), Set("f"))

    // 3. List of sets of values
    Ns.strs insert List(Set("g"))
    Ns.strs insert List(Set("h", "i"))
    Ns.strs insert List(Set("j"), Set("k"))

    // 4. Arity-1 HList
    Ns.strs insert Set("l") :: HNil
    Ns.strs insert Set("m", "n") :: HNil

    // 5. List of Arity-1 HLists
    Ns.strs insert List(Set("o") :: HNil)
    Ns.strs insert List(Set("p", "q") :: HNil)
    Ns.strs insert List(Set("r") :: HNil, Set("s") :: HNil)

    // All values inserted
    Ns.strs.one.toSeq.sorted === List("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s")


    Ns.ints.insert(Set(1))
    Ns.ints.insert(Set(2, 3))
    Ns.ints.insert(Set(4), Set(5))
    Ns.ints.insert(List(Set(6)))
    Ns.ints.insert(List(Set(7, 8)))
    Ns.ints.insert(List(Set(9), Set(10)))

    Ns.ints.insert(Set(11) :: HNil)
    Ns.ints.insert(Set(12, 13) :: HNil)
    Ns.ints.insert(List(Set(14) :: HNil))
    Ns.ints.insert(List(Set(15, 16) :: HNil))
    Ns.ints.insert(List(Set(17) :: HNil, Set(18) :: HNil))

    Ns.ints.one.toSeq.sorted === List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)


    Ns.longs.insert(Set(1L))
    Ns.longs.insert(Set(2L, 3L))
    Ns.longs.insert(Set(4L), Set(5L))
    Ns.longs.insert(List(Set(6L)))
    Ns.longs.insert(List(Set(7L, 8L)))
    Ns.longs.insert(List(Set(9L), Set(10L)))

    Ns.longs.insert(Set(11L) :: HNil)
    Ns.longs.insert(Set(12L, 13L) :: HNil)
    Ns.longs.insert(List(Set(14L) :: HNil))
    Ns.longs.insert(List(Set(15L, 16L) :: HNil))
    Ns.longs.insert(List(Set(17L) :: HNil, Set(18L) :: HNil))

    Ns.longs.one.toSeq.sorted === List(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L)


    Ns.floats.insert(Set(1.0f))
    Ns.floats.insert(Set(2.0f, 3.0f))
    Ns.floats.insert(Set(4.0f), Set(5.0f))
    Ns.floats.insert(List(Set(6.0f)))
    Ns.floats.insert(List(Set(7.0f, 8.0f)))
    Ns.floats.insert(List(Set(9.0f), Set(10.0f)))

    Ns.floats.insert(Set(11.0f) :: HNil)
    Ns.floats.insert(Set(12.0f, 13.0f) :: HNil)
    Ns.floats.insert(List(Set(14.0f) :: HNil))
    Ns.floats.insert(List(Set(15.0f, 16.0f) :: HNil))
    Ns.floats.insert(List(Set(17.0f) :: HNil, Set(18.0f) :: HNil))

    Ns.floats.one.toSeq.sorted === List(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f, 16.0f, 17.0f, 18.0f)


    Ns.doubles.insert(Set(1.0))
    Ns.doubles.insert(Set(2.0, 3.0))
    Ns.doubles.insert(Set(4.0), Set(5.0))
    Ns.doubles.insert(List(Set(6.0)))
    Ns.doubles.insert(List(Set(7.0, 8.0)))
    Ns.doubles.insert(List(Set(9.0), Set(10.0)))

    Ns.doubles.insert(Set(11.0) :: HNil)
    Ns.doubles.insert(Set(12.0, 13.0) :: HNil)
    Ns.doubles.insert(List(Set(14.0) :: HNil))
    Ns.doubles.insert(List(Set(15.0, 16.0) :: HNil))
    Ns.doubles.insert(List(Set(17.0) :: HNil, Set(18.0) :: HNil))

    Ns.doubles.one.toSeq.sorted === List(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0)


    Ns.dates.insert(Set(date1))
    Ns.dates.insert(Set(date2, date3))
    Ns.dates.insert(Set(date4), Set(date5))
    Ns.dates.insert(List(Set(date6)))
    Ns.dates.insert(List(Set(date7, date8)))
    Ns.dates.insert(List(Set(date9), Set(date10)))

    Ns.dates.insert(Set(date11) :: HNil)
    Ns.dates.insert(Set(date12, date13) :: HNil)
    Ns.dates.insert(List(Set(date14) :: HNil))
    Ns.dates.insert(List(Set(date15, date16) :: HNil))
    Ns.dates.insert(List(Set(date17) :: HNil, Set(date18) :: HNil))

    Ns.dates.one.toSeq.sorted === List(date1, date2, date3, date4, date5, date6, date7, date8, date9, date10, date11, date12, date13, date14, date15, date16, date17, date18)


    Ns.uuids.insert(Set(uuid1))
    Ns.uuids.insert(Set(uuid2, uuid3))
    Ns.uuids.insert(Set(uuid4), Set(uuid5))
    Ns.uuids.insert(List(Set(uuid6)))
    Ns.uuids.insert(List(Set(uuid7, uuid8)))
    Ns.uuids.insert(List(Set(uuid9), Set(uuid10)))

    Ns.uuids.insert(Set(uuid11) :: HNil)
    Ns.uuids.insert(Set(uuid12, uuid13) :: HNil)
    Ns.uuids.insert(List(Set(uuid14) :: HNil))
    Ns.uuids.insert(List(Set(uuid15, uuid16) :: HNil))
    Ns.uuids.insert(List(Set(uuid17) :: HNil, Set(uuid18) :: HNil))

    Ns.uuids.one.toSeq.sortBy(_.toString) === List(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7, uuid8, uuid9, uuid10, uuid11, uuid12, uuid13, uuid14, uuid15, uuid16, uuid17, uuid18)


    Ns.uris.insert(Set(uri1))
    Ns.uris.insert(Set(uri2, uri3))
    Ns.uris.insert(Set(uri4), Set(uri5))
    Ns.uris.insert(List(Set(uri6)))
    Ns.uris.insert(List(Set(uri7, uri8)))
    Ns.uris.insert(List(Set(uri9), Set(uri10)))

    Ns.uris.insert(Set(uri11) :: HNil)
    Ns.uris.insert(Set(uri12, uri13) :: HNil)
    Ns.uris.insert(List(Set(uri14) :: HNil))
    Ns.uris.insert(List(Set(uri15, uri16) :: HNil))
    Ns.uris.insert(List(Set(uri17) :: HNil, Set(uri18) :: HNil))

    Ns.uris.one.toSeq.sortBy(_.toString) === List(uri1, uri10, uri11, uri12, uri13, uri14, uri15, uri16, uri17, uri18, uri2, uri3, uri4, uri5, uri6, uri7, uri8, uri9)


    Ns.enums.insert(Set(enum1))
    Ns.enums.insert(Set(enum2, enum3))
    Ns.enums.insert(Set(enum4), Set(enum5))
    Ns.enums.insert(List(Set(enum6)))
    Ns.enums.insert(List(Set(enum7, enum8)))
    Ns.enums.insert(List(Set(enum9), Set(enum0)))

    Ns.enums.insert(Set(enum1) :: HNil)
    Ns.enums.insert(Set(enum2, enum3) :: HNil)
    Ns.enums.insert(List(Set(enum4) :: HNil))
    Ns.enums.insert(List(Set(enum5, enum6) :: HNil))
    Ns.enums.insert(List(Set(enum7) :: HNil, Set(enum8) :: HNil))

    Ns.enums.one.toSeq.sorted === List(enum0, enum1, enum2, enum3, enum4, enum5, enum6, enum7, enum8, enum9)
  }


  "Data-molecule, 1 attr" in new CoreSetup {

    // Construct a "Data-Molecule" with an attribute value and add it to the database

    Ns.strs("a").add
    Ns.strs("b", "c").add
    Ns.strs.one === Set("a", "b", "c")

    Ns.ints(1).add
    Ns.ints(2, 3).add
    Ns.ints.one === Set(1, 2, 3)

    Ns.longs(1L).add
    Ns.longs(2L, 3L).add
    Ns.longs.one === Set(1L, 2L, 3L)

    Ns.floats(1.0f).add
    Ns.floats(2.0f, 3.0f).add
    Ns.floats.one === Set(1.0f, 2.0f, 3.0f)

    Ns.doubles(1.0).add
    Ns.doubles(2.0, 3.0).add
    Ns.doubles.one === Set(1.0, 2.0, 3.0)

    // Ns.bools not implemented...

    Ns.dates(date1).add
    Ns.dates(date2, date3).add
    Ns.dates.one === Set(date1, date2, date3)

    Ns.uuids(uuid1).add
    Ns.uuids(uuid2, uuid3).add
    Ns.uuids.one === Set(uuid1, uuid2, uuid3)

    Ns.uris(uri1).add
    Ns.uris(uri2, uri3).add
    Ns.uris.one === Set(uri1, uri2, uri3)

    Ns.enums("enum1").add
    Ns.enums("enum2", "enum3").add
    Ns.enums.one === Set("enum1", "enum2", "enum3")
  }


  "Data-molecule, n attrs" in new CoreSetup {

    // Construct a "Data-Molecule" with multiple attributes populated with data and add it to the database

    Ns.strs("a", "b")
      .ints(1, 2)
      .longs(1L, 2L)
      .floats(1.0f, 2.0f)
      .doubles(1.0, 2.0)
      .dates(date1, date2)
      .uuids(uuid1, uuid2)
      .uris(uri1, uri2)
      .enums("enum1", "enum2").add

    Ns.strs.ints.longs.floats.doubles.dates.uuids.uris.enums.one ===(
      Set("a", "b"),
      Set(1, 2),
      Set(1L, 2L),
      Set(1.0f, 2.0f),
      Set(1.0, 2.0),
      Set(date1, date2),
      Set(uuid1, uuid2),
      Set(uri1, uri2),
      Set("enum1", "enum2"))
  }


  " Insert-molecule n attrs" in new CoreSetup {

    // Insert 3 entities as tuples of values
    // Note that values are typechecked against the attribute types of the molecule
    Ns.strs.ints.longs.floats.doubles.dates.uuids.uris.enums insert List(
      (Set("a", "b"),
        Set(1, 2),
        Set(1L, 2L),
        Set(1.0f, 2.0f),
        Set(1.0, 2.0),
        Set(date1, date2),
        Set(uuid1, uuid2),
        Set(uri1, uri2),
        Set("enum1", "enum2")),
      (Set("c", "d"),
        Set(3, 4),
        Set(3L, 4L),
        Set(3.0f, 4.0f),
        Set(3.0, 4.0),
        Set(date3, date4),
        Set(uuid3, uuid4),
        Set(uri3, uri4),
        Set("enum3", "enum4"))
    )

    // Unique values coalesced
    Ns.strs.ints.longs.floats.doubles.dates.uuids.uris.enums.one ===(
      Set("d", "a", "b", "c"),
      Set(1, 4, 3, 2),
      Set(1L, 4L, 3L, 2L),
      Set(2.0f, 4.0f, 1.0f, 3.0f),
      Set(2.0, 4.0, 1.0, 3.0),
      Set(date4, date1, date2, date3),
      Set(uuid3, uuid2, uuid4, uuid1),
      Set(uri1, uri2, uri3, uri4),
      Set("enum4", "enum1", "enum3", "enum2"))
  }


  " Insert-molecule (2-step insertion)" in new CoreSetup {

    // 1. Define "Insert-molecule"
    val insertStrs = Ns.strs.insert

    // 2. Re-use Insert-molecule to insert values
    insertStrs(Set("a"))
    insertStrs(Set("b", "c"))

    Ns.strs.one === List("a", "b", "c")


    val insertAlls = Ns.strs.ints.longs.floats.doubles.dates.uuids.uris.enums.insert

    insertAlls(Set(" "), Set(0), Set(0L), Set(0.0f), Set(0.0), Set(date0), Set(uuid0), Set(uri0), Set("enum0"))
    insertAlls(List(
      (Set("a"), Set(1), Set(1L), Set(1.0f), Set(1.0), Set(date1), Set(uuid1), Set(uri1), Set("enum1")),
      (Set("b"), Set(2), Set(2L), Set(2.0f), Set(2.0), Set(date2), Set(uuid2), Set(uri2), Set("enum2"))
    ))

    Ns.strs.ints.longs.floats.doubles.dates.uuids.uris.enums.one ===(
      Set("a", "b", " "),
      Set(0, 1, 2),
      Set(0L, 1L, 2L),
      Set(0.0f, 2.0f, 1.0f),
      Set(0.0, 2.0, 1.0),
      Set(date0, date1, date2),
      Set(uuid0, uuid1, uuid2),
      Set(uri0, uri1, uri2),
      Set("enum1", "enum0", "enum2"))
  }


  " Insert inconsistent data sets" in new CoreSetup {

    // If we have an inconsistent data set we can use typed `null` as
    // a placeholder for a missing value. When Molecule encounters a null
    // value it won't assert a fact about that attribute (simply skipping it is
    // different from for instance in SQL where a NULL value could be inserted).

    Ns.strs.ints.longs.floats.doubles.dates.uuids.uris.enums insert List(
      (null.asInstanceOf[Set[String]], Set(0), Set(0L), Set(0.0f), Set(0.0), Set(date0), Set(uuid0), Set(uri0), Set("enum0")),
      (Set("a"), null.asInstanceOf[Set[Int]], Set(1L), Set(1.0f), Set(1.0), Set(date1), Set(uuid1), Set(uri1), Set("enum1")),
      (Set("b"), Set(2), null.asInstanceOf[Set[Long]], Set(2.0f), Set(2.0), Set(date2), Set(uuid2), Set(uri2), Set("enum2")),
      (Set("c"), Set(3), Set(3L), null.asInstanceOf[Set[Float]], Set(3.0), Set(date3), Set(uuid3), Set(uri3), Set("enum3")),
      (Set("d"), Set(4), Set(4L), Set(4.0f), null.asInstanceOf[Set[Double]], Set(date4), Set(uuid4), Set(uri4), Set("enum4")),
      (Set("e"), Set(5), Set(5L), Set(5.0f), Set(5.0), null.asInstanceOf[Set[Date]], Set(uuid5), Set(uri5), Set("enum5")),
      (Set("f"), Set(6), Set(6L), Set(6.0f), Set(6.0), Set(date6), null.asInstanceOf[Set[UUID]], Set(uri6), Set("enum6")),
      (Set("g"), Set(7), Set(7L), Set(7.0f), Set(7.0), Set(date7), Set(uuid7), null.asInstanceOf[Set[URI]], Set("enum7")),
      (Set("h"), Set(8), Set(8L), Set(8.0f), Set(8.0), Set(date8), Set(uuid8), Set(uri8), null.asInstanceOf[Set[String]])
    )

    // Null values haven't been asserted:

    // View created entities:

    // Without str
    Ns.ints.longs.floats.doubles.dates.uuids.uris.enums.one ===(Set(0), Set(0L), Set(0.0f), Set(0.0), Set(date0), Set(uuid0), Set(uri0), Set("enum0"))
    // Without int
    Ns.strs.longs.floats.doubles.dates.uuids.uris.enums.one ===(Set("a"), Set(1L), Set(1.0f), Set(1.0), Set(date1), Set(uuid1), Set(uri1), Set("enum1"))
    // Without long
    Ns.strs.ints.floats.doubles.dates.uuids.uris.enums.one ===(Set("b"), Set(2), Set(2.0f), Set(2.0), Set(date2), Set(uuid2), Set(uri2), Set("enum2"))
    // Without float
    Ns.strs.ints.longs.doubles.dates.uuids.uris.enums.one ===(Set("c"), Set(3), Set(3L), Set(3.0), Set(date3), Set(uuid3), Set(uri3), Set("enum3"))
    // Without double
    Ns.strs.ints.longs.floats.dates.uuids.uris.enums.one ===(Set("d"), Set(4), Set(4L), Set(4.0f), Set(date4), Set(uuid4), Set(uri4), Set("enum4"))
    // Without date
    Ns.strs.ints.longs.floats.doubles.uuids.uris.enums.one ===(Set("e"), Set(5), Set(5L), Set(5.0f), Set(5.0), Set(uuid5), Set(uri5), Set("enum5"))
    // Without uuid
    Ns.strs.ints.longs.floats.doubles.dates.uris.enums.one ===(Set("f"), Set(6), Set(6L), Set(6.0f), Set(6.0), Set(date6), Set(uri6), Set("enum6"))
    // Without uri
    Ns.strs.ints.longs.floats.doubles.dates.uuids.enums.one ===(Set("g"), Set(7), Set(7L), Set(7.0f), Set(7.0), Set(date7), Set(uuid7), Set("enum7"))
    // Without enum
    Ns.strs.ints.longs.floats.doubles.dates.uuids.uris.one ===(Set("h"), Set(8), Set(8L), Set(8.0f), Set(8.0), Set(date8), Set(uuid8), Set(uri8))


    // View attributes:

    // No value " "
    Ns.strs.one === Set("a", "b", "c", "d", "e", "f", "g", "h")
    // No value 1
    Ns.ints.one === Set(0, 2, 3, 4, 5, 6, 7, 8)
    // No value 2L
    Ns.longs.one === Set(0L, 1L, 3L, 4L, 5L, 6L, 7L, 8L)
    // No value 3.0f
    Ns.floats.one === Set(0.0f, 1.0f, 2.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f)
    // No value 4.0
    Ns.doubles.one === Set(0.0, 1.0, 2.0, 3.0, 5.0, 6.0, 7.0, 8.0)
    // No value date5
    Ns.dates.one === Set(date0, date7, date2, date6, date3, date4, date8, date1)
    // No value uuid6
    Ns.uuids.one === Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid7, uuid8, uuid0)
    // No value uri7
    Ns.uris.one === Set(uri2, uri5, uri1, uri0, uri6, uri4, uri3, uri8)
    // No value enum8
    Ns.enums.one === Set(enum0, enum1, enum2, enum3, enum4, enum5, enum6, enum7)
  }
}