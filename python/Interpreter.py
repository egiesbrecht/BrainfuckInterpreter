#!/usr/bin/python3.8

import sys


def interpret(cinput):
    # max for a 16 bit integer
    maxsize = 65535
    memory = bytearray(maxsize)
    pointer = 0
    c = 0
    i = 0
    while i < len(cinput):
        if cinput[i] == '>':
            if pointer == maxsize:
                pointer = 0
            else:
                pointer += 1

        elif cinput[i] == '<':
            if pointer == 0:
                pointer = maxsize-1
            else:
                pointer -= 1

        elif cinput[i] == '+':
            if memory[pointer] == 255:
                memory[pointer] = 0
            else:
             memory[pointer] += 1

        elif cinput[i] == '-':
            if memory[pointer] == 0:
                memory[pointer] = 255
            else:
                memory[pointer] -= 1

        elif cinput[i] == ',':
            memory[pointer] = ord(input()[0])

        elif cinput[i] == '.':
            print(chr(memory[pointer]), end="")

        elif cinput[i] == '!':
            print(memory[pointer], end=""),

        elif cinput[i] == '[':
            if memory[pointer] == 0:
                i += 1
                while c > 0 or cinput[i] != ']':
                    if cinput[i] == '[':
                        c += 1
                    elif cinput[i] == ']':
                        c -= 1

        elif cinput[i] == ']':
            if memory[pointer] != 0:
                i -= 1
                while c > 0 or cinput[i] != '[':
                    if cinput[i] == ']':
                        c += 1
                    elif cinput[i] == '[':
                        c -= 1
                    i -= 1
                #i -= 1
        i += 1
    print("")


if __name__ == '__main__':

    if len(sys.argv) > 1:
        f = open(sys.argv[1], 'r')
        code = f.read()

    else:
        print("Type your code:")
        code = input()
        print("Output/Program input:")

    interpret(code)

