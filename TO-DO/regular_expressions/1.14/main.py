import re

'''
Checking if a string is a hex colour value using Regular Expression (Regex).
Author: Draghici Andreea
17/03/2022
'''


def isValidHexColour(string):
    # regex to check valid
    # hexadecimal color code
    regex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"

    # compile string pattern to re.Pattern object
    pattern = re.compile(regex)

    if string is None:
        return False

    if not re.search(pattern, string):
        return False
    else:
        return True


if __name__ == '__main__' :
    str = "#AA12FF"
    print(str, "is:", isValidHexColour(str))
