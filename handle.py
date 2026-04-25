import subprocess
import glob

print("Compiling all Java files...")

# get all .java files recursively
java_files = glob.glob("**/*.java", recursive=True)

if not java_files:
    print("❌ No Java files found")
    exit()

result = subprocess.run(
    ["javac", "-d", "."] + java_files,
    capture_output=True,
    text=True
)

if result.returncode != 0:
    print("❌ Compilation error")
    print(result.stderr)
else:
    print("✅ All files compiled successfully")