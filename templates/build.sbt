import play.sbt.activator.Templates._

templateSettings

val playSbtVersion = propOrElse("sbt.version", "0.13.5-RC1")

val coffeescriptVersion = propOrElse("coffeescript.version", "1.0.0-RC1")

val lessVersion = propOrElse("coffeescript.version", "1.0.0-RC1")

val jshintVersion = propOrElse("jshint.version", "1.0.0-RC1")

val digestVersion = propOrElse("digest.version", "1.0.0-RC1")

val rjsVersion = propOrElse("rjs.version", "1.0.0-RC1")

val mochaVersion = propOrElse("mocha.version", "1.0.0-RC1")

templates := {
  val dir = baseDirectory.value
  sys.props.get("templates").map(_.split(",").toSeq).getOrElse(Seq(
    "play-scala",
    "play-java"
  )).map(template => dir / template)
}

val playVersion = propOrElse("play.version", {
  println("[\033[31merror\033[0m] No play.version system property specified.\n[\033[31merror\033[0m] Just use the build script to launch SBT and life will be much easier.")
  System.exit(1)
  throw new RuntimeException("No play version")
})

// The Play templates should default to using the latest compatible version of Scala
val playScalaVersion = propOrElse("scala.version", "2.11.0")

val playDocsUrl = propOrElse("play.docs.url", s"http://www.playframework.com/documentation/${playVersion}")

// Use different names for release and milestone templates
val (templateNameSuffix, templateTitleSuffix) = {
  val officialRelease = playVersion.matches("[0-9.]+") // Match final versions but not *-SNAPSHOT or *-RC1
  if (officialRelease) ("", "") else ("-preview", " (Preview)")
}

def propOrElse(prop: String, default: => String): String = sys.props.get(prop).getOrElse(default)

templateParameters := Map(
  "PLAY_VERSION" -> playVersion,
  "SCALA_VERSION" -> playScalaVersion,
  "PLAY_DOCS_URL" -> playDocsUrl,
  "SBT_VERSION" -> playSbtVersion,
  "COFFEESCRIPT_VERSION" -> coffeescriptVersion,
  "LESS_VERSION" -> lessVersion,
  "JSHINT_VERSION" -> jshintVersion,
  "DIGEST_VERSION" -> digestVersion,
  "RJS_VERSION" -> rjsVersion,
  "MOCHA_VERSION" -> mochaVersion,
  "TEMPLATE_NAME_SUFFIX" -> templateNameSuffix,
  "TEMPLATE_TITLE_SUFFIX" -> templateTitleSuffix
)

