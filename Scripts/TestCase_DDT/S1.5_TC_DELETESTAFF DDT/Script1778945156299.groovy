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

// 1. Log In Admin (Gunakan setText agar aman dari autofill browser)
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
if (testType == 'DELETE') {
    // Skenario S1.5_TC01: Menghapus data staff dari daftar
    WebUI.waitForElementVisible(findTestObject('TC_DeleteStaff/Deleted_Behaviour/td_zidan'), 5)

    WebUI.click(findTestObject('TC_DeleteStaff/i_Delete_fa fa-trash'))

    // Tunggu dan verifikasi pop-up konfirmasi muncul
    WebUI.waitForElementVisible(findTestObject('TC_DeleteStaff/h2_zidan'), 2)

    // Klik tombol konfirmasi delete di dalam pop-up modal
    WebUI.click(findTestObject('TC_DeleteStaff/i_Delete_fa fa-trash_1'))

    // Verifikasi alert sukses penghapusan data
    WebUI.waitForElementVisible(findTestObject('TC_DeleteStaff/div_Employee Record has been Deleted successfully'), 5)

    WebUI.verifyElementPresent(findTestObject('TC_DeleteStaff/div_Employee Record has been Deleted successfully'), 5) // Skenario S1.5_TC02: Memastikan staff yang dihapus tidak muncul lagi saat di-search
    // Verifikasi pesan bahwa data tidak ditemukan di tabel
} else if (testType == 'VERIFY_GONE') {
    WebUI.sendKeys(findTestObject('Page_Attendance Management System/Staff_Special_Zidan/input_Search_Staff'), searchKeyword)

    WebUI.waitForElementVisible(findTestObject('TC_DeleteStaff/Page_Attendance Management System/td_No matching records found'), 
        5)

    WebUI.verifyElementPresent(findTestObject('TC_DeleteStaff/Page_Attendance Management System/td_No matching records found'), 
        5)
}

// 4. Tutup Browser
WebUI.closeBrowser()

