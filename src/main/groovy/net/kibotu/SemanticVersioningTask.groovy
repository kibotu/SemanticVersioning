package net.kibotu

import org.gradle.api.DefaultTask

class SemanticVersioningTask extends DefaultTask {

    SemanticVersioningTask() {
        super()
        this.description = 'Creates VersionName and VersionCode'
    }
}