import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform._

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(PreserveDanglingCloseParenthesis, true)
  .setPreference(AlignParameters, true)
  .setPreference(CompactControlReadability, true)
  .setPreference(SpaceInsideBrackets, true)
  .setPreference(SpaceInsideParentheses, true)
  .setPreference(SpacesWithinPatternBinders, true)
