# Author: Jonothan Meyer
# Class: This contains methods for the AnomMiddleNameRemoval class. Most of these relate to pulling the Patient name
# and anomilie information for the purpose of comparing the two to see if the anomilie is good for automatic removal.
# An example of this would be the patient name of "Smith, John, Alton" with the anomilie error,
# "Mismatch [Patient Name]" and anomilie information of "New Patient Name: Smith, John, A". Since this appears to be the
# exact same person, just with a change to include their full middle name, rather than just the initial, this would be a
# ripe case to remove the anomilie
# Version: 1.2
# 7/22/21

# Relevent xpaths:
# Patient name:
# //*[@id="DataTables_Table_3"]/tbody/tr[1]/td[4]
# Anomilie Information
# //*[@id="DataTables_Table_3"]/tbody/tr[1]/td[7]
# Patient ID:
# //*[@id="DataTables_Table_3"]/tbody/tr[1]/td[4]



from selenium.common.exceptions import NoSuchElementException
from selenium import webdriver
import time
from selenium.common.exceptions import WebDriverException
from selenium.webdriver import ActionChains
from selenium.webdriver.common.keys import Keys

Location = ''  # Choose patient location: S: Good Sam, H: St. Joes, M: Lutheran, W:St Mary's, J:Holy Rosiery
Real_Delete = True  # If true will actually delete appropriate anomilies. False to test/demo
scrl_thrs = 500  # Amount of times the list will be scrolled before program termination
rvm_thrs = 100  # Amount of anomilies to be removed before program termination.
del_time = 4  # Time delay in between actions (seconds). If internet is slow then increase value


def remove_namefluff(Anom_Info):
    """Takes a string, checks if it begins with 'New Patient Name' and if so, chops off that portion of the string and
    returns. Otherwise prints an error"""
    if "New Patient Name: " in Anom_Info:
        return Anom_Info[18:]
    return Anom_Info



def remove_commas(String):
    """Takes a string as an argument, meant to be a persons name in this context, checks to see if it contains any
    ',' (commas) within the string and removes them
    ex. 'Musk, Elon, Jr, -> 'Musk Elon Jr'"""
    if ',' in String:
        count = String.count(",")
        for i in range(count):
            String = String.replace(',', "")
    return String


def separate_name(Name):
    Name = remove_namefluff(Name)
    Name = remove_commas(Name)
    return Name


def compnames(pn, pure_ai):
    pn_len = len(pn)
    ai_len = len(pure_ai)
    if pn_len == ai_len:
        return comp(pn, pure_ai)
    elif pn_len > ai_len:
        chop_amt = ai_len - pn_len
        pn = pn[:chop_amt]
        print(pn)
        return comp(pn, pure_ai)
    else:
        chop_amt = pn_len - ai_len
        pure_ai = pure_ai[:chop_amt]
        print(pure_ai)
        return comp(pn, pure_ai)


def comp(pn, ai):
    """Used to compare Patients Name (pn) and Anomilie Information that has already been cleaned to be only the
    Patients name (ai). Returns True if stings are the same, otherwise returns False"""
    if pn == ai:
        return True
    else:
        return False


def switch_gridlist(driver):
    try:
        driver.find_element_by_xpath('/html/body/div[3]/div[2]/div/div[1]/div[3]/div/div/button').click()
        time.sleep(del_time)
    except WebDriverException:
        print("Clicking Between Grid/List Failed")


