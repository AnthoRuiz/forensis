
outputPath in assembly := file( s"dist/${name.value}-${version.value}.jar" )

val meta = """META.INF(.)*""".r

assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", xs @ _*) => MergeStrategy.first
  case meta(_) => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
