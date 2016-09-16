package net.kibotu

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task


class SemanticVersioningPlugin implements Plugin<Project> {

    final static String GROUP_NAME = 'SemanticVersioning'

    @Override
    void apply(Project project) {

        applyExtensions(project)
        applyTasks(project)
    }

    static void applyExtensions(final Project project) {
        project.extensions.create('semver', SemanticVersioningPluginExtension, project)
    }

    static void applyTasks(final Project project) {

        if (project.plugins.hasPlugin(AppPlugin)) {
            AppExtension android = project.android
            Task uploadAllTask = project.tasks.create("uploadToHockeyApp", Task);
            uploadAllTask.group = GROUP_NAME
            uploadAllTask.description = "Uploads all variants to HockeyApp"
            uploadAllTask.outputs.upToDateWhen { false }
            String uploadAllPath = uploadAllTask.getPath();

            android.applicationVariants.all { ApplicationVariant variant ->
                SemanticVersioningTask task = project.tasks.create("upload${variant.name.capitalize()}ToHockeyApp", HockeyAppUploadTask)
                task.group = GROUP_NAME
                task.description = "Upload '${variant.name}' to HockeyApp"
                task.applicationVariant = variant
                task.variantName = variant.name
                task.outputs.upToDateWhen { false }
                task.dependsOn variant.assemble
                task.uploadAllPath = uploadAllPath

                uploadAllTask.dependsOn(task)
            }
        } else {
            project.task('semver', type: SemanticVersioningTask, group: GROUP_NAME)
        }
    }
}
