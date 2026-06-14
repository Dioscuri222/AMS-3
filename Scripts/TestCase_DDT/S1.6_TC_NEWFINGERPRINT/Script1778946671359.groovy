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

// 1. Log In Admin
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.NavigateToURL)

WebUI.sendKeys(findTestObject('TC_Login/Page_Attendance Management System/input_Email Address_email'), GlobalVariable.sendkeys)

WebUI.sendKeys(findTestObject('TC_Login/Page_Attendance Management System/input_Password_password'), GlobalVariable.sendpassword)

WebUI.click(findTestObject('TC_Login/Page_Attendance Management System/button_Log In'))

// 2. Navigasi ke Halaman Biometric Device
WebUI.click(findTestObject('TC_Fingerprint/Page_Attendance Management System/span_Biometric Device'))

WebUI.click(findTestObject('TC_Fingerprint/Page_Attendance Management System/a_Add Fingerprint Device'))

// 3. Mengisi Form Menggunakan Variabel CSV
WebUI.sendKeys(findTestObject('TC_Fingerprint/Page_Attendance Management System/input_Name_title'), deviceName)

WebUI.sendKeys(findTestObject('TC_Fingerprint/Page_Attendance Management System/input_IP Address_ip'), deviceIP)

WebUI.click(findTestObject('TC_Fingerprint/Page_Attendance Management System/button_Save'))

// 4. Logika Validasi Hasil
if (testType == 'ERR_NO_DEV') {
    // Memastikan muncul pesan error karena alat tidak tersedia di jaringan
    WebUI.waitForElementVisible(findTestObject('TC_Fingerprint/Page_Attendance Management System/span_Error'), 5)

    WebUI.verifyElementPresent(findTestObject('TC_Fingerprint/Page_Attendance Management System/span_Error'), 5)
}

// 5. Tutup Browser
WebUI.closeBrowser()

