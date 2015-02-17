package molecule.semantics.schema

import molecule.dsl.schemaDefinition._

@InOut(3, 22)
trait CoreTestDefinition {

  trait Ns {
    val str    = oneString.fullTextSearch
    val int    = oneInt
    val long   = oneLong
    val float  = oneFloat
    val double = oneDouble
    val bool   = oneBoolean
    val date   = oneDate
    val uuid   = oneUUID
    val uri    = oneURI
    val enum   = oneEnum('enum0, 'enum1, 'enum2, 'enum3, 'enum4)
    val ref    = one[Ref1]

    val strs    = manyString.fullTextSearch
    val ints    = manyInt
    val longs   = manyLong
    val floats  = manyFloat
    val doubles = manyDouble
    val dates   = manyDate
    val uuids   = manyUUID
    val uris    = manyURI
    val enums   = manyEnum('enum0, 'enum1, 'enum2, 'enum3, 'enum4)
    val refs    = many[Ref1]
  }

  trait Ref1 {
    val str    = oneString.fullTextSearch
    val int    = oneInt
    val long   = oneLong
    val float  = oneFloat
    val double = oneDouble
    val bool   = oneBoolean
    val date   = oneDate
    val uuid   = oneUUID
    val uri    = oneURI
    val enum   = oneEnum('enum0, 'enum1, 'enum2, 'enum3, 'enum4)
    val ref    = one[Ref2]

    val strs    = manyString.fullTextSearch
    val ints    = manyInt
    val longs   = manyLong
    val floats  = manyFloat
    val doubles = manyDouble
    val dates   = manyDate
    val uuids   = manyUUID
    val uris    = manyURI
    val enums   = manyEnum('enum0, 'enum1, 'enum2, 'enum3, 'enum4)
    val refs    = many[Ref2]
  }

  trait Ref2 {
    val str    = oneString.fullTextSearch
    val int    = oneInt
    val long   = oneLong
    val float  = oneFloat
    val double = oneDouble
    val bool   = oneBoolean
    val date   = oneDate
    val uuid   = oneUUID
    val uri    = oneURI
    val enum   = oneEnum('enum0, 'enum1, 'enum2, 'enum3, 'enum4)

    val strs    = manyString.fullTextSearch
    val ints    = manyInt
    val longs   = manyLong
    val floats  = manyFloat
    val doubles = manyDouble
    val dates   = manyDate
    val uuids   = manyUUID
    val uris    = manyURI
    val enums   = manyEnum('enum0, 'enum1, 'enum2, 'enum3, 'enum4)
  }
}