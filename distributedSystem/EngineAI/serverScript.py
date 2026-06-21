import docker
import subprocess
import time

client = docker.from_env()

container = client.containers.get("minio")


def dockerRun():
    try:
        container.start()
    except Exception:
        pass

def dockerStop(specific_run=False):
    try:
        if flag:
            container.stop()
            time.sleep(20)
            dockerRun()
        else:
            container.stop()
    except Exception:
        pass