def check_phantom(String):
    """Ya know, does things. Checks if string is 'Phantom, Keep' returns True if it is, False if not. Duh"""
    boolean = False
    Patient_Name = String
    if Patient_Name is None:
        return boolean
    if "Phantom, Keep" in Patient_Name or "Toshiba Phantom" in Patient_Name or "Phantom Keep" in Patient_Name \
            or "Toshiba, Phantom" in Patient_Name:
        print("Phantom Name         Found++++++++++++++++")
        boolean = True
    if "Ct Qa" in Patient_Name or "Daily Qa" in Patient_Name or "Skylight 1" in Patient_Name or "Skylight, 1" \
            in Patient_Name:
        print("'Ct Qa' - 'Daily Qa' - 'Skylight 1' - 'Skylight, 1'     Found+++++++++++")
        boolean = True
    if "Sjhct Qa" in Patient_Name or "Weekly Qa" in Patient_Name or "Daily2" in Patient_Name:
        print("'sjhct Qa' - 'Weekly Qa' - 'Daily2'        Found++++++++++")
        boolean = True
    if "Acr Qc" in Patient_Name or Patient_Name == "Qa":
        print("'Acr Qc' - 'Qa'       Found+++++++++")
        boolean = True
    if "Gsmc Ct" in Patient_Name or "Gsm Mr" in Patient_Name or "Gssim Qa" in Patient_Name:
        print("'Gsmc Ct' - 'Gsm Mr' - 'Gssim Qa'             Found+++++++++++")
        boolean = True
    if "Geservice" in Patient_Name or "Delete me" in Patient_Name:
        print("'Geservice' - 'Delete me'                  Found++++++++++++")
        boolean = True

    return boolean

def bad_PatientID(PatientID):
    boolean = False
    if PatientID is None:
        return boolean
    if PatientID.isnumeric():
        boolean = True
    return boolean

def check_date(String):
    newString = String
    if '/' in newString:
        count = newString.count('/')
        for i in range(count):
            newString = String.replace('/', "")
    if '.' in newString:
        count = newString.count('.')
        for i in range(count):
            newString = String.replace('.', "")
    if '/' in String or '.' in String and newString.isnumeric():
        print("Date Detected --- ", String)
        return True
    else:
        return False


def print_anomRemoved(rvm_thrs, amount_removed):
    quarter = rvm_thrs / 4
    if amount_removed == quarter or amount_removed == (quarter * 2) or amount_removed == (quarter * 3):
        print("************************************************** " + str(amount_removed) + " Anomilies Removed")


def sel_connect():
    driver = webdriver.Chrome()
    webpage = "http://ehc-geo-synapse/content/"
    print("Connecting to Webpage...")
    driver.get(webpage)
    time.sleep(del_time*2)
    print("Entering username and password...")
    username = input("Enter your username (ex S115544")
    password = input("Enter your password:")
    type(driver, "username", username)
    type(driver, "password", password)
    time.sleep(del_time*1.5)
    print("Navigating to Anomilies...")
    driver.find_element_by_xpath('//*[@id="center-parent"]/div/div[3]/div/div/div[2]/form/div[1]/div[4]/button').click()
    doubleClick(driver, '//*[@id="DataTables_Table_0"]/tbody/tr[1]/td[2]', del_time)
    doubleClick(driver, '//*[@id="DataTables_Table_1"]/tbody/tr[3]/td[2]', del_time)
    doubleClick(driver, '//*[@id="DataTables_Table_2"]/tbody/tr[1]/td[2]', del_time)
    print("Enlarging Window...")
    driver.fullscreen_window()
    type(driver, 'INTERNALPATIENTUID', Location)
    time.sleep(del_time*3.5)
    print("Starting Automated Anomilie Removal Process...")
    finalpagework(driver)
    driver.close()


def type(driver, name, buttons):
    driver.find_element_by_name(name).send_keys(buttons)
    time.sleep(.8)


def doubleClick(driver, xpath, sleepTime):
    time.sleep(sleepTime)
    admin_elm = driver.find_element_by_xpath(xpath)
    actions = ActionChains(driver)
    actions.double_click(admin_elm)
    actions.perform()
    time.sleep(sleepTime)


def check_exists_by_xpath(driver, xpath):
    try:
        driver.find_element_by_xpath(xpath)
    except NoSuchElementException:
        return False
    return True


def extraInfo(scrolling_threshold, removal_threshold):
    print("-------------Starting Automation of Anomilies Removal----------------")
    print("Program will terminate after scrolling " + str(scrolling_threshold) + " times")
    print("Program will terminate after removing " + str(removal_threshold) + " anomilies")


