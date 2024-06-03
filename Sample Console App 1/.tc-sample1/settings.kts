import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.dotnetBuild
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

    buildType(BuildSample1)

    vcsRoot(SampleProject1_Sample1Root) // added manually
}

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

object BuildSample1 : BuildType({
    name = "Build Sample 1"

    steps {
        dotnetBuild {
            id = "dotnet"
            projects = """"Sample Console App 1/Sample Console App 1.sln""""
            sdk = "8"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
    }

    features {
        perfmon {
        }
    }
})
