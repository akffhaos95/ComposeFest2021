package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.math.log

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }
        //composeTestRule.onRoot().printToLog("currentLabelExists")
        //composeTestRule
        //    .onNodeWithContentDescription(RallyScreen.Accounts.name)
        //    .assertExists()

        //composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLableExists")
        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                hasParent(hasContentDescription(RallyScreen.Accounts.name)),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_tabSelected() {
        composeTestRule.setContent { RallyApp() }
        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Overview.name)
            )
            .performClick()
        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Accounts.name)
            )
            .assertExists()
            .assertIsNotSelected()
            .performClick()
            .assertIsSelected()
        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Bills.name)
            )
            .assertExists()
            .assertIsNotSelected()
            .performClick()
            .assertIsSelected()
        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Overview.name)
            )
            .assertExists()
            .assertIsNotSelected()
            .performClick()
            .assertIsSelected()
    }
}