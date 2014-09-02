
import java.net.URI
import java.util.{UUID, Date}
import datomic._
import molecule.ast.model._
import molecule.in._
import molecule.out._
import scala.language.experimental.macros
import scala.language.implicitConversions

package object molecule {

  // Molecules

  implicit def m(dsl: Molecule_0): Molecule0 = macro BuildMolecule.from0attr
  implicit def m[A](dsl: Molecule_1[A]): Molecule1[A] = macro BuildMolecule.from1attr[A]
  implicit def m[A, B](dsl: Molecule_2[A, B]): Molecule2[A, B] = macro BuildMolecule.from2attr[A, B]
  implicit def m[A, B, C](dsl: Molecule_3[A, B, C]): Molecule3[A, B, C] = macro BuildMolecule.from3attr[A, B, C]
  implicit def m[A, B, C, D](dsl: Molecule_4[A, B, C, D]): Molecule4[A, B, C, D] = macro BuildMolecule.from4attr[A, B, C, D]
  implicit def m[A, B, C, D, E](dsl: Molecule_5[A, B, C, D, E]): Molecule5[A, B, C, D, E] = macro BuildMolecule.from5attr[A, B, C, D, E]
  implicit def m[A, B, C, D, E, F](dsl: Molecule_6[A, B, C, D, E, F]): Molecule6[A, B, C, D, E, F] = macro BuildMolecule.from6attr[A, B, C, D, E, F]
  implicit def m[A, B, C, D, E, F, G](dsl: Molecule_7[A, B, C, D, E, F, G]): Molecule7[A, B, C, D, E, F, G] = macro BuildMolecule.from7attr[A, B, C, D, E, F, G]
  implicit def m[A, B, C, D, E, F, G, H](dsl: Molecule_8[A, B, C, D, E, F, G, H]): Molecule8[A, B, C, D, E, F, G, H] = macro BuildMolecule.from8attr[A, B, C, D, E, F, G, H]
  implicit def m[A, B, C, D, E, F, G, H, I](dsl: Molecule_9[A, B, C, D, E, F, G, H, I]): Molecule9[A, B, C, D, E, F, G, H, I] = macro BuildMolecule.from9attr[A, B, C, D, E, F, G, H, I]
  implicit def m[A, B, C, D, E, F, G, H, I, J](dsl: Molecule_10[A, B, C, D, E, F, G, H, I, J]): Molecule10[A, B, C, D, E, F, G, H, I, J] = macro BuildMolecule.from10attr[A, B, C, D, E, F, G, H, I, J]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K](dsl: Molecule_11[A, B, C, D, E, F, G, H, I, J, K]): Molecule11[A, B, C, D, E, F, G, H, I, J, K] = macro BuildMolecule.from11attr[A, B, C, D, E, F, G, H, I, J, K]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L](dsl: Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]): Molecule12[A, B, C, D, E, F, G, H, I, J, K, L] = macro BuildMolecule.from12attr[A, B, C, D, E, F, G, H, I, J, K, L]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M](dsl: Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]): Molecule13[A, B, C, D, E, F, G, H, I, J, K, L, M] = macro BuildMolecule.from13attr[A, B, C, D, E, F, G, H, I, J, K, L, M]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N](dsl: Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]): Molecule14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = macro BuildMolecule.from14attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](dsl: Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]): Molecule15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = macro BuildMolecule.from15attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](dsl: Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]): Molecule16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = macro BuildMolecule.from16attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](dsl: Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]): Molecule17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = macro BuildMolecule.from17attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](dsl: Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]): Molecule18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = macro BuildMolecule.from18attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](dsl: Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]): Molecule19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = macro BuildMolecule.from19attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](dsl: Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]): Molecule20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = macro BuildMolecule.from20attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](dsl: Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]): Molecule21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = macro BuildMolecule.from21attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](dsl: Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]): Molecule22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = macro BuildMolecule.from22attr[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]


  // SubComponent molecules

