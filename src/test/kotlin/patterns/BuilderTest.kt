package patterns

import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import qa.step


class BuilderTest {

    @DisplayName("base builder test")
    @Test
    fun builderTest() {
        lateinit var config: Configuration
        step("create builder class") {
            config = ConfigurationBuilder().build()
        }

        Allure.step("Открыть главную страницу М.Видео", ThrowableRunnableVoid {
            Assertions.assertEquals("Chrome", config.driver)
            Assertions.assertEquals(false, config.headless)
            Assertions.assertEquals(4_500, config.loadTime)
        })

    }

    @DisplayName("custom filed set test")
    @Test
    fun builderCustomTest() {
        val conf = ConfigurationBuilder()
            .driver("Safari")
            .build()

        step("qa.step from custom allure") {
            Assertions.assertEquals("Safari", conf.driver)
        }
        step("another 2") {
            Assertions.assertTrue(false)
        }
        step("another qa.step") {
            Assertions.assertTrue(true)
        }
    }
}