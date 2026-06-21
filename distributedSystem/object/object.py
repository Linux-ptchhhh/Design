#!/usr/bin/env python3

from pathlib import Path
import argparse
import requests
import json

def bucketHandle(buck):
    data = {"bucketname": buck}
    response = requests.post("http://localhost:8080/bucketNames", json=data)
    return response.ok

def objectHandle(bname, filename):
    path = Path(filename)
    if path.is_file():
        abpath = path.resolve()
        metrics = {
            "bucketname": bname,
            "filename": filename
        }

        response = requests.post("http://localhost:8080/putFile", json=metrics)
        return response.ok
    else:
        print(f"File {filename} not found!")
        return False

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--bucketCheck", action="store_true")
    parser.add_argument("--upload", action="store_true")
    args = parser.parse_args()

    if args.bucketCheck:
        print("connecting to minio server")
        print("automatically connect with minio server")
        print(" ")
        print("Enter name of the bucket you want to create or list something:")

        buck = input()
        finals = bucketHandle(buck)

        if finals:
            print(f"Bucket {buck} handled successfully!")
        else:
            print("Error occurred in bucket handing // manage services")

    elif args.upload:
        print("Give name of your file name and bucket name you want to store data on it and local file name")
        print("Enter your bucket name you want to store files:")
        bname = input()

        print("Enter your file name:")
        filename = input()

        res = objectHandle(bname, filename)

        if res:
            print(f"Your file was successfully put into the bucket {bname}")
        else:
            print("Error occurred while uploading file")
