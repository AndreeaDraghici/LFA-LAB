import re

'''
Checking if a string is a email using Regular Expression (Regex).
Author: Draghici Andreea
17/03/2022
'''


def regex():
    string = 'draghici108@gmail.com'
    # re.findall()=returns a list of all regex matches in a string
    emails = re.findall(r'[\w\.-]+@[\w\.-]+', string)
    for email in emails:
        print(email)


if __name__ == '__main__':
    regex()
