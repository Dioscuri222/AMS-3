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

// 1. Log In Admin (Selalu bersihkan autofill dengan setText)
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.NavigateToURL)

WebUI.sendKeys(findTestObject('TC_Login/Page_Attendance Management System/input_Email Address_email'), GlobalVariable.sendkeys)

WebUI.sendKeys(findTestObject('TC_Login/Page_Attendance Management System/input_Password_password'), GlobalVariable.sendpassword)

WebUI.click(findTestObject('TC_Login/Page_Attendance Management System/button_Log In'))

// 2. Navigasi ke Halaman Employees List
WebUI.click(findTestObject('TC_AddStaff/span_Employees'))

WebUI.click(findTestObject('TC_AddStaff/span_Employees List'), FailureHandling.STOP_ON_FAILURE)

// 3. Logika Kondisional DDT Berdasarkan 'testType'
if (testType == 'UPDATE') {
    // Skenario S1.4_TC01: Melakukan update informasi staff
    WebUI.click(findTestObject('TC_UpdateStaff/Page_Attendance Management System/i_Edit_fa fa-edit'))

    WebUI.clearText(findTestObject('TC_UpdateStaff/Page_Attendance Management System/input_Email_email'))

    // Mengubah isi email (setText otomatis menghapus teks lama sebelum mengetik data baru)
    WebUI.sendKeys(findTestObject('TC_UpdateStaff/Page_Attendance Management System/input_Email_email'), staffEmail)

    // Interaksi dengan komponen dropdown schedule secara dinamis
    WebUI.click(findTestObject('TC_UpdateStaff/Page_Attendance Management System/Shift_Pagi'))

    WebUI.waitForElementClickable(findTestObject('TC_UpdateStaff/Page_Attendance Management System/Shift_Pagi'), 5)

    WebUI.selectOptionByValue(findTestObject('TC_UpdateStaff/Page_Attendance Management System/Shift_Pagi'), staffSchedule, 
        false)

    // Submit Form Edit
    WebUI.click(findTestObject('TC_UpdateStaff/Page_Attendance Management System/button_Update'))

    // Verifikasi kemunculan toast/alert sukses dari sistem
    WebUI.verifyElementVisible(findTestObject('TC_UpdateStaff/Page_Attendance Management System/div_Employee Record has been Updated successfully')) // Skenario S1.4_TC02: Memverifikasi perubahan di tabel utama pasca-update
    // Buat/pastikan objek 'td_zidan123ams.com' menggunakan XPath generik berbasis text agar dinamis
} else if (testType == 'VERIFY') {
    WebUI.waitForElementVisible(findTestObject('TC_UpdateStaff/Page_Attendance Management System/td_zidan123ams.com'), 5)

    WebUI.verifyElementPresent(findTestObject('TC_UpdateStaff/Page_Attendance Management System/td_zidan123ams.com'), 5)
}

// 4. Tutup Browser
WebUI.closeBrowser()

