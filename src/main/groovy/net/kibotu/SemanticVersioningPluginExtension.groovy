package net.kibotu

import org.gradle.api.Project

class SemanticVersioningPluginExtension {

    private final Project project

    public SemanticVersioningPluginExtension(Project project) {
        this.project = project
        this.outputDirectory = {
            return project.project.getBuildDir()
        }
    }

    File getOutputDirectory() {
        return project.file(outputDirectory)
    }

    void setOutputDirectory(Object outputDirectory) {
        this.outputDirectory = outputDirectory
    }
}