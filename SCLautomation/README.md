Title: SCL Health Automated Improper Anomile Detection and Deletion
Author: Jonothan Meyer
Date Written: July-November 2021

This program uses Selenium to navigate to Synapse 7's Content Manager within the web browser. Requires input through console for user authentication via username and password to function. Automatically navigates to anomiles and depending on the class finds anomilies that need to be removed and deletes them. Before running check your version of chrome and make sure that the chromedriver matches with your version. If it does not then you need to download the appropriate version and ensure it is in the file path. 

MismatchPatientIDAutoV1.3
This class finds anomiles relating the error of 'mismatch patient name'. It finds anomilie studies that were created because a patients middle name was added to their PACs file. For instance Pat R Surtan -> Pat Rain Surtan. These are obviously the same person, just with their middle name added. Therefore the anomile is deleted. Additionally this class removes any 'test' anomiles such as studies with patient name 'Trash - please delete'. This automatically scrolls through the SPA and iterates through each anomile, cross checking the previous patient name with the new one. By default it is set to terminate after scrolling 70 times or deleting 70 anomilies or if it arrives at the end of the anomile list.

onlyNumericAnomRemove
Functions the same way as the previous class except this deletes any anomiles which do not have a real MRN #. Any MRN that is comprised of only numeric digits are detected and then deleted unless the error is a 'mismatch - patient ID' error because those require further investigation. By default it terminates after scrolling 500 times or removing 100 anomilies. Also removes any 'test' anomiles.