//  implicit def m(dsl: SubMolecule_0): SubMolecule0 = macro BuildMolecule.from0attrSub
//  implicit def m[A](dsl: SubMolecule_1[A]): SubMolecule1[A] = macro BuildMolecule.from1attrSub[A]
//  implicit def m[A, B](dsl: SubMolecule_2[A, B]): SubMolecule2[A, B] = macro BuildMolecule.from2attrSub[A, B]
//  implicit def m[A, B, C](dsl: SubMolecule_3[A, B, C]): SubMolecule3[A, B, C] = macro BuildMolecule.from3attrSub[A, B, C]
//  implicit def m[A, B, C, D](dsl: SubMolecule_4[A, B, C, D]): SubMolecule4[A, B, C, D] = macro BuildMolecule.from4attrSub[A, B, C, D]
//  implicit def m[A, B, C, D, E](dsl: SubMolecule_5[A, B, C, D, E]): SubMolecule5[A, B, C, D, E] = macro BuildMolecule.from5attrSub[A, B, C, D, E]
//  implicit def m[A, B, C, D, E, F](dsl: SubMolecule_6[A, B, C, D, E, F]): SubMolecule6[A, B, C, D, E, F] = macro BuildMolecule.from6attrSub[A, B, C, D, E, F]
//  implicit def m[A, B, C, D, E, F, G](dsl: SubMolecule_7[A, B, C, D, E, F, G]): SubMolecule7[A, B, C, D, E, F, G] = macro BuildMolecule.from7attrSub[A, B, C, D, E, F, G]
//  implicit def m[A, B, C, D, E, F, G, H](dsl: SubMolecule_8[A, B, C, D, E, F, G, H]): SubMolecule8[A, B, C, D, E, F, G, H] = macro BuildMolecule.from8attrSub[A, B, C, D, E, F, G, H]
//  implicit def m[A, B, C, D, E, F, G, H, I](dsl: SubMolecule_9[A, B, C, D, E, F, G, H, I]): SubMolecule9[A, B, C, D, E, F, G, H, I] = macro BuildMolecule.from9attrSub[A, B, C, D, E, F, G, H, I]
//  implicit def m[A, B, C, D, E, F, G, H, I, J](dsl: SubMolecule_10[A, B, C, D, E, F, G, H, I, J]): SubMolecule10[A, B, C, D, E, F, G, H, I, J] = macro BuildMolecule.from10attrSub[A, B, C, D, E, F, G, H, I, J]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K](dsl: SubMolecule_11[A, B, C, D, E, F, G, H, I, J, K]): SubMolecule11[A, B, C, D, E, F, G, H, I, J, K] = macro BuildMolecule.from11attrSub[A, B, C, D, E, F, G, H, I, J, K]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L](dsl: SubMolecule_12[A, B, C, D, E, F, G, H, I, J, K, L]): SubMolecule12[A, B, C, D, E, F, G, H, I, J, K, L] = macro BuildMolecule.from12attrSub[A, B, C, D, E, F, G, H, I, J, K, L]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M](dsl: SubMolecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]): SubMolecule13[A, B, C, D, E, F, G, H, I, J, K, L, M] = macro BuildMolecule.from13attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N](dsl: SubMolecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]): SubMolecule14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = macro BuildMolecule.from14attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](dsl: SubMolecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]): SubMolecule15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = macro BuildMolecule.from15attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](dsl: SubMolecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]): SubMolecule16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = macro BuildMolecule.from16attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](dsl: SubMolecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]): SubMolecule17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = macro BuildMolecule.from17attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](dsl: SubMolecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]): SubMolecule18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = macro BuildMolecule.from18attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](dsl: SubMolecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]): SubMolecule19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = macro BuildMolecule.from19attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](dsl: SubMolecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]): SubMolecule20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = macro BuildMolecule.from20attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](dsl: SubMolecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]): SubMolecule21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = macro BuildMolecule.from21attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
//  implicit def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](dsl: SubMolecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]): SubMolecule22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = macro BuildMolecule.from22attrSub[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]


  // Input molecules awaiting 1 input

  implicit def m[I1](inputDsl: In_1_0[I1]): InputMolecule_1_0[I1] = macro BuildInputMolecule.await_1_0[I1]
  implicit def m[I1, A](inputDsl: In_1_1[I1, A]): InputMolecule_1_1[I1, A] = macro BuildInputMolecule.await_1_1[I1, A]
  implicit def m[I1, A, B](inputDsl: In_1_2[I1, A, B]): InputMolecule_1_2[I1, A, B] = macro BuildInputMolecule.await_1_2[I1, A, B]
  implicit def m[I1, A, B, C](inputDsl: In_1_3[I1, A, B, C]): InputMolecule_1_3[I1, A, B, C] = macro BuildInputMolecule.await_1_3[I1, A, B, C]
  implicit def m[I1, A, B, C, D](inputDsl: In_1_4[I1, A, B, C, D]): InputMolecule_1_4[I1, A, B, C, D] = macro BuildInputMolecule.await_1_4[I1, A, B, C, D]
  implicit def m[I1, A, B, C, D, E](inputDsl: In_1_5[I1, A, B, C, D, E]): InputMolecule_1_5[I1, A, B, C, D, E] = macro BuildInputMolecule.await_1_5[I1, A, B, C, D, E]
  implicit def m[I1, A, B, C, D, E, F](inputDsl: In_1_6[I1, A, B, C, D, E, F]): InputMolecule_1_6[I1, A, B, C, D, E, F] = macro BuildInputMolecule.await_1_6[I1, A, B, C, D, E, F]
  implicit def m[I1, A, B, C, D, E, F, G](inputDsl: In_1_7[I1, A, B, C, D, E, F, G]): InputMolecule_1_7[I1, A, B, C, D, E, F, G] = macro BuildInputMolecule.await_1_7[I1, A, B, C, D, E, F, G]
  implicit def m[I1, A, B, C, D, E, F, G, H](inputDsl: In_1_8[I1, A, B, C, D, E, F, G, H]): InputMolecule_1_8[I1, A, B, C, D, E, F, G, H] = macro BuildInputMolecule.await_1_8[I1, A, B, C, D, E, F, G, H]
  implicit def m[I1, A, B, C, D, E, F, G, H, I](inputDsl: In_1_9[I1, A, B, C, D, E, F, G, H, I]): InputMolecule_1_9[I1, A, B, C, D, E, F, G, H, I] = macro BuildInputMolecule.await_1_9[I1, A, B, C, D, E, F, G, H, I]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J](inputDsl: In_1_10[I1, A, B, C, D, E, F, G, H, I, J]): InputMolecule_1_10[I1, A, B, C, D, E, F, G, H, I, J] = macro BuildInputMolecule.await_1_10[I1, A, B, C, D, E, F, G, H, I, J]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K](inputDsl: In_1_11[I1, A, B, C, D, E, F, G, H, I, J, K]): InputMolecule_1_11[I1, A, B, C, D, E, F, G, H, I, J, K] = macro BuildInputMolecule.await_1_11[I1, A, B, C, D, E, F, G, H, I, J, K]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L](inputDsl: In_1_12[I1, A, B, C, D, E, F, G, H, I, J, K, L]): InputMolecule_1_12[I1, A, B, C, D, E, F, G, H, I, J, K, L] = macro BuildInputMolecule.await_1_12[I1, A, B, C, D, E, F, G, H, I, J, K, L]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M](inputDsl: In_1_13[I1, A, B, C, D, E, F, G, H, I, J, K, L, M]): InputMolecule_1_13[I1, A, B, C, D, E, F, G, H, I, J, K, L, M] = macro BuildInputMolecule.await_1_13[I1, A, B, C, D, E, F, G, H, I, J, K, L, M]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N](inputDsl: In_1_14[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N]): InputMolecule_1_14[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N] = macro BuildInputMolecule.await_1_14[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](inputDsl: In_1_15[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]): InputMolecule_1_15[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = macro BuildInputMolecule.await_1_15[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](inputDsl: In_1_16[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]): InputMolecule_1_16[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = macro BuildInputMolecule.await_1_16[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](inputDsl: In_1_17[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]): InputMolecule_1_17[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = macro BuildInputMolecule.await_1_17[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](inputDsl: In_1_18[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]): InputMolecule_1_18[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = macro BuildInputMolecule.await_1_18[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](inputDsl: In_1_19[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]): InputMolecule_1_19[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = macro BuildInputMolecule.await_1_19[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](inputDsl: In_1_20[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]): InputMolecule_1_20[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = macro BuildInputMolecule.await_1_20[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](inputDsl: In_1_21[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]): InputMolecule_1_21[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = macro BuildInputMolecule.await_1_21[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
  implicit def m[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](inputDsl: In_1_22[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]): InputMolecule_1_22[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = macro BuildInputMolecule.await_1_22[I1, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]


  // Input molecules awaiting 2 inputs

  implicit def m[I1, I2](inputDsl: In_2_0[I1, I2]): InputMolecule_2_0[I1, I2] = macro BuildInputMolecule.await_2_0[I1, I2]
  implicit def m[I1, I2, A](inputDsl: In_2_1[I1, I2, A]): InputMolecule_2_1[I1, I2, A] = macro BuildInputMolecule.await_2_1[I1, I2, A]
  implicit def m[I1, I2, A, B](inputDsl: In_2_2[I1, I2, A, B]): InputMolecule_2_2[I1, I2, A, B] = macro BuildInputMolecule.await_2_2[I1, I2, A, B]
  implicit def m[I1, I2, A, B, C](inputDsl: In_2_3[I1, I2, A, B, C]): InputMolecule_2_3[I1, I2, A, B, C] = macro BuildInputMolecule.await_2_3[I1, I2, A, B, C]
  implicit def m[I1, I2, A, B, C, D](inputDsl: In_2_4[I1, I2, A, B, C, D]): InputMolecule_2_4[I1, I2, A, B, C, D] = macro BuildInputMolecule.await_2_4[I1, I2, A, B, C, D]
  implicit def m[I1, I2, A, B, C, D, E](inputDsl: In_2_5[I1, I2, A, B, C, D, E]): InputMolecule_2_5[I1, I2, A, B, C, D, E] = macro BuildInputMolecule.await_2_5[I1, I2, A, B, C, D, E]
  implicit def m[I1, I2, A, B, C, D, E, F](inputDsl: In_2_6[I1, I2, A, B, C, D, E, F]): InputMolecule_2_6[I1, I2, A, B, C, D, E, F] = macro BuildInputMolecule.await_2_6[I1, I2, A, B, C, D, E, F]
  implicit def m[I1, I2, A, B, C, D, E, F, G](inputDsl: In_2_7[I1, I2, A, B, C, D, E, F, G]): InputMolecule_2_7[I1, I2, A, B, C, D, E, F, G] = macro BuildInputMolecule.await_2_7[I1, I2, A, B, C, D, E, F, G]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H](inputDsl: In_2_8[I1, I2, A, B, C, D, E, F, G, H]): InputMolecule_2_8[I1, I2, A, B, C, D, E, F, G, H] = macro BuildInputMolecule.await_2_8[I1, I2, A, B, C, D, E, F, G, H]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I](inputDsl: In_2_9[I1, I2, A, B, C, D, E, F, G, H, I]): InputMolecule_2_9[I1, I2, A, B, C, D, E, F, G, H, I] = macro BuildInputMolecule.await_2_9[I1, I2, A, B, C, D, E, F, G, H, I]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J](inputDsl: In_2_10[I1, I2, A, B, C, D, E, F, G, H, I, J]): InputMolecule_2_10[I1, I2, A, B, C, D, E, F, G, H, I, J] = macro BuildInputMolecule.await_2_10[I1, I2, A, B, C, D, E, F, G, H, I, J]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K](inputDsl: In_2_11[I1, I2, A, B, C, D, E, F, G, H, I, J, K]): InputMolecule_2_11[I1, I2, A, B, C, D, E, F, G, H, I, J, K] = macro BuildInputMolecule.await_2_11[I1, I2, A, B, C, D, E, F, G, H, I, J, K]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L](inputDsl: In_2_12[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L]): InputMolecule_2_12[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L] = macro BuildInputMolecule.await_2_12[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M](inputDsl: In_2_13[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M]): InputMolecule_2_13[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M] = macro BuildInputMolecule.await_2_13[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N](inputDsl: In_2_14[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N]): InputMolecule_2_14[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N] = macro BuildInputMolecule.await_2_14[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](inputDsl: In_2_15[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]): InputMolecule_2_15[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = macro BuildInputMolecule.await_2_15[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](inputDsl: In_2_16[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]): InputMolecule_2_16[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = macro BuildInputMolecule.await_2_16[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](inputDsl: In_2_17[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]): InputMolecule_2_17[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = macro BuildInputMolecule.await_2_17[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](inputDsl: In_2_18[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]): InputMolecule_2_18[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = macro BuildInputMolecule.await_2_18[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](inputDsl: In_2_19[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]): InputMolecule_2_19[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = macro BuildInputMolecule.await_2_19[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](inputDsl: In_2_20[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]): InputMolecule_2_20[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = macro BuildInputMolecule.await_2_20[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](inputDsl: In_2_21[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]): InputMolecule_2_21[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = macro BuildInputMolecule.await_2_21[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
  implicit def m[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](inputDsl: In_2_22[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]): InputMolecule_2_22[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = macro BuildInputMolecule.await_2_22[I1, I2, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]


  // Input molecules awaiting 3 inputs

  implicit def m[I1, I2, I3](inputDsl: In_3_0[I1, I2, I3]): InputMolecule_3_0[I1, I2, I3] = macro BuildInputMolecule.await_3_0[I1, I2, I3]
  implicit def m[I1, I2, I3, A](inputDsl: In_3_1[I1, I2, I3, A]): InputMolecule_3_1[I1, I2, I3, A] = macro BuildInputMolecule.await_3_1[I1, I2, I3, A]
  implicit def m[I1, I2, I3, A, B](inputDsl: In_3_2[I1, I2, I3, A, B]): InputMolecule_3_2[I1, I2, I3, A, B] = macro BuildInputMolecule.await_3_2[I1, I2, I3, A, B]
  implicit def m[I1, I2, I3, A, B, C](inputDsl: In_3_3[I1, I2, I3, A, B, C]): InputMolecule_3_3[I1, I2, I3, A, B, C] = macro BuildInputMolecule.await_3_3[I1, I2, I3, A, B, C]
  implicit def m[I1, I2, I3, A, B, C, D](inputDsl: In_3_4[I1, I2, I3, A, B, C, D]): InputMolecule_3_4[I1, I2, I3, A, B, C, D] = macro BuildInputMolecule.await_3_4[I1, I2, I3, A, B, C, D]
  implicit def m[I1, I2, I3, A, B, C, D, E](inputDsl: In_3_5[I1, I2, I3, A, B, C, D, E]): InputMolecule_3_5[I1, I2, I3, A, B, C, D, E] = macro BuildInputMolecule.await_3_5[I1, I2, I3, A, B, C, D, E]
  implicit def m[I1, I2, I3, A, B, C, D, E, F](inputDsl: In_3_6[I1, I2, I3, A, B, C, D, E, F]): InputMolecule_3_6[I1, I2, I3, A, B, C, D, E, F] = macro BuildInputMolecule.await_3_6[I1, I2, I3, A, B, C, D, E, F]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G](inputDsl: In_3_7[I1, I2, I3, A, B, C, D, E, F, G]): InputMolecule_3_7[I1, I2, I3, A, B, C, D, E, F, G] = macro BuildInputMolecule.await_3_7[I1, I2, I3, A, B, C, D, E, F, G]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H](inputDsl: In_3_8[I1, I2, I3, A, B, C, D, E, F, G, H]): InputMolecule_3_8[I1, I2, I3, A, B, C, D, E, F, G, H] = macro BuildInputMolecule.await_3_8[I1, I2, I3, A, B, C, D, E, F, G, H]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I](inputDsl: In_3_9[I1, I2, I3, A, B, C, D, E, F, G, H, I]): InputMolecule_3_9[I1, I2, I3, A, B, C, D, E, F, G, H, I] = macro BuildInputMolecule.await_3_9[I1, I2, I3, A, B, C, D, E, F, G, H, I]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J](inputDsl: In_3_10[I1, I2, I3, A, B, C, D, E, F, G, H, I, J]): InputMolecule_3_10[I1, I2, I3, A, B, C, D, E, F, G, H, I, J] = macro BuildInputMolecule.await_3_10[I1, I2, I3, A, B, C, D, E, F, G, H, I, J]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K](inputDsl: In_3_11[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K]): InputMolecule_3_11[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K] = macro BuildInputMolecule.await_3_11[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L](inputDsl: In_3_12[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L]): InputMolecule_3_12[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L] = macro BuildInputMolecule.await_3_12[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M](inputDsl: In_3_13[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M]): InputMolecule_3_13[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M] = macro BuildInputMolecule.await_3_13[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N](inputDsl: In_3_14[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N]): InputMolecule_3_14[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N] = macro BuildInputMolecule.await_3_14[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](inputDsl: In_3_15[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]): InputMolecule_3_15[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = macro BuildInputMolecule.await_3_15[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](inputDsl: In_3_16[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]): InputMolecule_3_16[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = macro BuildInputMolecule.await_3_16[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](inputDsl: In_3_17[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]): InputMolecule_3_17[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = macro BuildInputMolecule.await_3_17[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](inputDsl: In_3_18[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]): InputMolecule_3_18[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = macro BuildInputMolecule.await_3_18[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](inputDsl: In_3_19[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]): InputMolecule_3_19[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = macro BuildInputMolecule.await_3_19[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](inputDsl: In_3_20[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]): InputMolecule_3_20[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = macro BuildInputMolecule.await_3_20[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](inputDsl: In_3_21[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]): InputMolecule_3_21[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = macro BuildInputMolecule.await_3_21[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
  implicit def m[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](inputDsl: In_3_22[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]): InputMolecule_3_22[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = macro BuildInputMolecule.await_3_22[I1, I2, I3, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]


  // Attribute expressions
  implicit def string2Model (v: String)  = TermValue(v)
  implicit def int2Model    (v: Int)     = TermValue(v)
  implicit def long2Model   (v: Long)    = TermValue(v)
  implicit def float2Model  (v: Float)   = TermValue(v)
  implicit def double2Model (v: Double)  = TermValue(v)
  implicit def boolean2Model(v: Boolean) = TermValue(v)
  implicit def date2Model   (v: Date)    = TermValue(v)
  implicit def uuid2Model   (v: UUID)    = TermValue(v)
  implicit def uri2Model    (v: URI)     = TermValue(v)

  implicit def stringSet2Model (set: Set[String])  = TermValue(set)
  implicit def intSet2Model    (set: Set[Int])     = TermValue(set)
  implicit def longSet2Model   (set: Set[Long])    = TermValue(set)
  implicit def floatSet2Model  (set: Set[Float])   = TermValue(set)
  implicit def doubleSet2Model (set: Set[Double])  = TermValue(set)
  implicit def booleanSet2Model(set: Set[Boolean]) = TermValue(set)
  implicit def dateSet2Model   (set: Set[Date])    = TermValue(set)
  implicit def uuidSet2Model   (set: Set[UUID])    = TermValue(set)
  implicit def uriSet2Model    (set: Set[URI])     = TermValue(set)

  implicit def contains2Model[T](c: contains[T]) = TermValue(c.value)

  implicit def tuple2Model[A, B](tpl: (A, B)) = TermValue(tpl)

  // Entity api
  implicit def long2Entity(id: Long)(implicit conn: Connection) =  EntityFacade(conn.db.entity(id), conn, id.asInstanceOf[Object])

  // Markers
  object ?
  object ?!
  object maybe
  object count
  case class contains[T](value: T)
  case class oldNew[T](from: T, to: T)
}
