def main():

    pn = "Barlow, Jesse, James"
    ai = "New Patient Name: Barlow, Jesse Bill"

    pn = "Lewis, Caitlin"
    ai = "New Patient Name: Lewis Caitlin,"



    pn = "Becker, Audrey"
    ai = "New Patient Name: Becker, Audrey"
    pn = "Vandenbark, Douglas, Eugene"
    ai = "New Patient Name: Vandenbark, Douglas E"
    pn = "Lewis, Caitlin"
    confirm_txt = "New Patient Name: Lewis Caitlin, and a bunch of other stuff"


    pn = separate_names(pn)
    confirm_txt = separate_names(confirm_txt)

    print(confirm_txt)
    confirm_txt = confirm_txt[0:len(pn)]
    print(confirm_txt)

    String = "30.52.1"
    print("is date:", check_date(String))


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

def is_upper(String):
    newString = String.upper()
    print(newString)


def remove_commas(String):
    """Takes a string as an argument, meant to be a persons name in this context, checks to see if it contains any
    ',' (commas) within the string and removes them
    ex. 'Musk, Elon, Jr, -> 'Musk Elon Jr'"""
    if ',' in String:
        count = String.count(",")
        for i in range(count):
            String = String.replace(',', "")
    return String

def remove_namefluff(Anom_Info):
    """Takes a string, checks if it begins with 'New Patient Name' and if so, chops off that portion of the string and
    returns. Otherwise prints an error"""
    if "New Patient Name: " in Anom_Info:
        return Anom_Info[18:]
    return Anom_Info


def separate_names(Name):
    Name = remove_namefluff(Name)
    Name = remove_commas(Name)
    return Name


main()
