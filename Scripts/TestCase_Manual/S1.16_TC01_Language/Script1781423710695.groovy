import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.NavigateToURL)

WebUI.sendKeys(findTestObject('TC_Bahasa/Page_Attendance Management System/input_Email Address_email'), GlobalVariable.sendkeys)

WebUI.sendKeys(findTestObject('TC_Bahasa/Page_Attendance Management System/input_Password_password'), GlobalVariable.sendpassword)

WebUI.click(findTestObject('TC_Bahasa/Page_Attendance Management System/button_Log In'))

WebUI.waitForElementVisible(findTestObject('TC_Bahasa/Page_Attendance Management System/h4_Dashboard'), 0)

// 1. Klik tombol ikon untuk membuka menu dropdown bahasa
WebUI.click(findTestObject('TC_Bahasa/Page_Attendance Management System/span_A_mdi mdi-chevron-down'))

// 2. Klik pada pilihan bahasa Jerman (German) yang muncul
WebUI.click(findTestObject('TC_Bahasa/Page_Attendance Management System/a_German'))

WebUI.waitForElementVisible(findTestObject('TC_Bahasa/Page_Attendance Management System/a_German'), 0)

WebUI.closeBrowser()

