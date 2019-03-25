/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testattoo.core


import org.junit.jupiter.api.DisplayName

//import org.openqa.selenium.WebDriver
//import org.openqa.selenium.chrome.ChromeDriver
//import org.testatoo.Server

//import org.testattoo.evaluator.webdriver.WebDriverEvaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@DisplayName("Browser behaviours")
class BrowserTest {
//    private static DisposableServer server
//    private static final int PORT = 9090
//    private static String BASE_URL = "http://localhost:${PORT}/"
//    private static WebDriver driver
//
//    @BeforeAll
//    static void before() {
//        server = Server.start(9090)
//
//        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup()
//        driver = new ChromeDriver()
//        config.evaluator = new WebDriverEvaluator(driver)
//
//        config.waitUntil = 10.seconds
//        visit BASE_URL + 'wait.html'
//    }
//
//    @AfterAll
//    static void tearDown() {
//        config.waitUntil = 2.seconds
//        driver.close()
//        server.dispose()
//    }
//
//    @Test
//    @DisplayName("Should access to browser properties")
//    void should_access_to_browser_properties() {
//        org.testatoo.core.Browser.open BASE_URL + 'index.html'
//
//        assert org.testatoo.core.Browser.getTitle == 'Testatoo Rocks'
//        assert org.testatoo.core.Browser.getPageSource.contains('<title>Testatoo Rocks</title>')
//        assert org.testatoo.core.Browser.getUrl == BASE_URL + 'index.html'
//
//        org.testatoo.core.Browser.open(BASE_URL + 'keyboard.html')
//        assert org.testatoo.core.Browser.getUrl == BASE_URL + 'keyboard.html'
//    }
//
//    @Test
//    @DisplayName("Should navigate")
//    void should_navigate() {
//        org.testatoo.core.Browser.open BASE_URL + 'index.html'
//
//        assert org.testatoo.core.Browser.getUrl == BASE_URL + 'index.html'
//
//        org.testatoo.core.Browser.navigateTo(BASE_URL + 'keyboard.html')
//        assert org.testatoo.core.Browser.getUrl == BASE_URL + 'keyboard.html'
//
//        org.testatoo.core.Browser.back()
//        assert org.testatoo.core.Browser.getUrl == BASE_URL + 'index.html'
//
//        org.testatoo.core.Browser.forward()
//        assert org.testatoo.core.Browser.getUrl == BASE_URL + 'keyboard.html'
//
//        org.testatoo.core.Browser.refresh()
//        assert org.testatoo.core.Browser.getUrl == BASE_URL + 'keyboard.html'
//    }
//
//    @Test
//    @DisplayName("Should manage Windows")
//    void should_manage_windows() {
//        org.testatoo.core.Browser.open BASE_URL + 'index.html'
//        Component link = $('#link') as Component
//        Component form = $('#dsl-form') as Component
//
//        assert org.testatoo.core.Browser.getWindows.size() == 1
//        assert link.available()
//        assert !form.available()
//
//        String main_window_id = org.testatoo.core.Browser.getWindows[0].id
//
//        org.testatoo.core.Testatoo.clickOn link
//
//        org.testatoo.core.Testatoo.waitUntil({ org.testatoo.core.Browser.getWindows.size() == 2 })
//        org.testatoo.core.Browser.switchTo(org.testatoo.core.Browser.getWindows[1])
//        assert form.available()
//
//        org.testatoo.core.Browser.getWindows[1].close()
//        org.testatoo.core.Testatoo.waitUntil({ org.testatoo.core.Browser.getWindows.size() == 1 })
//        assert org.testatoo.core.Browser.getWindows[0].id == main_window_id
//        assert org.testatoo.core.Browser.getWindows[0].toString() == main_window_id
//    }
}
