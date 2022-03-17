import re

'''
Checking if a string is formatted as a HTML tag  using Regular Expression (Regex).
Author: Draghici Andreea
17/03/2022
'''


def isValidHexColour(string):
    # regex to check valid
    # html tag
    regex = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>"

    # compile string pattern to re.Pattern object
    pattern = re.compile(regex)

    if string is None:
        return False

    if not re.search(pattern, string):
        return False
    else:
        return True


if __name__ == '__main__' :
    str = "<div>"
    print(str, "is:", isValidHexColour(str))
