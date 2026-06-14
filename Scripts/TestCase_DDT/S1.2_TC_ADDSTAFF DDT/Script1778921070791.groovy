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

// 1. Proses Login Admin Terlebih Dahulu (Menggunakan setText agar bersih)
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.NavigateToURL)

WebUI.sendKeys(findTestObject('TC_Login/Page_Attendance Management System/input_Email Address_email'), GlobalVariable.sendkeys)

WebUI.sendKeys(findTestObject('TC_Login/Page_Attendance Management System/input_Password_password'), GlobalVariable.sendpassword)

WebUI.click(findTestObject('TC_Login/Page_Attendance Management System/button_Log In'))

// 2. Navigasi ke Halaman Form Add Staff
WebUI.click(findTestObject('TC_AddStaff/i_Logout_mdi mdi-menu'))

WebUI.click(findTestObject('TC_AddStaff/span_Employees'))

WebUI.click(findTestObject('TC_AddStaff/span_Employees List'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('TC_AddStaff/a_Add'))

// 3. Mengisi Form Berdasarkan Variabel CSV (Menggunakan setText, bukan sendKeys)
WebUI.sendKeys(findTestObject('TC_AddStaff/input_Name_name'), staffName)

WebUI.sendKeys(findTestObject('TC_AddStaff/input_Position_position'), staffPosition)

WebUI.sendKeys(findTestObject('TC_AddStaff/input_Email_email'), staffEmail)

// 4. Memilih Dropdown Schedule Secara Dinamis
WebUI.click(findTestObject('TC_AddStaff/Select-Schedule'))

WebUI.waitForElementClickable(findTestObject('TC_AddStaff/Select-Schedule'), 5)

WebUI.selectOptionByValue(findTestObject('TC_AddStaff/Select-Schedule'), staffSchedule, false)

// 5. Logika Validasi Hasil Berdasarkan 'testType'
if (testType == 'P') {
    // Skenario Positif: Klik submit dan pastikan muncul alert sukses 
    WebUI.click(findTestObject('TC_AddStaff/button_Submit'), FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementVisible(findTestObject('Page_Attendance Management System/Staff_Special_Zidan/Success_Input'), 5)

    WebUI.verifyElementPresent(findTestObject('Page_Attendance Management System/Staff_Special_Zidan/Success_Input'), 5 // Skenario Negatif Duplikat: Klik submit dan pastikan muncul alert error database 
        ) // Skenario Negatif Kosong: Gunakan aturan pelindung trim() untuk deteksi HTML5 required 
} else if (testType == 'N_Dupe') {
    WebUI.click(findTestObject('TC_AddStaff/button_Submit'), FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementVisible(findTestObject('TC_AddStaff/Error_Duplicate_(23000)'), 5)

    WebUI.verifyElementPresent(findTestObject('TC_AddStaff/Error_Duplicate_(23000)'), 5)
} else if (testType == 'N_Empty') {
    if (staffName.trim() == '') {
        WebUI.verifyElementHasAttribute(findTestObject('TC_AddStaff/input_Name_name'), 'required', 5)
    }
}

WebUI.closeBrowser()

