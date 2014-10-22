package molecule.util.dsl
import java.net.URI
import java.util.{UUID, Date}
import molecule._
import molecule.ast.model._


object schemaDSL {

  // todo?
  trait Partition

  trait Tree
  trait HyperEdge

  trait NS

  // Dummy types used with generated trait types

  type P0 = Nothing
  type P1[A] = Nothing
  type P2[A, B] = Nothing
  type P3[A, B, C] = Nothing
  type P4[A, B, C, D] = Nothing
  type P5[A, B, C, D, E] = Nothing
  type P6[A, B, C, D, E, F] = Nothing
  type P7[A, B, C, D, E, F, G] = Nothing
  type P8[A, B, C, D, E, F, G, H] = Nothing
  type P9[A, B, C, D, E, F, G, H, I] = Nothing
  type P10[A, B, C, D, E, F, G, H, I, J] = Nothing
  type P11[A, B, C, D, E, F, G, H, I, J, K] = Nothing
  type P12[A, B, C, D, E, F, G, H, I, J, K, L] = Nothing
  type P13[A, B, C, D, E, F, G, H, I, J, K, L, M] = Nothing
  type P14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = Nothing
  type P15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = Nothing
  type P16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = Nothing
  type P17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = Nothing
  type P18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = Nothing
  type P19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = Nothing
  type P20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = Nothing
  type P21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = Nothing
  type P22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = Nothing
  type P23[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, X] = Nothing
  type P24[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, X, Y] = Nothing
  type P25[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, X, Y, Z] = Nothing
  type P26[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, X, Y, Z, AA] = Nothing
  type P27[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, X, Y, Z, AA, AB] = Nothing


  trait Ref[Ns1, Ns2]
  trait OneRef[Ns1, Ns2] extends Ref[Ns1, Ns2]
  trait ManyRef[Ns1, Ns2] extends Ref[Ns1, Ns2]
//  trait BackRef[BackRefNS, ThisNs] extends Ref[BackRefNS, ThisNs]
  trait ChildRef[Ns1] extends Ref[Ns1, Ns1]
  trait HyperRef[Ns1] extends Ref[Ns1, Ns1]


  trait Attr

  trait RefAttr[Ns1, T] extends Attr

  trait OneRefAttr[Ns, In] extends RefAttr[Ns,  Long] {
    def apply(value: Long): Ns = ???
  }
  trait ManyRefAttr[Ns, In] extends RefAttr[Ns,  Long] {
    def apply(value: Long*): Ns = ???
    def add(value: Long): Ns = ???
    def remove(values: Long*): Ns = ???
  }

  sealed trait ValueAttr[Ns, In, T] extends Attr {
    def apply(expr: Exp1[T]) : Ns = ???
    def eq(value: T) : Ns = ???

    def < (value: T) : Ns = ???
    def > (value: T) : Ns = ???
    def <= (value: T) : Ns = ???
    def >= (value: T) : Ns = ???

    // Input
    def < (in: ?) : In = ???
    def > (in: ?) : In = ???
    def <= (in: ?) : In = ???
    def >= (in: ?) : In = ???

    def apply(in: ?) : In = ???

    // Optional value (will return Option[T])
    //    def apply(m: maybe): Ns = ???
  }

  // One-cardinality
  trait One[Ns, In, T] extends ValueAttr[Ns, In, T] {
    // Request for no value!
    def apply(): Ns = ???
    def apply(values: Seq[T]) : Ns = ???
  }
  trait OneString [Ns, In] extends One[Ns, In, String ]  {
    def length: Ns = ???
  }
  trait OneInt    [Ns, In] extends One[Ns, In, Int    ]
  trait OneLong   [Ns, In] extends One[Ns, In, Long   ]
  trait OneFloat  [Ns, In] extends One[Ns, In, Float  ]
  trait OneDouble [Ns, In] extends One[Ns, In, Double ]
  trait OneDate   [Ns, In] extends One[Ns, In, Date   ]
  trait OneBoolean[Ns, In] extends One[Ns, In, Boolean]
  trait OneUUID   [Ns, In] extends One[Ns, In, UUID   ]
  trait OneURI    [Ns, In] extends One[Ns, In, URI    ]
  trait OneAny    [Ns, In] extends One[Ns, In, Any    ]

  // Many-cardinality
  trait Many[Ns, In, S, T] extends ValueAttr[Ns, In, T] {
    def apply(value: T*): Ns = ???
//    def apply(one: T, more: T*): Ns = ???
//    def apply(): Ns = ???

//    def apply(values: Seq[T]): Ns = ???
    def apply(oldNew: (T, T), oldNewMore: (T, T)*): Ns = ???
    //    def apply(h: Seq[(T, T)]): Ns = ???
    def add(value: T): Ns = ???
    def remove(values: T*): Ns = ???
  }
  trait ManyString[Ns, In] extends Many[Ns, In, Set[String], String]
  trait ManyInt   [Ns, In] extends Many[Ns, In, Set[Int]   , Int   ]
  trait ManyLong  [Ns, In] extends Many[Ns, In, Set[Long]  , Long  ]
  trait ManyFloat [Ns, In] extends Many[Ns, In, Set[Float] , Float ]
  trait ManyDouble[Ns, In] extends Many[Ns, In, Set[Double], Double]
  trait ManyDate  [Ns, In] extends Many[Ns, In, Set[Date]  , Date  ]
  trait ManyUUID  [Ns, In] extends Many[Ns, In, Set[UUID]  , UUID  ]
  trait ManyURI   [Ns, In] extends Many[Ns, In, Set[URI]   , URI   ]

  // Enums
  trait Enum
  trait OneEnum  [Ns, In] extends One [Ns, In, String]              with Enum
  trait ManyEnums[Ns, In] extends Many[Ns, In, Set[String], String] with Enum
  object EnumValue

  // Attribute options
  case class Doc(msg: String)
  trait UniqueValue
  trait UniqueIdentity
  trait Indexed
  trait FulltextSearch[Ns, In] {
    def contains(that: String): Ns = ???
    def contains(in: ?) : In = ???
  }
  trait IsComponent
  trait NoHistory
}