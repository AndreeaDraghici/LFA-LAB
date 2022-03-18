import re

'''
Checking if a string contains small caps, digits and uppercase using Regular Expression (Regex).
Author: Draghici Andreea
18/03/2022
'''


def isPresent(string) :
    # regex to check if a string
    # contains uppercase, lowercase
    # special character & numeric value
    regex = ("^(?=.*[a-z])(?=." +
             "*[A-Z])(?=.*\\d)" +
             "(?=.*[-+_!@#$%^&*., ?]).+$")
    # compile string pattern to re.Pattern object
    pattern = re.compile(regex)

    if None is not string :
        if not re.search(pattern, string) :
            print("Not contains")
        else :
            print("Yes, contains")
        return

    print("Not contains")


if __name__ == '__main__' :
    str = "#Andreea22!34#$"
    isPresent(str)
