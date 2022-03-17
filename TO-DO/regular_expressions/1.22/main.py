import re

'''
Checking if a string is a URL using Regular Expression (Regex).
Author: Draghici Andreea
17/03/2022
'''


def isValidURL():
    reg = re.compile(
        r'^(?:http|ftp)s?://'  # http:// or https://
        r'(?:(?:[A-Z0-9](?:[A-Z0-9-]{0,61}[A-Z0-9])?\.)+(?:[A-Z]{2,6}\.?|[A-Z0-9-]{2,}\.?)|'  # domain
        r'localhost|'  # localhost
        r'\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})'  # IP
        r'(?::\d+)?'  # optional port
        r'(?:/?|[/?]\S+)$', re.IGNORECASE)

    string = "https://pytutorial.com"
    # re.match()=looks for a regex match at the beginning of a string
    print(re.match(reg, string) is not None)


if __name__ == '__main__':
    isValidURL()
