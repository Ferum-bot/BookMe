import com.android.build.gradle.internal.dsl.SigningConfig
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

@Suppress("UnstableApiUsage")
interface SigningConfigCreator {

    val name: String

    fun createOrConfig(container: NamedDomainObjectContainer<SigningConfig>): SigningConfig {
        val buildType = container.findByName(name)
        return if (buildType != null) {
            container.getByName(name, configureAction())
        }
        else {
            container.create(name, configureAction())
        }
    }

    fun configureAction(): Action<in SigningConfig>
}

/**
 * Edit this properties for real data lately
 */
@Suppress("UnstableApiUsage")
object SigningConfigRelease: SigningConfigCreator {

    override val name: String = "release"

    override fun configureAction(): Action<in SigningConfig> {
        return Action {
            this.storeFile = File("release/keystore")
            this.storePassword = "myStorePassword"
            this.keyAlias = "myKeyAllias"
            this.keyPassword = "myKeyPassword"
        }
    }
}

@Suppress("UnstableApiUsage")
object SigningConfigDebug: SigningConfigCreator {

    override val name: String = "debug"

    override fun configureAction(): Action<in SigningConfig> {
        return Action {
            this.storeFile = File("keystore/debug.jks")
            this.keyAlias = "androidDebugKey"
            this.keyPassword = "android"
            this.storePassword = "android"
        }
    }

}