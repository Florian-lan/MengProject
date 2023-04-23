# Open the file in write mode
print('this is python')


with open("./spring-boot-upload/src/main/java/cn/czh0123/controller/pythonFile/file.txt", "w") as file:
    # Write some text to the file
    file.write("Hello, world!\n")
    file.write("This is some more text.\n")

