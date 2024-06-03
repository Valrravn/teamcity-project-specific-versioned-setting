import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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
    subProject(SampleProject1)
    subProject(SampleProject2)
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

// Added manually
object SampleProject1 : Project({
    name = "Sample 1 Project"
})

object SampleProject2 : Project({
    name = "Sample 2 Project"
})

// added manually
object SampleProject1_Sample1Root : GitVcsRoot({
    name = "Subproject 1 root"
    url = "https://github.com/Valrravn/teamcity-project-specific-versioned-setting"
    branch = "main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "Valrravn"
        password = "zxx9d3653f55381d175752356791b377143da3ea808a3d648360153b91c604058df19081de775f83da7775d03cbe80d301b"
    }
})


// added manually
object SampleProject2_Sample2Root : GitVcsRoot({
    name = "Subproject 2 root"
    url = "https://github.com/Valrravn/teamcity-project-specific-versioned-setting"
    branch = "main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "Valrravn"
        password = "zxx9d3653f55381d175752356791b377143da3ea808a3d648360153b91c604058df19081de775f83da7775d03cbe80d301b"
    }
})
