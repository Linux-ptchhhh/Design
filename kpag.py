#*****************************THIRU$$^^!.$$32CommunityDevelopersTeam<---india>**********************************
import subprocess as sb
import sys
import os

# handles two parameters logic
def createProcessTwo(param1,param2):

    run = sb.run(
        ["java", "-cp", ".:lib/*", "source.controll",param1,param2],
        capture_output=True,
        text=True,
    )
    
    print(run.stdout)
    
        
      
# handles three parameter funtions and logics
def createProcessThree(param1,param2,param3):
     
   run = sb.run(
        ["java", "-cp", ".:lib/*", "source.controll",param1,param2,param3],
        capture_output=True,
        text=True,
    )
   
   print(run.stdout)
   

if __name__ == "__main__":

    if len(sys.argv) < 3:
      print("Invalid usage")
      sys.exit(1)

    v1 = sys.argv[1]
    v2 = sys.argv[2]
    v3 = sys.argv[3] if len(sys.argv) > 3 else None
     
    if v1 == "-c":
        if len(sys.argv) -1 != 2:
            print("usage: -c <File_name>")
            sys.exit(1)
        createProcessTwo(param1=v1,param2=v2)

        
    elif v1 == "-rn":
        if len(sys.argv)-1 != 3:
            print("usage: -rn <old file_name> <new file_name>")
            sys.exit(1)
        createProcessThree(param1=v1,param2=v2,param3=v3)

    elif v1 == "-cp":
        if len(sys.argv)-1 != 3:
            print("usage : -cp <source_file> <destination_folder>")
            sys.exit(1)
        createProcessThree(param1=v1,param2=v2,param3=v3)

    elif v1 == "-re":
        if len(sys.argv)-1 != 2:
            print("usage: -re <file_name>")
            sys.exit(1)
        createProcessTwo(param1=v1,param2=v2)

    elif v1 == "-perm":
        if len(sys.argv)-1 != 3:
            print("usages: -perm <file_permission> <file_name>")
            sys.exit(1)
        createProcessThree(param1=v1,param2=v2,param3=v3)

    elif v1 == "-permC":
        if v2 == "-check":
            if len(sys.argv)-1 != 3:
                print("usage: -permC -check <file_name>")
        else:
            createProcessTwo(param1=v2,param2=v3)

    elif v1 == "symC":
        if len(sys.argv)-1 != 3:
            print("usages : -symC <link_file> <target_file>")
            sys.exit(1)
        createProcessThree(param1=v1,param2=v2,param3=v3)

    elif v1 == "-sl":
       if len(sys.argv)-1 != 2:
           print("usage : -sl <dir_name>")
           sys.exit(1)
       createProcessTwo(param1=v1,param2=v2)

    elif v1 == "-sld":
        if len(sys.argv)-1 != 2:
            print("usage : -sld <file_name(linking file)>")
            sys.exit(1)
        createProcessTwo(param1=v1,param2=v2)

    elif v1 == "-symlP":
        if len(sys.argv)-1 != 2:
            print("usages : -symlP <linked_file>")
            sys.exit(1)
        createProcessTwo(param1=v1,param2=v2)

    elif v1 == "-symB":
        if len(sys.argv)-1 != 2:
            print("usages : -symB <link_file>")
            sys.exit(1)
        createProcessTwo(param1=v1,param2=v2)

    elif v1 == "-symTC":
        if len(sys.argv)-1 != 3:
            print("usages : -symTC <linked_file> <new_target_file_name>")
            sys.exit(1)
        createProcessThree(param1=v1,param2=v2,param3=v3)

    elif v1 == "-symLC":
        if len(sys.argv)-1 != 3:
            print("usages : -symLC <old_link_file> <new_link_file>")
            sys.exit(1)
        createProcessThree(param1=v1,param2=v2,param3=v3)

    elif v1 == "-search":
        if len(sys.argv)-1 != 2:
            print("usages : -search <filename>")
            sys.exit(1)
        createProcessTwo(param1=v1,param2=v2)
        


         
    

        


