import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.03"

project {

    buildType(BuildAll)
}

object BuildAll : BuildType({
    name = "Build All"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(DslContext.settingsRoot)

        showDependenciesChanges = true
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        snapshot(RelativeId("Sample1Project_BuildSample1")) {
        }
        snapshot(RelativeId("Sample2Project_BuildSample2")) {
        }
    }
})