def build_final_xpathStrings(t_row):
    before_xpath = '//*[@id="DataTables_Table_3"]/tbody/tr['
    after_xpath_pn = ']/td[4]'
    after_xpath_ai = ']/td[7]'
    Final_xpath_pn = before_xpath + str(t_row) + after_xpath_pn
    Final_xpath_ai = before_xpath + str(t_row) + after_xpath_ai
    return Final_xpath_pn, Final_xpath_ai

def build_scroll_xpathString(t_row):
    before_xpath = '//*[@id="DataTables_Table_3"]/tbody/tr['
    after_xpath_pn = ']/td[4]'
    scroll_row = str(t_row + 1)
    scroll_xpath = before_xpath + scroll_row + after_xpath_pn
    return scroll_xpath

def build_PatientID_xpathStrings(t_row):
    before_xpath = '//*[@id="DataTables_Table_3"]/tbody/tr['
    middle_xpath = str(t_row)
    after_xpath = ']/td[5]'
    final_PatientID_xpath = before_xpath + middle_xpath + after_xpath
    return final_PatientID_xpath

def build_AccessionNum_xpathString(t_row):
    before_xpath = '//*[@id="DataTables_Table_3"]/tbody/tr['
    middle_xpath = str(t_row)
    after_xpath = ']/td[2]'
    final_accession_xpath = before_xpath + middle_xpath + after_xpath
    return final_accession_xpath

def get_PatientID(driver, t_row):
    try:
        x_path = build_PatientID_xpathStrings(t_row)
        if check_exists_by_xpath(driver, x_path):
            elm = driver.find_element_by_xpath(x_path)
            if bool(elm.text):
                toreturn = elm.text
                return toreturn.upper()
            else:
                print("No Text")
        else:
            print("No Element found for Patient ID")
            return "Patient ID"
    except WebDriverException:
        print("Error with Patient ID")
        return "Patient ID"

def find_anom_type(driver,t_row):
    before_xpath = '//*[@id="DataTables_Table_3"]/tbody/tr['
    middle_xpath = str(t_row)
    after_xpath = ']/td[6]'
    x_path = before_xpath + middle_xpath + after_xpath
    if check_exists_by_xpath(driver, x_path):
        elm = driver.find_element_by_xpath(x_path)
        if bool(elm.text):
            newvar = elm.text
            return newvar
        else:
            return "COULD NOT VALIDATE ANOMILY TYPE"

def patnamemismatch(anom_type):
    patmismatch = 'Mismatch [Patient Name]'
    if anom_type == patmismatch:
        return True
    else:
        return False

def get_accession_number(driver,t_row):
    try:
        x_path = build_AccessionNum_xpathString(t_row)
        if check_exists_by_xpath(driver, x_path):
            elm = driver.find_element_by_xpath(x_path)
            print("elm things")
            if bool(elm.text):
                toreturn = elm.text
                print("accession number: ", toreturn)
                return toreturn
            else:
                print("No Text")
        else:
            print("No Element found for Accession Number")
            return "Patient ID"
    except WebDriverException:
        print("Error with Patient ID")
        return "Accession Number"

def accesssionnum_allnumeric(accession_text):
    if accession_text.isnumeric():
        return True
    else:
        return False

def print_results(scrolling_counter, amount_removed, total_anomilies_checked):
    print("Scrolled through list: ", scrolling_counter)
    print("Amount of Anomiles Removed: ", amount_removed)
    print("Total Anomilies Iterated through: ", total_anomilies_checked)

def print_row(t_row,Patient_ID):
    if t_row < 10:
        print("Row #: ", t_row, " ----------- ", Patient_ID)
    else:
        print("Row #: ", t_row, " ---------- ", Patient_ID)

