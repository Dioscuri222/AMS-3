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

// 1. Membuka URL
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.NavigateToURL)

// 2. Memasukkan Data dari CSV (Menggunakan Variabel Lokal, BUKAN GlobalVariable)
WebUI.setText(findTestObject('TC_Login/Page_Attendance Management System/input_Email Address_email'), email)

WebUI.setText(findTestObject('TC_Login/Page_Attendance Management System/input_Password_password'), password)

WebUI.click(findTestObject('TC_Login/Page_Attendance Management System/button_Log In'))

// 3. Logika DDT berdasarkan kolom 'testType'
if (testType == 'P') {
    // Jika tipe Positif, pastikan berhasil masuk ke halaman Dashboard
    WebUI.waitForElementVisible(findTestObject('Page_Attendance Management System/Page_Attendance Management System/welcomeToDashboard'), 
        3)

    WebUI.verifyElementPresent(findTestObject('Page_Attendance Management System/Page_Attendance Management System/welcomeToDashboard'), 
        3 // Jika tipe Negatif, pastikan pesan error muncul sesuai di CSV
        // Jika ekspektasinya adalah validasi bawaan HTML5 (Browser)
        ) // Cek jika Email yang dikosongkan
    // Cek jika Password yang dikosongkan
    // Jika ekspektasinya adalah error dari Laravel (Akun salah)
} else {
    if (warningMsg == 'Please fill out this field') {
        if (email == '') {
            WebUI.verifyElementHasAttribute(findTestObject('TC_Login/Page_Attendance Management System/input_Email Address_email'), 
                'required', 5)
        } else if (password == '') {
            WebUI.verifyElementHasAttribute(findTestObject('TC_Login/Page_Attendance Management System/input_Password_password'), 
                'required', 5)
        }
    } else {
        WebUI.waitForElementVisible(findTestObject('warningMsgContainer'), 5)

        WebUI.verifyElementText(findTestObject('warningMsgContainer'), warningMsg)
    }
}

// 4. Tutup browser setelah selesai satu baris eksekusi
WebUI.closeBrowser()