def finalpagework(driver):
    final_elm_len = len(driver.find_elements_by_xpath('//*[@id="DataTables_Table_3"]/tbody/tr'))
    print(final_elm_len)
    # X paths we want:
    # Patient name:
    # //*[@id="DataTables_Table_3"]/tbody/tr[1]/td[4]

    # Anomilie Information
    # //*[@id="DataTables_Table_3"]/tbody/tr[1]/td[7]

    scrolling_threshold = scrl_thrs
    removal_threshold = rvm_thrs
    amount_removed = 0
    t_row = 1
    scrolling_counter = 0
    error_counter = 0
    total_anomilies_checked = 0

    while True:
            build_PatientID_xpathStrings(t_row)
            try:
                Patient_ID = get_PatientID(driver, t_row)
                anom_type = find_anom_type(driver, t_row)
                #access_num = get_accession_number(driver, t_row)
            except WebDriverException:
                print("Something Went Wrong")
            if Patient_ID is None:
                error_counter += 1
                print("Error - Error Counter Increased to ", error_counter)
                if error_counter == 7:
                    switch_gridlist(driver)
                elif error_counter == 8:
                    print("Program Terminating")
            else:
                error_counter = 0

            try:
                print_row(t_row, Patient_ID)
                total_anomilies_checked += 1
                time.sleep(del_time*.1)
                if bad_PatientID(Patient_ID) == True or check_phantom(Patient_ID) and patnamemismatch(anom_type) == False: #and accesssionnum_allnumeric(access_num) == True:
                    print("Row #: " + str(t_row) + " needs removal------------------------- " + str(Patient_ID))
                    needs_removal = driver.find_element_by_xpath(build_PatientID_xpathStrings(t_row))
                    try:
                        needs_removal.click()
                        driver.find_element_by_xpath(
                            '/html/body/div[3]/div[2]/div/div[1]/div[3]/div/div[1]/button').click()
                        time.sleep(del_time)
                        orig_txt = driver.find_element_by_xpath('/html/body/div[3]/div[3]/div[1]/div[3]/div').text
                        confirm_txt = separate_name(orig_txt)
                        time.sleep(del_time * 1.5)
                        print("Checking...")
                        time.sleep(del_time)
                        if Real_Delete == True:
                            print("All Numeric - Will be Deleted")
                            try:
                                driver.find_element_by_xpath('/html/body/div[3]/div[3]/div[2]/div[1]/button').click()
                                print(" ------- ", Patient_ID, " -------- ", anom_type, " ----DELETED")
                            except WebDriverException:
                                print("Unable to click Element for Deletion")
                        else:
                            driver.find_element_by_xpath('/html/body/div[3]/div[3]/div[2]/div[2]/button').click()
                            print(Patient_ID + " ----- Was Not Removed")
                        time.sleep(1)
                        # add to list of clickable elements
                    except WebDriverException:
                        print("Element is not clickable")
                    amount_removed += 1
            except WebDriverException:
                print("Error: Could not obtain cell text - Finding Resolution...")
                time.sleep(3)
                if Patient_ID == "EMPTY CELL":
                    error_counter += 1
                else:
                    error_counter = 0
                if error_counter == 4:
                    print("Too Many Continued Empty Cells ----- Program Terminating")
                t_row += 1
            if t_row == 39:
                print("------------------------------------Scrolling Page ", scrolling_counter,
                      " / ", scrl_thrs, " ----------------------------------------")
                print("------------------------------------Anomiles Removed ", amount_removed,
                      " / ", rvm_thrs, " --------------------------------------")
                print("------------------------------------Anomilies Iterated Through ", total_anomilies_checked,
                      " ---------------------------------------------")
                actions = ActionChains(driver)
                scroll_xpath = build_scroll_xpathString(t_row)
                driver.find_element_by_xpath(scroll_xpath).click()
                actions.key_down(Keys.PAGE_DOWN).perform()
                time.sleep(del_time / 3)
                actions.key_up(Keys.PAGE_DOWN).perform()
                # next_elm = current_elm.location_once_scrolled_into_view
                t_row = 1
                scrolling_counter += 1
            if scrolling_counter == (scrolling_threshold + 1) or amount_removed > removal_threshold:
                break
            print_anomRemoved(rvm_thrs, amount_removed)
            t_row = t_row + 1
    print_results(scrolling_counter, amount_removed, total_anomilies_checked)



def main():
    sel_connect()


main()